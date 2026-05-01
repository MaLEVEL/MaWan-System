package com.example.donormanagement.dto;

import com.example.donormanagement.entity.InventoryItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Update inventory status request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInventoryStatusRequest {
    
    @NotNull(message = "Status is required")
    private InventoryItem.InventoryStatus status;
    
    private String comment;
}

