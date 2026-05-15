package com.example.umc10th_week04.domain.mission;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MissionQueryContractTest {

    @Test
    void missionRepositoryUsesQueryAndPageableForMyMissions() throws Exception {
        Method method = repositoryMethod("findUserMissionsByUserIdAndCompleted");

        assertThat(method.isAnnotationPresent(Query.class)).isTrue();
        assertThat(method.getParameterTypes()).contains(Pageable.class);
    }

    @Test
    void missionServiceExposesPagedMyMissionUseCase() throws Exception {
        Class<?> service = Class.forName("com.example.umc10th_week04.domain.mission.service.MissionService");

        assertThat(Arrays.stream(service.getDeclaredMethods()).map(Method::getName))
                .contains("getMyMissions");
    }

    @Test
    void missionControllerConnectsPagedMyMissionEndpoint() throws Exception {
        Class<?> controller = Class.forName("com.example.umc10th_week04.domain.mission.controller.MissionController");

        assertThat(controller.getAnnotation(RequestMapping.class).value())
                .contains("/api/missions");

        assertThat(Arrays.stream(controller.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(GetMapping.class))
                .flatMap(method -> Arrays.stream(method.getAnnotation(GetMapping.class).value())))
                .contains("/me");
    }

    private Method repositoryMethod(String name) throws Exception {
        Class<?> repository = Class.forName("com.example.umc10th_week04.domain.mission.repository.MissionRepository");

        return Arrays.stream(repository.getDeclaredMethods())
                .filter(method -> method.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Missing repository method: " + name));
    }
}
