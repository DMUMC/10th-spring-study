package com.umc.jaengchalttak.global.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CursorPagination<T> (
        List<T> data,
        Boolean hasNext,
        String nextCursor,
        Integer pageSize
) {}
