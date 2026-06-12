package com.sanjana.researchtracker.research_tracker.Service.impl;

import com.sanjana.researchtracker.research_tracker.Dao.projectDao;
import com.sanjana.researchtracker.research_tracker.Dto.projectDto;
import com.sanjana.researchtracker.research_tracker.Dto.status;
import com.sanjana.researchtracker.research_tracker.Entities.projectEntity;
import com.sanjana.researchtracker.research_tracker.Exception.projectNotFoundException;
import com.sanjana.researchtracker.research_tracker.Service.projectService;
import com.sanjana.researchtracker.research_tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class projectServiceIMPL implements projectService {

    private final projectDao projectDao;
    private final ModelMapper modelMapper;

    @Override
    public List<projectDto> getAllProjects() {
        return projectDao.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, projectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public projectDto getProjectById(String id) {
        projectEntity entity = projectDao.findById(id)
                .orElseThrow(() -> new projectNotFoundException("Project not found with id: " + id));
        return modelMapper.map(entity, projectDto.class);
    }

    @Override
    public projectDto createProject(projectDto projectDto) {
        // Auto-generate project ID
        projectDto.setId(IDGenerator.projectIdGen());
        projectDto.setCreatedAt(LocalDateTime.now());
        projectDto.setUpdatedAt(LocalDateTime.now());

        projectEntity entity = modelMapper.map(projectDto, projectEntity.class);
        projectDao.save(entity);
        return modelMapper.map(entity, projectDto.class);
    }

    @Override
    public projectDto updateProject(String id, projectDto updatedProject) {
        projectEntity entity = projectDao.findById(id)
                .orElseThrow(() -> new projectNotFoundException("Project not found with id: " + id));

        entity.setTitle(updatedProject.getTitle());
        entity.setSummary(updatedProject.getSummary());
        entity.setTags(updatedProject.getTags());
        if (updatedProject.getPi() != null) {
            entity.setPi(modelMapper.map(updatedProject.getPi(), entity.getPi().getClass()));
        }
        entity.setStatus(updatedProject.getStatus());
        entity.setStartDate(updatedProject.getStartDate());
        entity.setEndDate(updatedProject.getEndDate());
        entity.setUpdatedAt(LocalDateTime.now());

        projectDao.save(entity);
        return modelMapper.map(entity, projectDto.class);
    }

    @Override
    public boolean updateProjectStatus(String id, status newStatus) {
        projectEntity entity = projectDao.findById(id)
                .orElseThrow(() -> new projectNotFoundException("Project not found with id: " + id));

        entity.setUpdatedAt(LocalDateTime.now());
        projectDao.save(entity);
        return true;
    }

    @Override
    public boolean deleteProject(String id) {
        if (!projectDao.existsById(id)) {
            throw new projectNotFoundException("Project not found with id: " + id);
        }
        projectDao.deleteById(id);
        return true;
    }
}