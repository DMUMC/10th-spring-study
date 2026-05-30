package com.umcstudy.jace.domain.mission.controller.docs;

import com.umcstudy.jace.domain.mission.dto.MissionResDTO;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Mission", description = "미션 관련 API")
public interface MissionControllerDocs {

    @Operation(summary = "홈 미션 목록 조회", description = "특정 지역의 미션 목록을 커서 기반 페이지네이션으로 조회합니다. 완료한 미션 수도 함께 반환합니다.")
    ApiResponse<MissionResDTO.GetHome> getHome(
            @Parameter(description = "조회할 지역명 (예: 강남구)", required = true, example = "강남구")
            String region,
            @Parameter(description = "마지막으로 받은 미션 ID (첫 요청 시 생략)", example = "10")
            Long cursorId,
            @Parameter(description = "한 페이지에 조회할 미션 수", example = "10")
            int size
    );

    @Operation(summary = "내 미션 목록 조회", description = "로그인한 사용자의 미션 목록을 상태별로 오프셋 기반 페이지네이션으로 조회합니다.")
    ApiResponse<MissionResDTO.GetMyMission> getMyMission(
            @Parameter(description = "미션 상태 (PROGRESS: 진행 중 / SUCCESS: 완료)", required = true, example = "PROGRESS")
            MissionStatus missionCondition,
            @Parameter(description = "조회할 페이지 번호 (0부터 시작)", example = "0")
            int page,
            @Parameter(description = "한 페이지에 조회할 미션 수", example = "10")
            int size
    );

    @Operation(summary = "미션 성공 처리", description = "진행 중인 미션을 성공 상태로 변경합니다.")
    ApiResponse<MissionResDTO.PatchMissionSuc> patchMissionSuc(
            @Parameter(description = "성공 처리할 미션 ID", required = true, example = "1")
            Integer missionId
    );
}
