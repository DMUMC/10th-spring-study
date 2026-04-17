package com.umcstudy.jace.domain.mission.exception;

import com.umcstudy.jace.global.apiPayload.code.BaseErrorCode;
import com.umcstudy.jace.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
