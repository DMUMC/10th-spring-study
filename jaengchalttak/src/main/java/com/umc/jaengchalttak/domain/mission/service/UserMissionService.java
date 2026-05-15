package com.umc.jaengchalttak.domain.mission.service;

import com.umc.jaengchalttak.domain.mission.converter.MissionConverter;
import com.umc.jaengchalttak.domain.mission.dto.response.MissionsProgressResDTO;
import com.umc.jaengchalttak.domain.mission.dto.response.MyMissionResDTO;
import com.umc.jaengchalttak.domain.mission.entity.UserMission;
import com.umc.jaengchalttak.domain.mission.enums.ProgressStatus;
import com.umc.jaengchalttak.domain.mission.repository.UserMissionRepository;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.enums.Address;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    private static final int PAGE_SIZE = 3;


    // 사용자 지역에서 현재 도전할 수 있는 새로운 미션 목록을 조회 (홈 화면)
    @Transactional(readOnly = true)
    public MyMissionResDTO getAvailableMissionsByAddress(Long userId, Address address, int page) {
        User user = userRepository.findByIdWithServiceUseAllow(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(
                page - 1,
                PAGE_SIZE,
                Sort.by(Sort.Direction.DESC, "mission.id")
        );

        // 시작 전인 지역 내의 미션 목록 조회
        Page<UserMission> userMissionList = userMissionRepository.
                findMissionsByAddressAndProgress(user.getId(),
                        address,
                        ProgressStatus.BEFORE_START,
                        pageable);

        Integer userTotalPoint = user.getPoint();

        return MissionConverter.toMyMissionResDTO(userMissionList, userTotalPoint);
    }


    // 진행 중/진행 완료 상태인 내 미션 조회
    @Transactional(readOnly = true)
    public List<MissionsProgressResDTO> getUserMissionsByProgress (Long userId, boolean isProgress, int page) {
        // userId 존재하는지 확인
        if (!userRepository.existsById(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        // true면 진행 완료, false면 진행 중
        ProgressStatus status = isProgress ? ProgressStatus.COMPLETED : ProgressStatus.IN_PROGRESS;

        // mission.id 기준으로 내림차순 정렬
        Pageable pageable = PageRequest.of(
                page - 1,
                PAGE_SIZE,
                Sort.by(Sort.Direction.DESC, "mission.id")
        );

        // JPQL로 조회
        Page<UserMission> userMissionList = userMissionRepository.
                findByUserAndProgressWithMissionAndStore(userId, status, pageable);

        // converter가 엔티티 리스트를 DTO로 변환
        return userMissionList.stream()
                .map(MissionConverter::toMissionsProgressResDTO)
                .toList();

    }

}
