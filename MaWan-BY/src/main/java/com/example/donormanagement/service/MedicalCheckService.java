package com.example.donormanagement.service;

import com.example.donormanagement.dto.CreateMedicalCheckRequest;
import com.example.donormanagement.dto.MedicalCheckResponse;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.MedicalCheck;
import com.example.donormanagement.exception.ResourceNotFoundException;
import com.example.donormanagement.mapper.MedicalCheckMapper;
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
 * Medical Check Service
 */
@Service
public class MedicalCheckService {

    private static final Logger logger = LoggerFactory.getLogger(MedicalCheckService.class);

    @Autowired
    private MedicalCheckRepository medicalCheckRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private MedicalCheckMapper medicalCheckMapper;

    @Transactional
    public MedicalCheckResponse createMedicalCheck(CreateMedicalCheckRequest request) {
        logger.info("Creating medical check for donor ID: {}", request.getDonorId());

        Donor donor = donorRepository.findById(request.getDonorId())
                .orElseThrow(() -> new ResourceNotFoundException("Donor", "id", request.getDonorId()));

        MedicalCheck medicalCheck = new MedicalCheck();
        medicalCheck.setDonor(donor);
        medicalCheck.setCheckDate(request.getCheckDate());
        medicalCheck.setHemoglobin(request.getHemoglobin());
        medicalCheck.setSystolicPressure(request.getSystolicPressure());
        medicalCheck.setDiastolicPressure(request.getDiastolicPressure());
        medicalCheck.setConclusion(request.getConclusion());

        MedicalCheck savedCheck = medicalCheckRepository.save(medicalCheck);
        logger.info("Medical check created successfully with ID: {}", savedCheck.getId());

        return medicalCheckMapper.toResponse(savedCheck);
    }

    @Transactional(readOnly = true)
    public MedicalCheckResponse getMedicalCheckById(Long id) {
        MedicalCheck medicalCheck = medicalCheckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalCheck", "id", id));

        return medicalCheckMapper.toResponse(medicalCheck);
    }

    @Transactional(readOnly = true)
    public Page<MedicalCheckResponse> searchMedicalChecks(Long donorId, LocalDateTime fromDate,
            LocalDateTime toDate, Pageable pageable) {
        Page<MedicalCheck> checks = medicalCheckRepository.searchMedicalChecks(donorId, fromDate, toDate, pageable);
        return checks.map(medicalCheckMapper::toResponse);
    }
}
