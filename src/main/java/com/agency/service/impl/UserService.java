package com.agency.service.impl;

import org.springframework.stereotype.Service;
import com.agency.dto.request.UserSummary;
import com.agency.security.UserPrincipal;

@Service
public class UserService {

    public UserSummary getCurrentUser(UserPrincipal userPrincipal) {
        return UserSummary.builder()
                .id(userPrincipal.getId())
                .email(userPrincipal.getEmail())
                .name(userPrincipal.getName())
                .build();
    }
}
