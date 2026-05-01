package com.example.donormanagement.controller;

import com.example.donormanagement.dto.ApiResponse;
import com.example.donormanagement.dto.UserResponse;
import com.example.donormanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Get information about the currently logged-in user")
    public ApiResponse<UserResponse> getCurrentUser() {
        UserResponse response = userService.getCurrentUser();
        return ApiResponse.success(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users", description = "Get paginated list of all users (Admin only)")
    public ApiResponse<Page<UserResponse>> getAllUsers(
            @RequestParam(required = false) String username,
            Pageable pageable) {
        Page<UserResponse> response = userService.getAllUsers(username, pageable);
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get user by ID", description = "Get user details by ID (Admin only)")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ApiResponse.success(response);
    }
}

