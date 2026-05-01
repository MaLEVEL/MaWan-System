package com.example.donormanagement.mapper;

import com.example.donormanagement.dto.MedicalCheckResponse;
import com.example.donormanagement.entity.MedicalCheck;
import org.springframework.stereotype.Component;

/**
 * Mapper for MedicalCheck entity and DTOs
 */
@Component
public class MedicalCheckMapper {
    
    public MedicalCheckResponse toResponse(MedicalCheck medicalCheck) {
        if (medicalCheck == null) {
            return null;
        }
        
        MedicalCheckResponse response = new MedicalCheckResponse();
        response.setId(medicalCheck.getId());
        response.setDonorId(medicalCheck.getDonor().getId());
        response.setDonorName(medicalCheck.getDonor().getFirstName() + " " + medicalCheck.getDonor().getLastName());
        response.setCheckDate(medicalCheck.getCheckDate());
        response.setHemoglobin(medicalCheck.getHemoglobin());
        response.setSystolicPressure(medicalCheck.getSystolicPressure());
        response.setDiastolicPressure(medicalCheck.getDiastolicPressure());
        response.setConclusion(medicalCheck.getConclusion());
        
        return response;
    }
}

