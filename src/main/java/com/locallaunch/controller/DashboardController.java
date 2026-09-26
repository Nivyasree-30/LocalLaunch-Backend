package com.locallaunch.controller;

import com.locallaunch.dto.DashboardDTO;
import com.locallaunch.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard() {

        DashboardDTO dashboard =
                dashboardService.getDashboard();

        return ResponseEntity.ok(dashboard);
    }
}