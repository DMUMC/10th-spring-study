package com.umcstudy.jace.domain.user.exception;

import com.umcstudy.jace.global.apiPayload.code.BaseErrorCode;
import com.umcstudy.jace.global.apiPayload.exception.ProjectException;

public class UserException extends ProjectException {
    public UserException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
