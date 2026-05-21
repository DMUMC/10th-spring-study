package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO.GetInfo;
import com.example.umc10th.domain.review.dto.ReviewResDTO.Pagination;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public GetInfo getInfo(ReviewReqDTO.GetInfo dto) {
        Review review = reviewRepository.findById(dto.id())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.NOT_FOUND));
        return ReviewConverter.toGetInfo(review,null);
    }

    public Pagination<GetInfo> getCursorInfo(
            ReviewReqDTO.GetInfo dto,
            Integer pageSize,
            String cursor,
            String query
    ) {
        Pageable pageable = PageRequest.of(0, pageSize);
        Slice<Review> reviewList;

        if (cursor.equals("-1")) {
            reviewList = reviewRepository
                    .findByMemberIdAndIdLessThanOrderByIdDesc(dto.id(), Long.MAX_VALUE, pageable);
        } else {
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id" -> {
                    long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository
                            .findByMemberIdAndIdLessThanOrderByIdDesc(dto.id(), idCursor, pageable);
                }
                case "star" -> {
                    int starCursor = Integer.parseInt(cursorSplit[0]);
                    long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository
                            .findByMemberIdOrderByStarDesc(dto.id(), starCursor, idCursor, pageable);
                }
                default -> throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        String nextCursor = null;
        if (reviewList.hasContent()) {
            Review lastReview = reviewList.getContent().getLast();
            nextCursor = lastReview.getStar() + ":" + lastReview.getId();
        }

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetInfo).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }

}