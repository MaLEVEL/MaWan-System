package com.example.donormanagement.controller;

import com.example.donormanagement.dto.*;
import com.example.donormanagement.entity.Donation;
import com.example.donormanagement.service.DonationService;
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
 * Donation Controller
 */
@RestController
@RequestMapping("/api/donations")
@Tag(name = "Donations", description = "Donation management APIs")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping
    @Operation(summary = "Create donation", description = "Create a new donation record")
    public ApiResponse<DonationResponse> createDonation(@Valid @RequestBody CreateDonationRequest request) {
        DonationResponse response = donationService.createDonation(request);
        return ApiResponse.success(response);
    }

    @PutMapping("/{id}/complete")
    @Operation(summary = "Complete donation", description = "Mark a donation as completed")
    public ApiResponse<DonationResponse> completeDonation(
            @PathVariable Long id,
            @Valid @RequestBody CompleteDonationRequest request) {
        DonationResponse response = donationService.completeDonation(id, request);
        return ApiResponse.success(response);
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel donation", description = "Cancel a planned donation")
    public ApiResponse<DonationResponse> cancelDonation(
            @PathVariable Long id,
            @Valid @RequestBody CancelDonationRequest request) {
        DonationResponse response = donationService.cancelDonation(id, request);
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get donation by ID", description = "Get detailed information about a specific donation")
    public ApiResponse<DonationResponse> getDonationById(@PathVariable Long id) {
        DonationResponse response = donationService.getDonationById(id);
        return ApiResponse.success(response);
    }

    @GetMapping
    @Operation(summary = "Search donations", description = "Search donations with filters and pagination")
    public ApiResponse<Page<DonationResponse>> searchDonations(
            @RequestParam(required = false) Long donorId,
            @RequestParam(required = false) Donation.DonationType type,
            @RequestParam(required = false) Donation.DonationStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
            Pageable pageable) {
        Page<DonationResponse> response = donationService.searchDonations(donorId, type, status, fromDate, toDate, pageable);
        return ApiResponse.success(response);
    }
}

