package com.agency.controller;

import com.agency.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.agency.dto.response.JwtAuthenticationResponse;
import com.agency.dto.request.LoginRequest;
import com.agency.dto.request.SignUpRequest;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    @ResponseStatus(OK)
    public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    @ResponseStatus(OK)
    public Long register(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}
