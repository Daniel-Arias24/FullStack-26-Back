package com.example.fullstack26back.controller;

import com.example.fullstack26back.dto.ApiResponse;
import com.example.fullstack26back.dto.LoginRequest;
import com.example.fullstack26back.dto.RegisterRequest;
import com.example.fullstack26back.dto.UserResponse;
import com.example.fullstack26back.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse user = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Usuario registrado exitosamente", user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        UserResponse user = userService.login(request);
        return ResponseEntity.ok(ApiResponse.ok("Login exitoso", user));
    }
}