package com.example.donormanagement.mapper;

import com.example.donormanagement.dto.AppointmentResponse;
import com.example.donormanagement.entity.Appointment;
import org.springframework.stereotype.Component;

/**
 * Mapper for Appointment entity and DTOs
 */
@Component
public class AppointmentMapper {
    
    public AppointmentResponse toResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setDonorId(appointment.getDonor().getId());
        response.setDonorName(appointment.getDonor().getFirstName() + " " + appointment.getDonor().getLastName());
        response.setType(appointment.getType());
        response.setStartTime(appointment.getStartTime());
        response.setEndTime(appointment.getEndTime());
        response.setStatus(appointment.getStatus());
        response.setLocation(appointment.getLocation());
        response.setDoctorName(appointment.getDoctorName());
        
        return response;
    }
}

