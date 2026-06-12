package com.sanjana.researchtracker.research_tracker.Controller;

import com.sanjana.researchtracker.research_tracker.Dto.projectDto;
import com.sanjana.researchtracker.research_tracker.Dto.status;
import com.sanjana.researchtracker.research_tracker.Service.projectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class projectController {

    private final projectService projectService;

    // Constructor Injection
    public projectController(projectService projectService) {
        this.projectService = projectService;
    }

    // Get all projects
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<projectDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // Get project by ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProjectById(@PathVariable String id) {
        projectDto project = projectService.getProjectById(id);
        return (project != null)
                ? ResponseEntity.ok(project)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body("Project with ID " + id + " not found.");
    }

    // Create new project
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<projectDto> createProject(@RequestBody projectDto projectDto) {
        return new ResponseEntity<>(projectService.createProject(projectDto), HttpStatus.CREATED);
    }

    // Update full project
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProject(@PathVariable String id, @RequestBody projectDto updatedProject) {
        projectDto updated = projectService.updateProject(id, updatedProject);
        return (updated != null)
                ? ResponseEntity.ok(updated)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body("Project with ID " + id + " not found.");
    }

    // Update project status
    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProjectStatus(@PathVariable String id, @RequestBody status newStatus) {
        boolean updated = projectService.updateProjectStatus(id, newStatus);
        return (updated)
                ? ResponseEntity.ok("Project " + id + " status updated to " + newStatus)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body("Project with ID " + id + " not found.");
    }

    // Delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable String id) {
        boolean deleted = projectService.deleteProject(id);
        return (deleted)
                ? ResponseEntity.ok("Project " + id + " deleted successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body("Project with ID " + id + " not found.");
    }
}