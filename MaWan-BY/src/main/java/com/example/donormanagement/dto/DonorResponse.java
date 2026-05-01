package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Donor response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorResponse {
    
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Donor.Gender gender;
    private String passportNumber;
    private String phone;
    private String email;
    private String address;
    private Donor.BloodType bloodType;
    private String hlaType;
    private Boolean active;
}

