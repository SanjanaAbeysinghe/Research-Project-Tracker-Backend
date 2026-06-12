package com.sanjana.researchtracker.research_tracker.Service;

import com.sanjana.researchtracker.research_tracker.Dto.milestoneDto;

import java.util.List;

public interface milestoneService {

    List<milestoneDto> getMilestonesByProject(String projectId);

    milestoneDto addMilestone(String projectId, milestoneDto milestoneDto);

    milestoneDto updateMilestone(String id, milestoneDto updatedMilestone);

    boolean deleteMilestone(String id);
}
