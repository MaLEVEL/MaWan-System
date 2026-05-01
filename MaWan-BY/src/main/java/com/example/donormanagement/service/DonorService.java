package com.example.donormanagement.service;

import com.example.donormanagement.dto.CreateDonorRequest;
import com.example.donormanagement.dto.DonorResponse;
import com.example.donormanagement.dto.UpdateDonorRequest;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.exception.BusinessException;
import com.example.donormanagement.exception.ResourceNotFoundException;
import com.example.donormanagement.mapper.DonorMapper;
import com.example.donormanagement.repository.DonorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Donor Service
 */
@Service
public class DonorService {

    private static final Logger logger = LoggerFactory.getLogger(DonorService.class);

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonorMapper donorMapper;

    @Transactional
    public DonorResponse createDonor(CreateDonorRequest request) {
        logger.info("Creating new donor: {} {}", request.getFirstName(), request.getLastName());

        if (donorRepository.existsByPassportNumber(request.getPassportNumber())) {
            throw new BusinessException("PASSPORT_EXISTS",
                    "Passport number already exists: " + request.getPassportNumber());
        }

        Donor donor = donorMapper.toEntity(request);
        Donor savedDonor = donorRepository.save(donor);

        logger.info("Donor created successfully with ID: {}", savedDonor.getId());
        return donorMapper.toResponse(savedDonor);
    }

    @Transactional(readOnly = true)
    public DonorResponse getDonorById(Long id) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor", "id", id));

        return donorMapper.toResponse(donor);
    }

    @Transactional
    public DonorResponse updateDonor(Long id, UpdateDonorRequest request) {
        logger.info("Updating donor with ID: {}", id);

        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor", "id", id));

        donorMapper.updateEntity(donor, request);
        Donor updatedDonor = donorRepository.save(donor);

        logger.info("Donor updated successfully: {}", id);
        return donorMapper.toResponse(updatedDonor);
    }

    @Transactional
    public void deleteDonor(Long id) {
        logger.info("Deleting donor with ID: {}", id);

        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor", "id", id));

        donor.setActive(false);
        donorRepository.save(donor);

        logger.info("Donor deleted (soft delete) successfully: {}", id);
    }

    @Transactional(readOnly = true)
    public Page<DonorResponse> searchDonors(String name, String passportNumber,
            Donor.BloodType bloodType,
            Pageable pageable) {
        Page<Donor> donors = donorRepository.searchDonors(name, passportNumber, bloodType, pageable);
        return donors.map(donorMapper::toResponse);
    }
}
