package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Appointment response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    
    private Long id;
    private Long donorId;
    private String donorName;
    private Appointment.AppointmentType type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Appointment.AppointmentStatus status;
    private String location;
    private String doctorName;
}

