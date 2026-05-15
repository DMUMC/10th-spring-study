package com.example.umc10th.domain.review.dto;

import java.util.List;
import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record GetInfo(
            Long Id,
            String storeName,
            String content,
            Float star,
            String photoUrl
    ){}

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
