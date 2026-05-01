package com.example.donormanagement.dto;

import com.example.donormanagement.entity.MedicalCheck;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Medical check response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalCheckResponse {
    
    private Long id;
    private Long donorId;
    private String donorName;
    private LocalDateTime checkDate;
    private Double hemoglobin;
    private Integer systolicPressure;
    private Integer diastolicPressure;
    private MedicalCheck.Conclusion conclusion;
}

