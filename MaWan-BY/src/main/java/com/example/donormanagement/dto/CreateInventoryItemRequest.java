package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.InventoryItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Create inventory item request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInventoryItemRequest {

    @NotNull(message = "Inventory type is required")
    private InventoryItem.InventoryType type;

    @NotNull(message = "Donation ID is required")
    private Long donationId;

    @NotNull(message = "Blood type is required")
    private Donor.BloodType bloodType;

    @NotNull(message = "Collection date is required")
    private LocalDate collectedAt;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    private String storageLocation;

    private String comment;
}

