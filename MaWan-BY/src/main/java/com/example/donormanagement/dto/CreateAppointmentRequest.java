package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Appointment;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Create appointment request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {
    
    @NotNull(message = "Donor ID is required")
    private Long donorId;
    
    @NotNull(message = "Appointment type is required")
    private Appointment.AppointmentType type;
    
    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;
    
    @NotNull(message = "End time is required")
    private LocalDateTime endTime;
    
    private String location;
    
    private String doctorName;
}

