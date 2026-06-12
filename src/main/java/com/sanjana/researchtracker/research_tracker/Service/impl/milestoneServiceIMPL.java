package com.sanjana.researchtracker.research_tracker.Service.impl;

import com.sanjana.researchtracker.research_tracker.Dao.milestoneDao;
import com.sanjana.researchtracker.research_tracker.Dto.milestoneDto;
import com.sanjana.researchtracker.research_tracker.Dto.projectDto;
import com.sanjana.researchtracker.research_tracker.Entities.milestoneEntity;
import com.sanjana.researchtracker.research_tracker.Exception.milestoneNotFoundException;
import com.sanjana.researchtracker.research_tracker.Service.milestoneService;
import com.sanjana.researchtracker.research_tracker.Util.EntityDTOConversionHandle;
import com.sanjana.researchtracker.research_tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class milestoneServiceIMPL implements milestoneService {

    private final milestoneDao milestoneDao;
    private final EntityDTOConversionHandle conversion;

    @Override
    public List<milestoneDto> getMilestonesByProject(String projectId) {
        return milestoneDao.findAll()
                .stream()
                .filter(m -> m.getProject() != null && projectId.equals(m.getProject().getProjectId()))
                .map(conversion::toMilestoneDTO)
                .collect(Collectors.toList());
    }

    @Override
    public milestoneDto addMilestone(String projectId, milestoneDto milestoneDto) {
        // Auto-generate milestone ID
        milestoneDto.setId(IDGenerator.milestoneIdGen());

        if (milestoneDto.getProject() == null) {
            milestoneDto.setProject(new projectDto());
        }
        milestoneDto.getProject().setId(projectId);

        var entity = conversion.toMilestoneEntity(milestoneDto);
        milestoneDao.save(entity);

        return conversion.toMilestoneDTO(entity);
    }


    @Override
    public milestoneDto updateMilestone(String id, milestoneDto updatedMilestone) {
        milestoneEntity existing = milestoneDao.findById(id)
                .orElseThrow(() -> new milestoneNotFoundException("Milestone not found with id: " + id));

        existing.setTitle(updatedMilestone.getTitle());
        existing.setDescription(updatedMilestone.getDescription());
        existing.setDueDate(updatedMilestone.getDueDate());
        existing.setIsCompleted(updatedMilestone.getIsCompleted());

        if (updatedMilestone.getCreatedBy() != null) {
            existing.setCreatedBy(conversion.toMilestoneEntity(updatedMilestone).getCreatedBy());
        }
        if (updatedMilestone.getProject() != null) {
            existing.setProject(conversion.toMilestoneEntity(updatedMilestone).getProject());
        }

        milestoneDao.save(existing);
        return conversion.toMilestoneDTO(existing);
    }

    @Override
    public boolean deleteMilestone(String id) {
        if (!milestoneDao.existsById(id)) {
            throw new milestoneNotFoundException("Milestone not found with id: " + id);
        }
        milestoneDao.deleteById(id);
        return true;
    }
}