package com.example.donormanagement.repository;

import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.InventoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for InventoryItem entity
 */
@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    @Query("SELECT i FROM InventoryItem i WHERE " +
           "(:bloodType IS NULL OR i.bloodType = :bloodType) " +
           "AND (:type IS NULL OR i.type = :type) " +
           "AND (:status IS NULL OR i.status = :status) " +
           "AND (:willExpireBefore IS NULL OR i.expiryDate <= :willExpireBefore)")
    Page<InventoryItem> searchInventory(@Param("bloodType") Donor.BloodType bloodType,
                                        @Param("type") InventoryItem.InventoryType type,
                                        @Param("status") InventoryItem.InventoryStatus status,
                                        @Param("willExpireBefore") LocalDate willExpireBefore,
                                        Pageable pageable);

    @Query("SELECT i.bloodType, COUNT(i) FROM InventoryItem i " +
           "WHERE i.status = 'IN_STOCK' " +
           "GROUP BY i.bloodType")
    List<Object[]> getInventoryByBloodGroup();
}

