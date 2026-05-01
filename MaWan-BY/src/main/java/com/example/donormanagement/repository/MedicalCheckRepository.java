package com.example.donormanagement.repository;

import com.example.donormanagement.entity.MedicalCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for MedicalCheck entity
 */
@Repository
public interface MedicalCheckRepository extends JpaRepository<MedicalCheck, Long> {
    
    List<MedicalCheck> findByDonorIdOrderByCheckDateDesc(Long donorId);
    
    @Query("SELECT m FROM MedicalCheck m WHERE " +
           "(:donorId IS NULL OR m.donor.id = :donorId) " +
           "AND (:fromDate IS NULL OR m.checkDate >= :fromDate) " +
           "AND (:toDate IS NULL OR m.checkDate <= :toDate)")
    Page<MedicalCheck> searchMedicalChecks(@Param("donorId") Long donorId,
                                           @Param("fromDate") LocalDateTime fromDate,
                                           @Param("toDate") LocalDateTime toDate,
                                           Pageable pageable);
}

