package com.umc.jaengchalttak.global.converter;

import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;
import com.umc.jaengchalttak.global.dto.CursorPagination;
import com.umc.jaengchalttak.global.dto.OffsetPagination;

import java.util.List;

public class GlobalConverter {

    private GlobalConverter() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    public static <T> OffsetPagination<T> toOffsetPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return OffsetPagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    public static <T> CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

}
