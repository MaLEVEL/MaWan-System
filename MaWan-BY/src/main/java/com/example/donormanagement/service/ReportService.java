package com.example.donormanagement.service;

import com.example.donormanagement.entity.Donation;
import com.example.donormanagement.repository.AppointmentRepository;
import com.example.donormanagement.repository.DonationRepository;
import com.example.donormanagement.repository.DonorRepository;
import com.example.donormanagement.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Report Service
 */
@Service
public class ReportService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> getDonationSummary(LocalDateTime fromDate, LocalDateTime toDate) {
        List<Object[]> results = donationRepository.getDonationSummary(fromDate, toDate);

        long totalDonations = 0;
        long bloodDonations = 0;
        long boneMarrowDonations = 0;
        double totalVolume = 0;

        for (Object[] result : results) {
            Donation.DonationType type = (Donation.DonationType) result[0];
            Long count = ((Number) result[1]).longValue();
            double volume = result[2] != null ? ((Number) result[2]).doubleValue() : 0.0;

            totalDonations += count;
            totalVolume += volume;

            if (type == Donation.DonationType.BLOOD) {
                bloodDonations = count;
            } else if (type == Donation.DonationType.BONE_MARROW) {
                boneMarrowDonations = count;
            }
        }

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalDonations", totalDonations);
        summary.put("bloodDonations", bloodDonations);
        summary.put("boneMarrowDonations", boneMarrowDonations);
        summary.put("totalVolume", totalVolume);

        return summary;
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getInventoryByBloodGroup() {
        List<Object[]> results = inventoryItemRepository.getInventoryByBloodGroup();
        Map<String, Long> summary = new HashMap<>();

        for (Object[] result : results) {
            String bloodType = result[0].toString();
            Long count = ((Number) result[1]).longValue();
            summary.put(bloodType, count);
        }

        return summary;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getStatistics() {
        Map<String, Object> statistics = new HashMap<>();

        // 总捐献者数量
        long totalDonors = donorRepository.count();
        statistics.put("totalDonors", totalDonors);

        // 总捐献次数
        long totalDonations = donationRepository.count();
        statistics.put("totalDonations", totalDonations);

        // 总库存数量
        long totalInventory = inventoryItemRepository.count();
        statistics.put("totalInventory", totalInventory);

        // 待处理预约数量 (PLANNED + CONFIRMED)
        long plannedAppointments = appointmentRepository.countByStatus(com.example.donormanagement.entity.Appointment.AppointmentStatus.PLANNED);
        long confirmedAppointments = appointmentRepository.countByStatus(com.example.donormanagement.entity.Appointment.AppointmentStatus.CONFIRMED);
        statistics.put("pendingAppointments", plannedAppointments + confirmedAppointments);

        return statistics;
    }
}

