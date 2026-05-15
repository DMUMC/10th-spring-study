package com.umcstudy.jace.domain.user.service;

import com.umcstudy.jace.domain.point.repository.PointRepository;
import com.umcstudy.jace.domain.user.converter.UserConverter;
import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.domain.user.entity.User;
import com.umcstudy.jace.domain.user.exception.UserException;
import com.umcstudy.jace.domain.user.exception.code.UserErrorCode;
import com.umcstudy.jace.domain.user.repository.*;
import com.umcstudy.jace.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserSocialRepository userSocialRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final UserTermRepository userTermRepository;
    private final UserFoodRepository userFoodRepository;
    private final PointRepository pointRepository;
    private final UserSettingRepository userSettingRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserResDTO.PostSignup postSignup(UserReqDTO.PostSignup dto) {
        User user = userRepository.save(UserConverter.toUser(dto));

        userSocialRepository.save(UserConverter.toUserSocial(user, dto));

        dto.termsList().forEach(termsDto -> {
            var term = termRepository.findById(termsDto.termsId())
                    .orElseThrow(() -> new UserException(UserErrorCode.TERMS_NOT_FOUND));
            userTermRepository.save(UserConverter.toUserTerm(user, term, termsDto.isAgree()));
        });

        dto.favoriteFoodList().forEach(foodId -> {
            var food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new UserException(UserErrorCode.FOOD_NOT_FOUND));
            userFoodRepository.save(UserConverter.toUserFood(user, food));
        });

        pointRepository.save(UserConverter.toPoint(user));
        userSettingRepository.save(UserConverter.toUserSetting(user));

        String token = jwtTokenProvider.generateToken(user.getId());
        return UserConverter.toPostSignupRes(user, token);
    }

    @Transactional(readOnly = true)
    public UserResDTO.GetMyPage getMyPage() {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        int pointBalance = pointRepository.findById(userId)
                .map(point -> point.getPointBalance())
                .orElse(0);

        return UserConverter.toGetMyPage(user, pointBalance);
    }

    public UserResDTO.GetTerms getTerms() {
        return null;
    }

    public UserResDTO.GetFoods getFoods() {
        return null;
    }
}
