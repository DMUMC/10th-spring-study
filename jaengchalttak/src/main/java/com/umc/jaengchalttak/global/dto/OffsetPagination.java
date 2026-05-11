package com.umc.jaengchalttak.global.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OffsetPagination<T> (
        List<T> data,
        Integer pageNumber,
        Integer pageSize
) { }
