package com.example.donormanagement.controller;

import com.example.donormanagement.dto.ApiResponse;
import com.example.donormanagement.dto.CreateDonorRequest;
import com.example.donormanagement.dto.DonorResponse;
import com.example.donormanagement.dto.UpdateDonorRequest;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.repository.DonorRepository;
import com.example.donormanagement.service.DonorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Donor Controller
 */
@RestController
@RequestMapping("/api/donors")
@Tag(name = "Donors", description = "Donor management APIs")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorRepository donorRepository;

    @PostMapping
    @Operation(summary = "Create donor", description = "Register a new donor")
    public ApiResponse<DonorResponse> createDonor(@Valid @RequestBody CreateDonorRequest request) {
        DonorResponse response = donorService.createDonor(request);
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get donor by ID", description = "Get detailed information about a specific donor")
    public ApiResponse<DonorResponse> getDonorById(@PathVariable Long id) {
        DonorResponse response = donorService.getDonorById(id);
        return ApiResponse.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update donor", description = "Update donor information")
    public ApiResponse<DonorResponse> updateDonor(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDonorRequest request) {
        DonorResponse response = donorService.updateDonor(id, request);
        return ApiResponse.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete donor", description = "Soft delete a donor (set active=false)")
    public ApiResponse<Void> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ApiResponse.success(null);
    }

    @GetMapping
    @Operation(summary = "Search donors", description = "Search donors with filters and pagination")
    public ApiResponse<Page<DonorResponse>> searchDonors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String passportNumber,
            @RequestParam(required = false) Donor.BloodType bloodType,
            Pageable pageable) {
        Page<DonorResponse> response = donorService.searchDonors(name, passportNumber, bloodType,
                pageable);
        return ApiResponse.success(response);
    }

    @GetMapping("/export/csv")
    @Operation(summary = "Export donors to CSV", description = "Export all active donors to CSV format")
    public void exportDonorsToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"donors.csv\"");

        // Get all active donors
        List<Donor> donors = donorRepository.findByActiveTrue();

        PrintWriter writer = response.getWriter();
        writer.println(
                "ID,First Name,Last Name,Date of Birth,Gender,Passport Number,Blood Type,Phone,Email,Address");
        for (Donor donor : donors) {
            writer.println(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                    donor.getId(),
                    escapeCsv(donor.getFirstName()),
                    escapeCsv(donor.getLastName()),
                    donor.getDateOfBirth(),
                    donor.getGender(),
                    escapeCsv(donor.getPassportNumber()),
                    donor.getBloodType().getDisplayName(),
                    escapeCsv(donor.getPhone() != null ? donor.getPhone() : ""),
                    escapeCsv(donor.getEmail() != null ? donor.getEmail() : ""),
                    escapeCsv(donor.getAddress() != null ? donor.getAddress() : "")));
        }
    }

    private String escapeCsv(String value) {
        if (value == null)
            return "";
        // If the value contains commas, quotes, or newlines, wrap it in quotes
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
