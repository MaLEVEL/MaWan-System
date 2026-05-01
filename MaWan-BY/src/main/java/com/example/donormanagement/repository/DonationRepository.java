package com.example.donormanagement.repository;

import com.example.donormanagement.entity.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Donation entity
 */
@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    
    List<Donation> findByDonorIdOrderByPlannedAtDesc(Long donorId);
    
    @Query("SELECT d FROM Donation d WHERE " +
           "(:donorId IS NULL OR d.donor.id = :donorId) " +
           "AND (:type IS NULL OR d.type = :type) " +
           "AND (:status IS NULL OR d.status = :status) " +
           "AND (:fromDate IS NULL OR d.plannedAt >= :fromDate) " +
           "AND (:toDate IS NULL OR d.plannedAt <= :toDate)")
    Page<Donation> searchDonations(@Param("donorId") Long donorId,
                                   @Param("type") Donation.DonationType type,
                                   @Param("status") Donation.DonationStatus status,
                                   @Param("fromDate") LocalDateTime fromDate,
                                   @Param("toDate") LocalDateTime toDate,
                                   Pageable pageable);
    
    @Query("SELECT d.type, COUNT(d), COALESCE(SUM(d.volumeMl), 0) FROM Donation d " +
           "WHERE d.status = 'COMPLETED' " +
           "AND (:fromDate IS NULL OR d.performedAt >= :fromDate) " +
           "AND (:toDate IS NULL OR d.performedAt <= :toDate) " +
           "GROUP BY d.type")
    List<Object[]> getDonationSummary(@Param("fromDate") LocalDateTime fromDate,
                                      @Param("toDate") LocalDateTime toDate);
}

