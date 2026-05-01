package com.example.donormanagement.controller;

import com.example.donormanagement.dto.ApiResponse;
import com.example.donormanagement.dto.CreateMedicalCheckRequest;
import com.example.donormanagement.dto.MedicalCheckResponse;
import com.example.donormanagement.service.MedicalCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Medical Check Controller
 */
@RestController
@RequestMapping("/api/medical-checks")
@Tag(name = "Medical Checks", description = "Medical examination management APIs")
public class MedicalCheckController {

    @Autowired
    private MedicalCheckService medicalCheckService;

    @PostMapping
    @Operation(summary = "Create medical check", description = "Create a new medical examination record")
    public ApiResponse<MedicalCheckResponse> createMedicalCheck(@Valid @RequestBody CreateMedicalCheckRequest request) {
        MedicalCheckResponse response = medicalCheckService.createMedicalCheck(request);
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get medical check by ID", description = "Get detailed information about a specific medical check")
    public ApiResponse<MedicalCheckResponse> getMedicalCheckById(@PathVariable Long id) {
        MedicalCheckResponse response = medicalCheckService.getMedicalCheckById(id);
        return ApiResponse.success(response);
    }

    @GetMapping
    @Operation(summary = "Search medical checks", description = "Search medical checks with filters and pagination")
    public ApiResponse<Page<MedicalCheckResponse>> searchMedicalChecks(
            @RequestParam(required = false) Long donorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
            Pageable pageable) {
        Page<MedicalCheckResponse> response = medicalCheckService.searchMedicalChecks(donorId, fromDate, toDate, pageable);
        return ApiResponse.success(response);
    }
}

