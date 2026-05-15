package com.example.umc10th_week04.domain.user.exception;

import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_week04.global.apiPayload.exception.ProjectException;

public class UserException extends ProjectException {
    public UserException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
