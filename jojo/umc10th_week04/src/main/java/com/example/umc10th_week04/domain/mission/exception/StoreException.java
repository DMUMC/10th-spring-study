package com.example.umc10th_week04.domain.mission.exception;

import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_week04.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code) { super(code); }
}
