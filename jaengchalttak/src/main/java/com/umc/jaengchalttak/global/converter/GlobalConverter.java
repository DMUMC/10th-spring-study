package com.umc.jaengchalttak.global.converter;

import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;
import com.umc.jaengchalttak.global.dto.Pagination;

import java.util.List;

public class GlobalConverter {

    private GlobalConverter() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    public static <T> Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

}
