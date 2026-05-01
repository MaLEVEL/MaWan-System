package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Update donor request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDonorRequest {
    
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "Invalid phone number format")
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    private Donor.BloodType bloodType;

    private String hlaType;
}

