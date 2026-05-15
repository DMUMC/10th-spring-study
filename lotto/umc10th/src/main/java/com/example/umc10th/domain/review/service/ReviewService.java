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
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public GetInfo getInfo(ReviewReqDTO.GetInfo dto) {
        Review review = reviewRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return ReviewConverter.toGetInfo(review,null);
    }

    public Pagination<GetInfo> getCursorInfo(
            ReviewReqDTO.GetInfo dto,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewList;

        // 1. 커서 기반 조회 로직 정리
        if (cursor.equals("-1")) {
            // 첫 페이지 조회
            reviewList = reviewRepository.findByIdOrderByIdDesc(dto.id(), pageRequest);
        } else {
            // 다음 페이지 조회
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id": {
                    // cursorSplit[1]이 실제 비교에 쓰일 ID라고 가정
                    long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findByIdAndIdLessThanOrderByIdDesc(
                            dto.id(),
                            idCursor,
                            pageRequest
                    );
                    break;
                }
                case "star": {
                    // 별점 높은 순 정렬
                    int starCursor = Integer.parseInt(cursorSplit[0]); // 마지막으로 본 별점
                    long idCursor = Long.parseLong(cursorSplit[1]);   // 마지막으로 본 리뷰 ID

                    // 별점이 커서보다 낮거나, 별점이 같으면 ID가 커서보다 작은 데이터 조회
                    reviewList = reviewRepository.findByIdOrderByStarDesc(
                            dto.id(),
                            starCursor,
                            idCursor,
                            pageRequest
                    );
                    break;
                }
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        // 2. 결과가 비어있을 경우에 대한 방어 코드
        String nextCursor = null;
        if (reviewList.hasContent()) {
            Review lastReview = reviewList.getContent().getLast();
            // 실제 비즈니스 로직에 맞는 커서 문자열 생성
            nextCursor = lastReview.getId() + ":" + lastReview.getId();
        }
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetInfo).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }

}