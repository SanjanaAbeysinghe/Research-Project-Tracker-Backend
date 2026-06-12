package com.sanjana.researchtracker.research_tracker.Service;

import com.sanjana.researchtracker.research_tracker.Dto.projectDto;
import com.sanjana.researchtracker.research_tracker.Dto.status;

import java.util.List;

public interface projectService {

    List<projectDto> getAllProjects();

    projectDto getProjectById(String id);

    projectDto createProject(projectDto projectDto);

    projectDto updateProject(String id, projectDto updatedProject);

    boolean updateProjectStatus(String id, status newStatus);

    boolean deleteProject(String id);
}
