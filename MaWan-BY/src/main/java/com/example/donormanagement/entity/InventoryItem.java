package com.example.donormanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * InventoryItem Entity - Blood and bone marrow inventory
 */
@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"donation"})
@ToString(exclude = {"donation"})
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InventoryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Donor.BloodType bloodType;

    @Column(nullable = false)
    private LocalDate collectedAt;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InventoryStatus status;

    @Column(length = 100)
    private String storageLocation;

    @Column(length = 500)
    private String comment;

    public enum InventoryType {
        WHOLE_BLOOD, PLASMA, PLATELETS, BONE_MARROW_SAMPLE
    }

    public enum InventoryStatus {
        IN_STOCK, RESERVED, USED, EXPIRED, DISCARDED
    }
}

