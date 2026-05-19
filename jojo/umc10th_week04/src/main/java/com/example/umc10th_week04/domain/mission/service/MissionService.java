package com.example.umc10th_week04.domain.mission.service;

import com.example.umc10th_week04.domain.mission.converter.MissionConverter;
import com.example.umc10th_week04.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.entity.Mission;
import com.example.umc10th_week04.domain.mission.entity.Store;
import com.example.umc10th_week04.domain.mission.entity.UserMission;
import com.example.umc10th_week04.domain.mission.exception.MissionException;
import com.example.umc10th_week04.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th_week04.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th_week04.domain.mission.repository.MissionRepository;
import com.example.umc10th_week04.domain.mission.repository.StoreRepository;
import com.example.umc10th_week04.domain.mission.repository.UserMissionRepository;
import com.example.umc10th_week04.domain.review.exception.ReviewException;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import com.example.umc10th_week04.global.common.dto.CursorPaginationResDTO;
import com.example.umc10th_week04.global.common.dto.OffsetPaginationResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;


    //가게 내 미션들 조회
    public CursorPaginationResDTO<MissionResDTO.MissionInfo> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        // 페이지 정보들을 PageRequest로 만들기
        Pageable pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        //커서가 있는 경우
        if (!cursor.equals("-1")) {

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()) {
                case "id":

                    // 커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // 가게 내 미션들 조회 & where절에 커서값 기입
                    missionList = missionRepository.findMissionsByStoreIdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {

            // 커서 없이 조회
            missionList = missionRepository.findMissionsByStoreIdOrderByIdDesc(storeId, pageRequest);
        }

        // 다음 커서 계산
        nextCursor = missionList.getContent().getLast().getId() + ":" + missionList.getContent().getLast().getId();

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagenation(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }

    //가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(StoreErrorCode.NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);

        return null;
    }

    // 유저 미션 조회
    public OffsetPaginationResDTO<MissionResDTO.MyMissionInfo> getMyMissions(
            Long userId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        // 정렬 정보 생성
        Sort sortInfo;
        if(sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 유저 미션 조회
        Page<UserMission> missionList = userMissionRepository.findUserMissionsByUserId(userId, pageRequest);

        return MissionConverter.toOffsetPagination(
                missionList.map(MissionConverter::toGetMyMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}
