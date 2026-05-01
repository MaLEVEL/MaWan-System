package com.example.donormanagement.dto;

import com.example.donormanagement.entity.MedicalCheck;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Create medical check request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedicalCheckRequest {
    
    @NotNull(message = "Donor ID is required")
    private Long donorId;
    
    @NotNull(message = "Check date is required")
    private LocalDateTime checkDate;
    
    @Positive(message = "Hemoglobin must be positive")
    private Double hemoglobin;
    
    @Positive(message = "Systolic pressure must be positive")
    private Integer systolicPressure;
    
    @Positive(message = "Diastolic pressure must be positive")
    private Integer diastolicPressure;
    
    @NotNull(message = "Conclusion is required")
    private MedicalCheck.Conclusion conclusion;
}

