package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public List<MissionResDTO.GetInfo> getHomeMissions(
            MissionReqDTO.GetInfo dto,
            Long missionId,
            Long locationId,
            int size
    ) {

        Pageable pageable = PageRequest.of(0, size);

        Slice<Mission> missions = missionRepository
                .findAvailableMissions(dto.id(), missionId, locationId, pageable);

        return missions.getContent()
                .stream()
                .map(MissionConverter::toGetHomeInfo)
                .collect(Collectors.toList());
    }


    public List<MemberMissionResDTO.GetInfo> getMemberMissions(
            MissionReqDTO.GetInfo dto,
            Long missionId,
            List<Status> status,
            int size
    ) {

        Pageable pageable = PageRequest.of(0, size);

        Slice<MemberMission> memberMissions = memberMissionRepository
                .findMemberMissions(dto.id(), missionId, status, pageable);

        return memberMissions.getContent()
                .stream()
                .map(MissionConverter::toGetInfo)
                .collect(Collectors.toList());
    }

    public MemberMissionResDTO.Pagination<MemberMissionResDTO.GetInfo> getOffsetMemberMissions(
            MissionReqDTO.GetInfo dto,
            List<Status> status,
            int size,
            Integer pageNumber,
            String sort
    ){

        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else{
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, size, sortInfo);

        Page<MemberMission> missionList = memberMissionRepository.findByMemberIdAndStatus(dto.id(), status, pageRequest);

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetInfo).toList(),
                pageRequest.getPageNumber(),
                pageRequest.getPageSize()
        );

    }
}
