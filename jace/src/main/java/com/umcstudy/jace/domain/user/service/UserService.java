package com.umcstudy.jace.domain.user.service;

import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public UserResDTO.PostSignup postSignup(UserReqDTO.PostSignup dto) {
        return null;
    }

    public UserResDTO.GetTerms getTerms() {
        return null;
    }

    public UserResDTO.GetFoods getFoods() { return null; }
}
