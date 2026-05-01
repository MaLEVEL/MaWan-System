package com.example.donormanagement.controller;

import com.example.donormanagement.dto.ApiResponse;
import com.example.donormanagement.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Report Controller
 */
@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "Statistical reports APIs")
@PreAuthorize("hasAnyRole('ADMIN', 'REPORT_VIEWER')")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/donations/summary")
    @Operation(summary = "Get donation summary", description = "Get donation statistics by type within a date range")
    public ApiResponse<Map<String, Object>> getDonationSummary(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
        Map<String, Object> summary = reportService.getDonationSummary(fromDate, toDate);
        return ApiResponse.success(summary);
    }

    @GetMapping("/inventory/by-blood-group")
    @Operation(summary = "Get inventory by blood group", description = "Get current inventory count grouped by blood type and Rh factor")
    public ApiResponse<Map<String, Long>> getInventoryByBloodGroup() {
        Map<String, Long> summary = reportService.getInventoryByBloodGroup();
        return ApiResponse.success(summary);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Get dashboard statistics", description = "Get overall statistics for dashboard")
    public ApiResponse<Map<String, Object>> getStatistics() {
        Map<String, Object> statistics = reportService.getStatistics();
        return ApiResponse.success(statistics);
    }
}

