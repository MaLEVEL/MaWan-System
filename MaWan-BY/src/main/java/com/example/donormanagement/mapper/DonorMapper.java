package com.example.donormanagement.mapper;

import com.example.donormanagement.dto.CreateDonorRequest;
import com.example.donormanagement.dto.DonorResponse;
import com.example.donormanagement.dto.UpdateDonorRequest;
import com.example.donormanagement.entity.Donor;
import org.springframework.stereotype.Component;

/**
 * Mapper for Donor entity and DTOs
 */
@Component
public class DonorMapper {
    
    public Donor toEntity(CreateDonorRequest request) {
        if (request == null) {
            return null;
        }
        
        Donor donor = new Donor();
        donor.setFirstName(request.getFirstName());
        donor.setLastName(request.getLastName());
        donor.setDateOfBirth(request.getDateOfBirth());
        donor.setGender(request.getGender());
        donor.setPassportNumber(request.getPassportNumber());
        donor.setPhone(request.getPhone());
        donor.setEmail(request.getEmail());
        donor.setAddress(request.getAddress());
        donor.setBloodType(request.getBloodType());
        donor.setHlaType(request.getHlaType());
        donor.setActive(true);
        
        return donor;
    }
    
    public void updateEntity(Donor donor, UpdateDonorRequest request) {
        if (donor == null || request == null) {
            return;
        }
        
        if (request.getFirstName() != null) {
            donor.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            donor.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            donor.setPhone(request.getPhone());
        }
        if (request.getEmail() != null) {
            donor.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            donor.setAddress(request.getAddress());
        }
        if (request.getBloodType() != null) {
            donor.setBloodType(request.getBloodType());
        }
        if (request.getHlaType() != null) {
            donor.setHlaType(request.getHlaType());
        }
    }
    
    public DonorResponse toResponse(Donor donor) {
        if (donor == null) {
            return null;
        }
        
        DonorResponse response = new DonorResponse();
        response.setId(donor.getId());
        response.setFirstName(donor.getFirstName());
        response.setLastName(donor.getLastName());
        response.setDateOfBirth(donor.getDateOfBirth());
        response.setGender(donor.getGender());
        response.setPassportNumber(donor.getPassportNumber());
        response.setPhone(donor.getPhone());
        response.setEmail(donor.getEmail());
        response.setAddress(donor.getAddress());
        response.setBloodType(donor.getBloodType());
        response.setHlaType(donor.getHlaType());
        response.setActive(donor.getActive());
        
        return response;
    }
}

