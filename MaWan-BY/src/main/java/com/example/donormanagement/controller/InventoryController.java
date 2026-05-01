package com.example.donormanagement.controller;

import com.example.donormanagement.dto.ApiResponse;
import com.example.donormanagement.dto.CreateInventoryItemRequest;
import com.example.donormanagement.dto.InventoryItemResponse;
import com.example.donormanagement.dto.UpdateInventoryStatusRequest;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.InventoryItem;
import com.example.donormanagement.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Inventory Controller
 */
@RestController
@RequestMapping("/api/inventory")
@Tag(name = "Inventory", description = "Blood and bone marrow inventory management APIs")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    @Operation(summary = "Create inventory item", description = "Create a new inventory item from a completed donation")
    public ApiResponse<InventoryItemResponse> createInventoryItem(@Valid @RequestBody CreateInventoryItemRequest request) {
        InventoryItemResponse response = inventoryService.createInventoryItem(request);
        return ApiResponse.success(response);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update inventory status", description = "Update the status of an inventory item")
    public ApiResponse<InventoryItemResponse> updateInventoryStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInventoryStatusRequest request) {
        InventoryItemResponse response = inventoryService.updateInventoryStatus(id, request);
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get inventory item by ID", description = "Get detailed information about a specific inventory item")
    public ApiResponse<InventoryItemResponse> getInventoryItemById(@PathVariable Long id) {
        InventoryItemResponse response = inventoryService.getInventoryItemById(id);
        return ApiResponse.success(response);
    }

    @GetMapping
    @Operation(summary = "Search inventory", description = "Search inventory items with filters and pagination")
    public ApiResponse<Page<InventoryItemResponse>> searchInventory(
            @RequestParam(required = false) Donor.BloodType bloodType,
            @RequestParam(required = false) InventoryItem.InventoryType type,
            @RequestParam(required = false) InventoryItem.InventoryStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate willExpireBefore,
            Pageable pageable) {
        Page<InventoryItemResponse> response = inventoryService.searchInventory(
                bloodType, type, status, willExpireBefore, pageable);
        return ApiResponse.success(response);
    }
}

