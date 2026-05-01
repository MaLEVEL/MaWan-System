package com.example.donormanagement.controller;

import com.example.donormanagement.dto.ApiResponse;
import com.example.donormanagement.dto.AppointmentResponse;
import com.example.donormanagement.dto.CreateAppointmentRequest;
import com.example.donormanagement.dto.UpdateAppointmentRequest;
import com.example.donormanagement.entity.Appointment;
import com.example.donormanagement.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Appointment Controller
 */
@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments", description = "Appointment management APIs")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Operation(summary = "Create appointment", description = "Create a new appointment for a donor")
    public ApiResponse<AppointmentResponse> createAppointment(@Valid @RequestBody CreateAppointmentRequest request) {
        AppointmentResponse response = appointmentService.createAppointment(request);
        return ApiResponse.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update appointment", description = "Update appointment information or status")
    public ApiResponse<AppointmentResponse> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppointmentRequest request) {
        AppointmentResponse response = appointmentService.updateAppointment(id, request);
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID", description = "Get detailed information about a specific appointment")
    public ApiResponse<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        AppointmentResponse response = appointmentService.getAppointmentById(id);
        return ApiResponse.success(response);
    }

    @GetMapping
    @Operation(summary = "Search appointments", description = "Search appointments with filters and pagination")
    public ApiResponse<Page<AppointmentResponse>> searchAppointments(
            @RequestParam(required = false) Long donorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(required = false) Appointment.AppointmentStatus status,
            Pageable pageable) {
        Page<AppointmentResponse> response = appointmentService.searchAppointments(donorId, from, to, status, pageable);
        return ApiResponse.success(response);
    }
}

