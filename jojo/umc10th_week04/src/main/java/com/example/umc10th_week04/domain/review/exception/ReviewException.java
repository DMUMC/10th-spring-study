package com.example.umc10th_week04.domain.review.exception;

import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_week04.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
