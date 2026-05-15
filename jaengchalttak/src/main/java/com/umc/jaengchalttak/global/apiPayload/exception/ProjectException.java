package com.umc.jaengchalttak.global.apiPayload.exception;

import com.umc.jaengchalttak.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
  private final BaseErrorCode errorCode;
}
