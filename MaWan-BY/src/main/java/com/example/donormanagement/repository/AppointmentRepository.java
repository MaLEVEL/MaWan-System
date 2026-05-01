package com.example.donormanagement.repository;

import com.example.donormanagement.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Appointment entity
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

       List<Appointment> findByDonorIdOrderByStartTimeDesc(Long donorId);

       @Query("SELECT a FROM Appointment a WHERE " +
                     "(:donorId IS NULL OR a.donor.id = :donorId) " +
                     "AND (:from IS NULL OR a.startTime >= :from) " +
                     "AND (:to IS NULL OR a.endTime <= :to) " +
                     "AND (:status IS NULL OR a.status = :status)")
       Page<Appointment> searchAppointments(@Param("donorId") Long donorId,
                     @Param("from") LocalDateTime from,
                     @Param("to") LocalDateTime to,
                     @Param("status") Appointment.AppointmentStatus status,
                     Pageable pageable);

       @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE " +
                     "a.donor.id = :donorId " +
                     "AND a.status != 'CANCELLED' " +
                     "AND (:excludeId IS NULL OR a.id != :excludeId) " +
                     "AND ((a.startTime < :endTime AND a.endTime > :startTime))")
       boolean hasConflictingAppointments(@Param("donorId") Long donorId,
                     @Param("startTime") LocalDateTime startTime,
                     @Param("endTime") LocalDateTime endTime,
                     @Param("excludeId") Long excludeId);

       long countByStatus(Appointment.AppointmentStatus status);
}
