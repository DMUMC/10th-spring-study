package com.umc.jaengchalttak.domain.inquiry.payload;

import com.umc.jaengchalttak.global.apiPayload.code.BaseErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;

public class InquiryException extends ProjectException {
    public InquiryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}