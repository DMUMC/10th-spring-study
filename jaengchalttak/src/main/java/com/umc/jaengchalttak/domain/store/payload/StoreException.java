package com.umc.jaengchalttak.domain.store.payload;

import com.umc.jaengchalttak.global.apiPayload.code.BaseErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}