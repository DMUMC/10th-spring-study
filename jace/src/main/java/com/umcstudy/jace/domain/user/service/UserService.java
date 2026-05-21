package com.umcstudy.jace.domain.user.service;

import com.umcstudy.jace.domain.point.repository.PointRepository;
import com.umcstudy.jace.domain.user.converter.UserConverter;
import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.domain.user.entity.Food;
import com.umcstudy.jace.domain.user.entity.Term;
import com.umcstudy.jace.domain.user.entity.User;
import com.umcstudy.jace.domain.user.exception.UserException;
import com.umcstudy.jace.domain.user.exception.code.UserErrorCode;
import com.umcstudy.jace.domain.user.repository.*;
import com.umcstudy.jace.global.security.JwtTokenProvider;
import com.umcstudy.jace.global.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResDTO.PostSignup postSignup(UserReqDTO.PostSignup dto) {
        User user = userRepository.save(UserConverter.toUser(dto));

        userSocialRepository.save(UserConverter.toUserSocial(user, dto));

        // 약관: 1번 조회 + 1번 일괄 저장
        List<Long> termIds = dto.termsList().stream()
                .map(UserReqDTO.TermsDTO::termsId)
                .toList();
        Map<Long, Term> termMap = termRepository.findAllById(termIds).stream()
                .collect(Collectors.toMap(Term::getId, t -> t));
        if (termMap.size() != termIds.size()) {
            throw new UserException(UserErrorCode.TERMS_NOT_FOUND);
        }
        userTermRepository.saveAll(
                dto.termsList().stream()
                        .map(termsDto -> UserConverter.toUserTerm(user, termMap.get(termsDto.termsId()), termsDto.isAgree()))
                        .toList()
        );

        // 음식: 1번 조회 + 1번 일괄 저장
        List<Food> foods = foodRepository.findAllById(dto.favoriteFoodList());
        if (foods.size() != dto.favoriteFoodList().size()) {
            throw new UserException(UserErrorCode.FOOD_NOT_FOUND);
        }
        userFoodRepository.saveAll(
                foods.stream()
                        .map(food -> UserConverter.toUserFood(user, food))
                        .toList()
        );

        pointRepository.save(UserConverter.toPoint(user));
        userSettingRepository.save(UserConverter.toUserSetting(user));

        String token = jwtTokenProvider.generateToken(user.getId());
        return UserConverter.toPostSignupRes(user, token);
    }

    @Transactional
    public UserResDTO.PostSignup formSignup(UserReqDTO.FormSignup dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(dto.password());
        User user = userRepository.save(UserConverter.toUserFromForm(dto, encodedPassword));

        List<Long> termIds = dto.termsList().stream()
                .map(UserReqDTO.TermsDTO::termsId)
                .toList();
        Map<Long, Term> termMap = termRepository.findAllById(termIds).stream()
                .collect(Collectors.toMap(Term::getId, t -> t));
        if (termMap.size() != termIds.size()) {
            throw new UserException(UserErrorCode.TERMS_NOT_FOUND);
        }
        userTermRepository.saveAll(
                dto.termsList().stream()
                        .map(termsDto -> UserConverter.toUserTerm(user, termMap.get(termsDto.termsId()), termsDto.isAgree()))
                        .toList()
        );

        List<Food> foods = foodRepository.findAllById(dto.favoriteFoodList());
        if (foods.size() != dto.favoriteFoodList().size()) {
            throw new UserException(UserErrorCode.FOOD_NOT_FOUND);
        }
        userFoodRepository.saveAll(
                foods.stream()
                        .map(food -> UserConverter.toUserFood(user, food))
                        .toList()
        );

        pointRepository.save(UserConverter.toPoint(user));
        userSettingRepository.save(UserConverter.toUserSetting(user));

        String token = jwtTokenProvider.generateToken(user.getId());
        return UserConverter.toPostSignupRes(user, token);
    }

    @Transactional(readOnly = true)
    public UserResDTO.GetMyPage getMyPage() {
        Long userId = SecurityUtils.getCurrentUserId();

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
