package com.umc.jaengchalttak.domain.store.service;

import com.umc.jaengchalttak.domain.store.dto.request.StoreReviewReqDTO;
import com.umc.jaengchalttak.domain.store.repository.StoreRepository;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public void createReview(StoreReviewReqDTO request) {

    }

}
