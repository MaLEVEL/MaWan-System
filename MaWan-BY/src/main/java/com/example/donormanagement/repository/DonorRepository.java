package com.example.donormanagement.repository;

import com.example.donormanagement.entity.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Donor entity
 */
@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

       Optional<Donor> findByPassportNumber(String passportNumber);

       boolean existsByPassportNumber(String passportNumber);

       Page<Donor> findByActiveTrue(Pageable pageable);

       @Query("SELECT d FROM Donor d WHERE d.active = true " +
                     "AND (:name IS NULL OR LOWER(CONCAT(d.firstName, ' ', d.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))) "
                     +
                     "AND (:passportNumber IS NULL OR d.passportNumber = :passportNumber) " +
                     "AND (:bloodType IS NULL OR d.bloodType = :bloodType)")
       Page<Donor> searchDonors(@Param("name") String name,
                     @Param("passportNumber") String passportNumber,
                     @Param("bloodType") Donor.BloodType bloodType,
                     Pageable pageable);

       List<Donor> findByActiveTrue();
}
