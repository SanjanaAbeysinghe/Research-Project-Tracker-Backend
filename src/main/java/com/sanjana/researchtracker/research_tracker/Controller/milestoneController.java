package com.sanjana.researchtracker.research_tracker.Controller;

import com.sanjana.researchtracker.research_tracker.Dto.milestoneDto;
import com.sanjana.researchtracker.research_tracker.Service.milestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/milestone")
@RequiredArgsConstructor
public class milestoneController {

    private final milestoneService milestoneService;

    // List milestones for a project
    @GetMapping(value = "/projects/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<milestoneDto>> getMilestonesByProject(@PathVariable String projectId) {
        List<milestoneDto> milestones = milestoneService.getMilestonesByProject(projectId);
        return ResponseEntity.ok(milestones);
    }

    // Add a new milestone
    @PostMapping(value = "/projects/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<milestoneDto> addMilestone(
            @PathVariable String projectId,
            @RequestBody milestoneDto milestoneDto
    ) {
        milestoneDto created = milestoneService.addMilestone(projectId, milestoneDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update an existing milestone
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMilestone(
            @PathVariable String id,
            @RequestBody milestoneDto updatedMilestone
    ) {
        milestoneDto updated = milestoneService.updateMilestone(id, updatedMilestone);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Milestone with ID " + id + " not found.");
        }
    }

    // Delete milestone
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMilestone(@PathVariable String id) {
        boolean deleted = milestoneService.deleteMilestone(id);
        if (deleted) {
            return ResponseEntity.ok("Milestone " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Milestone with ID " + id + " not found.");
        }
    }
}