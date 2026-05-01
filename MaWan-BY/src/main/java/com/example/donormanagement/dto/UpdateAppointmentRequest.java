package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Update appointment request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAppointmentRequest {
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
    private Appointment.AppointmentStatus status;
    
    private String location;
    
    private String doctorName;
}

