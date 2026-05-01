package com.example.donormanagement.mapper;

import com.example.donormanagement.dto.DonationResponse;
import com.example.donormanagement.entity.Donation;
import org.springframework.stereotype.Component;

/**
 * Mapper for Donation entity and DTOs
 */
@Component
public class DonationMapper {
    
    public DonationResponse toResponse(Donation donation) {
        if (donation == null) {
            return null;
        }
        
        DonationResponse response = new DonationResponse();
        response.setId(donation.getId());
        response.setDonorId(donation.getDonor().getId());
        response.setDonorName(donation.getDonor().getFirstName() + " " + donation.getDonor().getLastName());
        response.setType(donation.getType());
        response.setPlannedAt(donation.getPlannedAt());
        response.setPerformedAt(donation.getPerformedAt());
        response.setStatus(donation.getStatus());
        response.setVolumeMl(donation.getVolumeMl());
        response.setNotes(donation.getNotes());
        response.setPreCheckId(donation.getPreCheck() != null ? donation.getPreCheck().getId() : null);
        
        return response;
    }
}

