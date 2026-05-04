package com.umc.jaengchalttak.domain.store.service;

import com.umc.jaengchalttak.domain.store.converter.StoreReviewConverter;
import com.umc.jaengchalttak.domain.store.dto.request.StoreReviewReqDTO;
import com.umc.jaengchalttak.domain.store.entity.Store;
import com.umc.jaengchalttak.domain.store.entity.StoreReview;
import com.umc.jaengchalttak.domain.store.payload.StoreException;
import com.umc.jaengchalttak.domain.store.payload.code.StoreErrorCode;
import com.umc.jaengchalttak.domain.store.repository.StoreRepository;
import com.umc.jaengchalttak.domain.store.repository.StoreReviewRepository;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final StoreReviewRepository storeReviewRepository;

    @Transactional
    public void createReview(StoreReviewReqDTO request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // Converter를 통해 entity로 변환
        StoreReview review = StoreReviewConverter.toEntity(request, user, store);

        storeReviewRepository.save(review);
    }


}
