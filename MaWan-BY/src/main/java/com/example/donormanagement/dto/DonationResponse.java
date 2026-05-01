package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Donation response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationResponse {
    
    private Long id;
    private Long donorId;
    private String donorName;
    private Donation.DonationType type;
    private LocalDateTime plannedAt;
    private LocalDateTime performedAt;
    private Donation.DonationStatus status;
    private Double volumeMl;
    private String notes;
    private Long preCheckId;
}

