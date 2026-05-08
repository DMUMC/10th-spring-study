package com.umcstudy.jace.domain.review.service;

import com.umcstudy.jace.domain.mission.entity.Shop;
import com.umcstudy.jace.domain.mission.repository.ShopRepository;
import com.umcstudy.jace.domain.review.converter.ReviewConverter;
import com.umcstudy.jace.domain.review.dto.ReviewReqDTO;
import com.umcstudy.jace.domain.review.dto.ReviewResDTO;
import com.umcstudy.jace.domain.review.entity.Review;
import com.umcstudy.jace.domain.review.exception.ReviewException;
import com.umcstudy.jace.domain.review.exception.code.ReviewErrorCode;
import com.umcstudy.jace.domain.review.repository.ReviewImageRepository;
import com.umcstudy.jace.domain.review.repository.ReviewRepository;
import com.umcstudy.jace.domain.user.entity.User;
import com.umcstudy.jace.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResDTO.PostReviewWrite postReviewWrite(ReviewReqDTO.PostReviewWrite dto, Long shopId) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_NOT_FOUND));

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.SHOP_NOT_FOUND));

        Review review = reviewRepository.save(ReviewConverter.toReview(shop, user, dto));

        if (dto.reviewImageUrl() != null) {
            dto.reviewImageUrl().forEach(url ->
                    reviewImageRepository.save(ReviewConverter.toReviewImage(review, url))
            );
        }

        return ReviewConverter.toPostReviewWrite(review);
    }

    @Transactional(readOnly = true)
    public ReviewResDTO.GetReviews getReviews(Long shopId, Long cursorId, int size) {
        List<Review> reviews = reviewRepository.findByShopIdWithCursor(shopId, cursorId, PageRequest.of(0, size + 1));
        boolean hasNext = reviews.size() > size;
        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        List<ReviewResDTO.ReviewItem> reviewList = reviews.stream()
                .map(ReviewConverter::toReviewItem)
                .collect(Collectors.toList());

        return ReviewConverter.toGetReviews(reviewList, hasNext);
    }
}
