package com.umc.jaengchalttak.domain.store.service;

import com.umc.jaengchalttak.domain.store.converter.StoreReviewConverter;
import com.umc.jaengchalttak.domain.store.dto.request.StoreReviewReqDTO;
import com.umc.jaengchalttak.domain.store.dto.response.StoreReviewListResDTO;
import com.umc.jaengchalttak.domain.store.entity.Store;
import com.umc.jaengchalttak.domain.store.entity.StoreReview;
import com.umc.jaengchalttak.domain.store.enums.QueryType;
import com.umc.jaengchalttak.domain.store.payload.StoreException;
import com.umc.jaengchalttak.domain.store.payload.code.StoreErrorCode;
import com.umc.jaengchalttak.domain.store.repository.StoreRepository;
import com.umc.jaengchalttak.domain.store.repository.StoreReviewRepository;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import com.umc.jaengchalttak.global.converter.GlobalConverter;
import com.umc.jaengchalttak.global.dto.CursorPagination;
import com.umc.jaengchalttak.global.dto.OffsetPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final StoreReviewRepository storeReviewRepository;

    @Transactional(readOnly = true)
    public CursorPagination<StoreReviewListResDTO> getMyReviewList(
            Long userId,
            Long storeId,
            Integer pageSize,
            String cursor,
            QueryType query
    ) {
        Pageable pageable = PageRequest.of(0, pageSize);
        Slice<StoreReview> storeReviewSlice;

        if (query == QueryType.ID) {
            Long cursorId = (cursor != null) ? Long.parseLong(cursor) : null;
            storeReviewSlice = storeReviewRepository.findReviewsByIdCursor(userId, storeId, cursorId, pageable);
        } else {
            // 정렬 기준인 별점이 동일할 경우를 대비하여 [별점:ID] 형태의 복합 커서를 분리하여 동점자 처리
            Double  cursorStar = null;
            Long cursorId = null;
            if (cursor != null) {
                String[] parts = cursor.split(":");
                cursorStar = Double.parseDouble(parts[0]);
                cursorId = Long.parseLong(parts[1]);
            }
            storeReviewSlice = storeReviewRepository.findReviewsByStarCursor(userId, storeId, cursorStar, cursorId, pageable);
        }

        List<StoreReview> content = storeReviewSlice.getContent();

        // nextCursor 생성
        String nextCursor = null;
        if (!content.isEmpty()) {
            StoreReview lastReview = content.getLast();
            nextCursor = (query == QueryType.ID)
                    ? String.valueOf(lastReview.getId())
                    : lastReview.getReviewStar() + ":" + lastReview.getId();
        }

        List<StoreReviewListResDTO> dtoList = content.stream()
                .map(StoreReviewConverter::toStoreReviewListResDTO)
                .toList();

        return GlobalConverter.toCursorPagination(
                dtoList,
                storeReviewSlice.hasNext(),
                nextCursor,
                pageSize
        );
    }


    @Transactional
    public void createReview(StoreReviewReqDTO request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // Converter를 통해 entity로 변환
        StoreReview review = StoreReviewConverter.toStoreReview(request, user, store);

        storeReviewRepository.save(review);
    }


}
