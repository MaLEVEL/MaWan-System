package com.example.donormanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * MedicalCheck Entity - Medical examination records
 */
@Entity
@Table(name = "medical_checks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"donor"})
@ToString(exclude = {"donor"})
public class MedicalCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @Column(nullable = false)
    private LocalDateTime checkDate;

    @Column
    private Double hemoglobin;

    @Column
    private Integer systolicPressure;

    @Column
    private Integer diastolicPressure;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Conclusion conclusion;

    public enum Conclusion {
        FIT, UNFIT, NEEDS_RECHECK
    }
}

