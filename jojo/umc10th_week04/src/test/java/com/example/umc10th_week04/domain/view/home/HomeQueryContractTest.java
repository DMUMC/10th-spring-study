package com.example.umc10th_week04.domain.view.home;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HomeQueryContractTest {

    @Test
    void missionRepositoryUsesQueryAndPageableForAvailableMissionsByLocation() throws Exception {
        Method method = repositoryMethod("findAvailableMissionsByLocation");

        assertThat(method.isAnnotationPresent(Query.class)).isTrue();
        assertThat(method.getParameterTypes()).contains(Pageable.class);
    }

    @Test
    void missionRepositoryUsesQueryForCompletedMissionCountInCurrentLocation() throws Exception {
        Method method = repositoryMethod("countCompletedMissionsByUserIdAndLocation");

        assertThat(method.isAnnotationPresent(Query.class)).isTrue();
    }

    @Test
    void homeServiceExposesHomeScreenUseCase() throws Exception {
        Class<?> service = Class.forName("com.example.umc10th_week04.domain.view.home.service.HomeService");

        assertThat(Arrays.stream(service.getDeclaredMethods()).map(Method::getName))
                .contains("getHome");
    }

    @Test
    void homeControllerConnectsHomeEndpoint() throws Exception {
        Class<?> controller = Class.forName("com.example.umc10th_week04.domain.view.home.controller.HomeController");

        assertThat(controller.getAnnotation(RequestMapping.class).value())
                .contains("/api/view");

        assertThat(Arrays.stream(controller.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(GetMapping.class))
                .flatMap(method -> Arrays.stream(method.getAnnotation(GetMapping.class).value())))
                .contains("/home");
    }

    private Method repositoryMethod(String name) throws Exception {
        Class<?> repository = Class.forName("com.example.umc10th_week04.domain.mission.repository.MissionRepository");

        return Arrays.stream(repository.getDeclaredMethods())
                .filter(method -> method.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Missing repository method: " + name));
    }
}
