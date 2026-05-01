package com.example.donormanagement.mapper;

import com.example.donormanagement.dto.InventoryItemResponse;
import com.example.donormanagement.entity.InventoryItem;
import org.springframework.stereotype.Component;

/**
 * Mapper for InventoryItem entity and DTOs
 */
@Component
public class InventoryItemMapper {
    
    public InventoryItemResponse toResponse(InventoryItem item) {
        if (item == null) {
            return null;
        }

        InventoryItemResponse response = new InventoryItemResponse();
        response.setId(item.getId());
        response.setType(item.getType());
        response.setDonationId(item.getDonation().getId());
        response.setBloodType(item.getBloodType());
        response.setCollectedAt(item.getCollectedAt());
        response.setExpiryDate(item.getExpiryDate());
        response.setStatus(item.getStatus());
        response.setStorageLocation(item.getStorageLocation());
        response.setComment(item.getComment());

        return response;
    }
}

