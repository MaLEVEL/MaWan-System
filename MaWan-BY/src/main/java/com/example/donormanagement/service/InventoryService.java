package com.example.donormanagement.service;

import com.example.donormanagement.dto.CreateInventoryItemRequest;
import com.example.donormanagement.dto.InventoryItemResponse;
import com.example.donormanagement.dto.UpdateInventoryStatusRequest;
import com.example.donormanagement.entity.Donation;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.InventoryItem;
import com.example.donormanagement.exception.BusinessException;
import com.example.donormanagement.exception.ResourceNotFoundException;
import com.example.donormanagement.mapper.InventoryItemMapper;
import com.example.donormanagement.repository.DonationRepository;
import com.example.donormanagement.repository.InventoryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory Service
 */
@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private InventoryItemMapper inventoryItemMapper;

    @Transactional
    public InventoryItemResponse createInventoryItem(CreateInventoryItemRequest request) {
        logger.info("Creating inventory item for donation ID: {}", request.getDonationId());

        Donation donation = donationRepository.findById(request.getDonationId())
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", request.getDonationId()));

        if (donation.getStatus() != Donation.DonationStatus.COMPLETED) {
            throw new BusinessException("INVALID_DONATION_STATUS", "Can only create inventory for completed donations");
        }

        InventoryItem item = new InventoryItem();
        item.setType(request.getType());
        item.setDonation(donation);
        item.setBloodType(request.getBloodType());
        item.setCollectedAt(request.getCollectedAt());
        item.setExpiryDate(request.getExpiryDate());
        item.setStatus(InventoryItem.InventoryStatus.IN_STOCK);
        item.setStorageLocation(request.getStorageLocation());
        item.setComment(request.getComment());

        InventoryItem savedItem = inventoryItemRepository.save(item);
        logger.info("Inventory item created successfully with ID: {}", savedItem.getId());

        return inventoryItemMapper.toResponse(savedItem);
    }

    @Transactional
    public InventoryItemResponse updateInventoryStatus(Long id, UpdateInventoryStatusRequest request) {
        logger.info("Updating inventory item status, ID: {}", id);

        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventoryItem", "id", id));

        item.setStatus(request.getStatus());
        if (request.getComment() != null) {
            item.setComment(request.getComment());
        }

        InventoryItem updatedItem = inventoryItemRepository.save(item);
        logger.info("Inventory item status updated successfully: {}", id);

        return inventoryItemMapper.toResponse(updatedItem);
    }

    @Transactional(readOnly = true)
    public InventoryItemResponse getInventoryItemById(Long id) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventoryItem", "id", id));

        return inventoryItemMapper.toResponse(item);
    }

    @Transactional(readOnly = true)
    public Page<InventoryItemResponse> searchInventory(Donor.BloodType bloodType,
            InventoryItem.InventoryType type,
            InventoryItem.InventoryStatus status,
            LocalDate willExpireBefore,
            Pageable pageable) {
        Page<InventoryItem> items = inventoryItemRepository.searchInventory(
                bloodType, type, status, willExpireBefore, pageable);
        return items.map(inventoryItemMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getInventoryByBloodGroup() {
        List<Object[]> results = inventoryItemRepository.getInventoryByBloodGroup();
        Map<String, Long> summary = new HashMap<>();

        for (Object[] result : results) {
            String bloodType = (String) result[0];
            Long count = (Long) result[1];
            summary.put(bloodType, count);
        }

        return summary;
    }
}
