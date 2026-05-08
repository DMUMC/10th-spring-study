package com.UmcSpringStudy.jingjing2.domain.review.service;

import com.UmcSpringStudy.jingjing2.domain.review.entity.StoreReview;
import com.UmcSpringStudy.jingjing2.domain.review.converter.ReviewConverter;
import com.UmcSpringStudy.jingjing2.domain.review.dto.request.ReviewCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.review.dto.response.ReviewResponse;
import com.UmcSpringStudy.jingjing2.domain.review.repository.StoreReviewRepository;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.store.repository.StoreRepository;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.repository.UserRepository;
import com.UmcSpringStudy.jingjing2.global.exception.CustomException;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.StoreErrorCode;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final StoreReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResponse createReview(ReviewCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(StoreErrorCode.STORE_NOT_FOUND));

        StoreReview newReview = ReviewConverter.toStoreReview(request, user, store);
        return ReviewConverter.toReviewResponse(reviewRepository.save(newReview), new ArrayList<>());
    }
}