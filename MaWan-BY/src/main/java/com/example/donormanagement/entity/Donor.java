package com.example.donormanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Donor Entity - Blood and bone marrow donors
 */
@Entity
@Table(name = "donors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "donations", "medicalChecks" })
@ToString(exclude = { "donations", "medicalChecks" })
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(nullable = false, unique = true, length = 14)
    @Pattern(regexp = "^\\d{8,14}$", message = "ID must be 8-14 digits")
    private String passportNumber;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private BloodType bloodType;

    @Column(length = 100)
    private String hlaType;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donations = new ArrayList<>();

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalCheck> medicalChecks = new ArrayList<>();

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum BloodType {
        I_POSITIVE("I+"),
        I_NEGATIVE("I-"),
        II_POSITIVE("II+"),
        II_NEGATIVE("II-"),
        III_POSITIVE("III+"),
        III_NEGATIVE("III-"),
        IV_POSITIVE("IV+"),
        IV_NEGATIVE("IV-");

        private final String displayName;

        BloodType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static BloodType fromDisplayName(String displayName) {
            for (BloodType type : values()) {
                if (type.displayName.equals(displayName)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown blood type: " + displayName);
        }
    }
}
