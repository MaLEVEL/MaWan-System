package com.example.donormanagement.controller;

import com.example.donormanagement.dto.ApiResponse;
import com.example.donormanagement.dto.CreateUserRequest;
import com.example.donormanagement.dto.DonorRegisterRequest;
import com.example.donormanagement.dto.DonorRegisterResponse;
import com.example.donormanagement.dto.LoginRequest;
import com.example.donormanagement.dto.LoginResponse;
import com.example.donormanagement.dto.UserResponse;
import com.example.donormanagement.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and user registration APIs")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ApiResponse.success(response);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Register new user", description = "Create a new system user (Admin only)")
    public ApiResponse<UserResponse> register(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = authService.register(request);
        return ApiResponse.success(response);
    }

    @PostMapping("/donor-register")
    @Operation(summary = "Donor self-registration", description = "Register as a new donor with account and donor info")
    public ApiResponse<DonorRegisterResponse> donorRegister(@Valid @RequestBody DonorRegisterRequest request) {
        DonorRegisterResponse response = authService.registerDonor(request);
        return ApiResponse.success(response);
    }
}

