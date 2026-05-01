package com.example.donormanagement.service;

import com.example.donormanagement.dto.CancelDonationRequest;
import com.example.donormanagement.dto.CompleteDonationRequest;
import com.example.donormanagement.dto.CreateDonationRequest;
import com.example.donormanagement.dto.DonationResponse;
import com.example.donormanagement.entity.Donation;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.MedicalCheck;
import com.example.donormanagement.exception.BusinessException;
import com.example.donormanagement.exception.ResourceNotFoundException;
import com.example.donormanagement.mapper.DonationMapper;
import com.example.donormanagement.repository.DonationRepository;
import com.example.donormanagement.repository.DonorRepository;
import com.example.donormanagement.repository.MedicalCheckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Donation Service
 */
@Service
public class DonationService {

    private static final Logger logger = LoggerFactory.getLogger(DonationService.class);

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private MedicalCheckRepository medicalCheckRepository;

    @Autowired
    private DonationMapper donationMapper;

    @Transactional
    public DonationResponse createDonation(CreateDonationRequest request) {
        logger.info("Creating new donation for donor ID: {}", request.getDonorId());

        Donor donor = donorRepository.findById(request.getDonorId())
                .orElseThrow(() -> new ResourceNotFoundException("Donor", "id", request.getDonorId()));

        if (!donor.getActive()) {
            throw new BusinessException("DONOR_INACTIVE", "Cannot create donation for inactive donor");
        }

        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setType(request.getType());
        donation.setPlannedAt(request.getPlannedAt());
        donation.setStatus(Donation.DonationStatus.PLANNED);
        donation.setVolumeMl(request.getVolumeMl());
        donation.setNotes(request.getNotes());

        if (request.getPreCheckId() != null) {
            MedicalCheck preCheck = medicalCheckRepository.findById(request.getPreCheckId())
                    .orElseThrow(() -> new ResourceNotFoundException("MedicalCheck", "id", request.getPreCheckId()));
            donation.setPreCheck(preCheck);
        }

        Donation savedDonation = donationRepository.save(donation);
        logger.info("Donation created successfully with ID: {}", savedDonation.getId());

        return donationMapper.toResponse(savedDonation);
    }

    @Transactional
    public DonationResponse completeDonation(Long id, CompleteDonationRequest request) {
        logger.info("Completing donation with ID: {}", id);

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", id));

        if (donation.getStatus() != Donation.DonationStatus.PLANNED) {
            throw new BusinessException("INVALID_STATUS", "Can only complete donations with PLANNED status");
        }

        donation.setStatus(Donation.DonationStatus.COMPLETED);
        donation.setPerformedAt(request.getPerformedAt());
        if (request.getVolumeMl() != null) {
            donation.setVolumeMl(request.getVolumeMl());
        }
        if (request.getNotes() != null) {
            donation.setNotes(request.getNotes());
        }

        Donation updatedDonation = donationRepository.save(donation);
        logger.info("Donation completed successfully: {}", id);

        return donationMapper.toResponse(updatedDonation);
    }

    @Transactional
    public DonationResponse cancelDonation(Long id, CancelDonationRequest request) {
        logger.info("Cancelling donation with ID: {}", id);

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", id));

        if (donation.getStatus() == Donation.DonationStatus.COMPLETED) {
            throw new BusinessException("INVALID_STATUS", "Cannot cancel completed donation");
        }

        donation.setStatus(Donation.DonationStatus.CANCELLED);
        donation.setNotes(
                (donation.getNotes() != null ? donation.getNotes() + " | " : "") + "Cancelled: " + request.getReason());

        Donation updatedDonation = donationRepository.save(donation);
        logger.info("Donation cancelled successfully: {}", id);

        return donationMapper.toResponse(updatedDonation);
    }

    @Transactional(readOnly = true)
    public DonationResponse getDonationById(Long id) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donation", "id", id));

        return donationMapper.toResponse(donation);
    }

    @Transactional(readOnly = true)
    public Page<DonationResponse> searchDonations(Long donorId, Donation.DonationType type,
            Donation.DonationStatus status,
            LocalDateTime fromDate, LocalDateTime toDate,
            Pageable pageable) {
        Page<Donation> donations = donationRepository.searchDonations(donorId, type, status, fromDate, toDate,
                pageable);
        return donations.map(donationMapper::toResponse);
    }
}
