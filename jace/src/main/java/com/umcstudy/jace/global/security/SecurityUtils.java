package com.umcstudy.jace.global.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {}

    public static Long getCurrentUserId() {
        return Long.parseLong(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
    }
}
