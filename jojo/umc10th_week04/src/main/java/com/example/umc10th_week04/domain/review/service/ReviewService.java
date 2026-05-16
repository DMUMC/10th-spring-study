package com.example.umc10th_week04.domain.review.service;

import com.example.umc10th_week04.domain.mission.entity.Store;
import com.example.umc10th_week04.domain.mission.exception.MissionException;
import com.example.umc10th_week04.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th_week04.domain.mission.repository.StoreRepository;
import com.example.umc10th_week04.domain.review.converter.ReviewConverter;
import com.example.umc10th_week04.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_week04.domain.review.dto.ReviewResDTO;
import com.example.umc10th_week04.domain.review.entity.Review;
import com.example.umc10th_week04.domain.review.exception.ReviewException;
import com.example.umc10th_week04.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th_week04.domain.review.repository.ReviewRepository;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.exception.UserException;
import com.example.umc10th_week04.domain.user.exception.code.UserErrorCode;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateReview createReview(Long storeId, ReviewReqDTO.CreateReview request) {
        Long targetStoreId = resolveStoreId(storeId, request.storeId());

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(targetStoreId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, user, store);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReview(savedReview);
    }

    public ReviewResDTO.GetReview getReview() {
        return ReviewConverter.toGetReview();
    }

    private Long resolveStoreId(Long pathStoreId, Long requestStoreId) {
        if (requestStoreId == null || pathStoreId.equals(requestStoreId)) {
            return pathStoreId;
        }

        throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_REQUEST);
    }

    // 유저 리뷰 조회
    public ReviewResDTO.Pagenation<ReviewResDTO.ReviewInfo> getMyReviews(
            Long userId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if(!cursor.equals("-1")){

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":

                    // 커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // 내 리뷰 조회 & where절에 cursor 값 기입
                    reviewList = reviewRepository.findReviewsByUser_IdAAndIdLessThanOrderByIdDesc(
                            userId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_REQUEST);
            }
        } else {
            reviewList = reviewRepository.findReviewsByUser_IdOrderByDesc(userId, pageRequest);
        }

        // 다음 커서 계산
        nextCursor = reviewList.getContent().getLast().getId() + ":" + reviewList.getContent().getLast().getId();

        // 리뷰들 응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toReviewInfo).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
