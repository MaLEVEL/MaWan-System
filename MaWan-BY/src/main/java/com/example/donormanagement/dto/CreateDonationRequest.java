package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Create donation request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDonationRequest {
    
    @NotNull(message = "Donor ID is required")
    private Long donorId;
    
    @NotNull(message = "Donation type is required")
    private Donation.DonationType type;
    
    @NotNull(message = "Planned date/time is required")
    private LocalDateTime plannedAt;
    
    @Positive(message = "Volume must be positive")
    private Double volumeMl;
    
    private String notes;
    
    private Long preCheckId;
}

