package com.example.donormanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Complete donation request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteDonationRequest {
    
    @NotNull(message = "Performed date/time is required")
    private LocalDateTime performedAt;
    
    @Positive(message = "Volume must be positive")
    private Double volumeMl;
    
    private String notes;
}

