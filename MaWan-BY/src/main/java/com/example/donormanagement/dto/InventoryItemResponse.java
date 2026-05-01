package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Inventory item response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemResponse {

    private Long id;
    private InventoryItem.InventoryType type;
    private Long donationId;
    private Donor.BloodType bloodType;
    private LocalDate collectedAt;
    private LocalDate expiryDate;
    private InventoryItem.InventoryStatus status;
    private String storageLocation;
    private String comment;
}

