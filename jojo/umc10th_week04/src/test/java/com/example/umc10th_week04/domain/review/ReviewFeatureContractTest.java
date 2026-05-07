package com.example.umc10th_week04.domain.review;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewFeatureContractTest {

    @Test
    void reviewCreateRequestDtoContainsRequiredFields() throws Exception {
        Class<?> dto = Class.forName("com.example.umc10th_week04.domain.review.dto.ReviewReqDTO$CreateReview");

        assertThat(recordComponentNames(dto))
                .containsExactly("userId", "storeId", "score", "contents");
    }

    @Test
    void reviewCreateResponseDtoContainsCreatedReviewFields() throws Exception {
        Class<?> dto = Class.forName("com.example.umc10th_week04.domain.review.dto.ReviewResDTO$CreateReview");

        assertThat(recordComponentNames(dto))
                .containsExactly("reviewId", "userId", "storeId", "score", "contents");
    }

    @Test
    void reviewServiceExposesCreateReviewUseCase() throws Exception {
        Class<?> service = Class.forName("com.example.umc10th_week04.domain.review.service.ReviewService");

        assertThat(Arrays.stream(service.getDeclaredMethods()).map(Method::getName))
                .contains("createReview");
    }

    @Test
    void reviewControllerConnectsCreateReviewEndpoint() throws Exception {
        Class<?> controller = Class.forName("com.example.umc10th_week04.domain.review.controller.ReviewController");

        assertThat(controller.getAnnotation(RequestMapping.class).value())
                .contains("/api/reviews");

        assertThat(Arrays.stream(controller.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(PostMapping.class))
                .flatMap(method -> Arrays.stream(method.getAnnotation(PostMapping.class).value())))
                .contains("/stores/{storeId}");
    }

    private String[] recordComponentNames(Class<?> recordClass) {
        return Arrays.stream(recordClass.getRecordComponents())
                .map(RecordComponent::getName)
                .toArray(String[]::new);
    }
}
