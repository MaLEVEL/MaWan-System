package com.example.donormanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Donor registration response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorRegisterResponse {

    private Long userId;
    private Long donorId;
    private String username;
    private String token;
}
