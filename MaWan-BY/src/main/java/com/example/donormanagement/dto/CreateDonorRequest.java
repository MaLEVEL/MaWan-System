package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donor;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Create donor request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDonorRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50)
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Donor.Gender gender;

    @NotBlank(message = "ID is required")
    @Size(min = 8, max = 14, message = "ID must be 8-14 digits")
    @Pattern(regexp = "^\\d{8,14}$", message = "ID must be 8-14 digits")
    private String passportNumber;

    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "Invalid phone number format")
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    @NotNull(message = "Blood type is required")
    private Donor.BloodType bloodType;

    private String hlaType;

    @AssertTrue(message = "Donor must be at least 18 years old")
    public boolean isAdult() {
        if (dateOfBirth == null)
            return false;
        LocalDate today = LocalDate.now();
        LocalDate adultDate = dateOfBirth.plusYears(18);
        return !adultDate.isAfter(today);
    }
}
