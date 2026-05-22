package com.example.umc10th.global.common;

import lombok.Builder;

import java.util.List;

public class Pagination {

    @Builder
    public record Pagi<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}
}
