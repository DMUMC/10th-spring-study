package com.example.umc10th_week04.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MyPageQueryContractTest {

    @Test
    void myPageResponseDtoContainsScreenFields() throws Exception {
        Class<?> dto = Class.forName("com.example.umc10th_week04.domain.user.dto.UserResDTO$MyPageInfo");

        assertThat(recordComponentNames(dto))
                .containsExactly("name", "email", "point", "reviews", "logoutUrl");
    }

    @Test
    void userRepositoryUsesQueryForMyPageScreen() throws Exception {
        Method method = repositoryMethod("findMyPageByUserId");

        assertThat(method.isAnnotationPresent(Query.class)).isTrue();
    }

    @Test
    void userServiceExposesMyPageUseCase() throws Exception {
        Class<?> service = Class.forName("com.example.umc10th_week04.domain.user.service.UserService");

        assertThat(Arrays.stream(service.getDeclaredMethods()).map(Method::getName))
                .contains("getMyPage");
    }

    @Test
    void userControllerConnectsMyPageEndpoint() throws Exception {
        Class<?> controller = Class.forName("com.example.umc10th_week04.domain.user.controller.UserController");

        assertThat(controller.getAnnotation(RequestMapping.class).value())
                .contains("/api/users");

        assertThat(Arrays.stream(controller.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(GetMapping.class))
                .flatMap(method -> Arrays.stream(method.getAnnotation(GetMapping.class).value())))
                .contains("/me");
    }

    private Method repositoryMethod(String name) throws Exception {
        Class<?> repository = Class.forName("com.example.umc10th_week04.domain.user.repository.UserRepository");

        return Arrays.stream(repository.getDeclaredMethods())
                .filter(method -> method.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Missing repository method: " + name));
    }

    private String[] recordComponentNames(Class<?> recordClass) {
        return Arrays.stream(recordClass.getRecordComponents())
                .map(RecordComponent::getName)
                .toArray(String[]::new);
    }
}
