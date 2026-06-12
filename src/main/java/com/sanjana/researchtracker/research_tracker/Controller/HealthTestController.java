package com.sanjana.researchtracker.research_tracker.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
@RequiredArgsConstructor
public class HealthTestController {

    @GetMapping
    public String healthCheck() {
        return "Application is running successfully 🚀";
    }
}