package com.example.donormanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cancel donation request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelDonationRequest {
    
    @NotBlank(message = "Cancellation reason is required")
    private String reason;
}

