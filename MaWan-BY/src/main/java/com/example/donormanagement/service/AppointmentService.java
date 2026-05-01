package com.example.donormanagement.service;

import com.example.donormanagement.dto.AppointmentResponse;
import com.example.donormanagement.dto.CreateAppointmentRequest;
import com.example.donormanagement.dto.UpdateAppointmentRequest;
import com.example.donormanagement.entity.Appointment;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.exception.ResourceNotFoundException;
import com.example.donormanagement.mapper.AppointmentMapper;
import com.example.donormanagement.repository.AppointmentRepository;
import com.example.donormanagement.repository.DonorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Appointment Service
 */
@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Transactional
    public AppointmentResponse createAppointment(CreateAppointmentRequest request) {
        logger.info("Creating appointment for donor ID: {}", request.getDonorId());

        // Check for conflicting appointments
        boolean hasConflict = appointmentRepository.hasConflictingAppointments(
                request.getDonorId(),
                request.getStartTime(),
                request.getEndTime(),
                null); // excludeId is null for new appointments

        if (hasConflict) {
            throw new com.example.donormanagement.exception.BusinessException(
                    "APPOINTMENT_CONFLICT",
                    "Donor already has an appointment scheduled during this time");
        }

        Donor donor = donorRepository.findById(request.getDonorId())
                .orElseThrow(() -> new ResourceNotFoundException("Donor", "id", request.getDonorId()));

        Appointment appointment = new Appointment();
        appointment.setDonor(donor);
        appointment.setType(request.getType());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setStatus(Appointment.AppointmentStatus.PLANNED);
        appointment.setLocation(request.getLocation());
        appointment.setDoctorName(request.getDoctorName());

        Appointment savedAppointment = appointmentRepository.save(appointment);
        logger.info("Appointment created successfully with ID: {}", savedAppointment.getId());

        return appointmentMapper.toResponse(savedAppointment);
    }

    @Transactional
    public AppointmentResponse updateAppointment(Long id, UpdateAppointmentRequest request) {
        logger.info("Updating appointment with ID: {}", id);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));

        if (request.getStartTime() != null) {
            appointment.setStartTime(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            appointment.setEndTime(request.getEndTime());
        }
        if (request.getStatus() != null) {
            appointment.setStatus(request.getStatus());
        }
        if (request.getLocation() != null) {
            appointment.setLocation(request.getLocation());
        }
        if (request.getDoctorName() != null) {
            appointment.setDoctorName(request.getDoctorName());
        }

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        logger.info("Appointment updated successfully: {}", id);

        return appointmentMapper.toResponse(updatedAppointment);
    }

    @Transactional(readOnly = true)
    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));

        return appointmentMapper.toResponse(appointment);
    }

    @Transactional(readOnly = true)
    public Page<AppointmentResponse> searchAppointments(Long donorId, LocalDateTime from,
            LocalDateTime to,
            Appointment.AppointmentStatus status,
            Pageable pageable) {
        Page<Appointment> appointments = appointmentRepository.searchAppointments(donorId, from, to, status, pageable);
        return appointments.map(appointmentMapper::toResponse);
    }
}
