package com.example.donormanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Donation Entity - Blood and bone marrow donation records
 */
@Entity
@Table(name = "donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"donor", "preCheck", "inventoryItems"})
@ToString(exclude = {"donor", "preCheck", "inventoryItems"})
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DonationType type;

    @Column(nullable = false)
    private LocalDateTime plannedAt;

    @Column
    private LocalDateTime performedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DonationStatus status;

    @Column
    private Double volumeMl;

    @Column(length = 500)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_check_id")
    private MedicalCheck preCheck;

    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> inventoryItems = new ArrayList<>();

    public enum DonationType {
        BLOOD, BONE_MARROW
    }

    public enum DonationStatus {
        PLANNED, COMPLETED, CANCELLED, REJECTED
    }
}

