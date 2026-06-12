package com.sanjana.researchtracker.research_tracker.Util;

import com.sanjana.researchtracker.research_tracker.Dao.projectDao;
import com.sanjana.researchtracker.research_tracker.Dao.researchMemberDao;
import com.sanjana.researchtracker.research_tracker.Dto.documentDto;
import com.sanjana.researchtracker.research_tracker.Dto.milestoneDto;
import com.sanjana.researchtracker.research_tracker.Dto.projectDto;
import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import com.sanjana.researchtracker.research_tracker.Entities.*;
import com.sanjana.researchtracker.research_tracker.Entities.piEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityDTOConversionHandle {
    private final ModelMapper modelMapper;
    private final projectDao projectDao;
    private final researchMemberDao researchMemberDao;
    private piEntity piEntity;

    public userDto toPrincipalInvestigatorDto(piEntity piEntity) {
        return modelMapper.map(piEntity, userDto.class);
    }

    public piEntity toPIEntity(userDto userDto) {
        return modelMapper.map(userDto, piEntity.class);
    }

    public List<userDto> getPrincipalInvestigatorDtoList(List<piEntity> piEntityList) {
        return modelMapper.map(piEntityList, new TypeToken<List<userDto>>() {}.getType());
    }

    //  Research Members
    public userDto toResearchMemberDto(memberEntity memberEntity) {
        return modelMapper.map(memberEntity, userDto.class);
    }

    public memberEntity toMemberEntity(userDto userDto) {
        return modelMapper.map(userDto, memberEntity.class);
    }

    public List<userDto> getResearchMemberDtoList(List<memberEntity> memberEntityList) {
        return modelMapper.map(memberEntityList, new TypeToken<List<userDto>>() {}.getType());
    }

    //  Admin
    public userDto toAdminDto(adminEntity adminEntity) {
        return modelMapper.map(adminEntity, userDto.class);
    }

    public adminEntity toAdminEntity(userDto userDto) {
        return modelMapper.map(userDto, adminEntity.class);
    }

    public List<userDto> getAdminDtoList(List<adminEntity> adminEntityList) {
        return modelMapper.map(adminEntityList, new TypeToken<List<userDto>>() {}.getType());
    }

    //Document

    public documentDto toDocumentDTO(documentEntity documentEntity) {
        documentDto dto = new documentDto();

        dto.setId(documentEntity.getDocumentId());
        dto.setTitle(documentEntity.getTitle());
        dto.setDescription(documentEntity.getDescription());
        dto.setUrlOrPath(Base64.getEncoder().encodeToString(documentEntity.getFilePath()));
        dto.setUploadedAt(String.valueOf(documentEntity.getUploadedAt()));

        if (documentEntity.getProject() != null) {
            projectEntity projectEntity = documentEntity.getProject();
            projectDto projectDto = new projectDto();
            projectDto.setId(projectEntity.getProjectId());
            projectDto.setTitle(projectEntity.getTitle());
            projectDto.setStatus(projectEntity.getStatus());
            dto.setProject(projectDto);
        }

        if (documentEntity.getUploadedBy() != null) {
            memberEntity memberEntity = documentEntity.getUploadedBy();
            userDto userDto = new userDto();
            userDto.setId(memberEntity.getMemberId());
            userDto.setFullname(memberEntity.getFullName());
            userDto.setUsername(memberEntity.getUsername());
            dto.setUploadedBy(userDto);
        }

        return dto;
    }


    public documentEntity toDocumentEntity(documentDto documentDTO) {
        documentEntity entity = new documentEntity();

        entity.setDocumentId(documentDTO.getId());
        entity.setTitle(documentDTO.getTitle());
        entity.setDescription(documentDTO.getDescription());
        entity.setFilePath(Base64.getDecoder().decode(documentDTO.getUrlOrPath()));

        entity.setUploadedAt(LocalDateTime.parse(documentDTO.getUploadedAt()));

        if (documentDTO.getProject() != null && documentDTO.getProject().getId() != null) {
            projectEntity selectedProject = projectDao.findById(documentDTO.getProject().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Project not found with id: " + documentDTO.getProject().getId()));
            entity.setProject(selectedProject);
        }

        if (documentDTO.getUploadedBy() != null && documentDTO.getUploadedBy().getId() != null) {
            memberEntity uploadedBy = researchMemberDao.findById(documentDTO.getUploadedBy().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Member not found with id: " + documentDTO.getUploadedBy().getId()));
            entity.setUploadedBy(uploadedBy);
        }

        return entity;
    }


    public List<documentDto> toDocumentDTOList(List<documentEntity> entities) {
        return entities.stream().map(this::toDocumentDTO).toList();
    }

    public List<documentEntity> toDocumentEntityList(List<documentDto> dtos) {
        return dtos.stream().map(this::toDocumentEntity).toList();
    }


    //   Project
    public projectDto toProjectDTO(projectEntity projectEntity) {
        projectDto dto = new projectDto();
        dto.setId(projectEntity.getProjectId());
        dto.setTitle(projectEntity.getTitle());
        dto.setSummary(projectEntity.getSummary());
        dto.setStatus(projectEntity.getStatus());
        dto.setTags(projectEntity.getTags());
        dto.setStartDate(projectEntity.getStartDate());
        dto.setEndDate(projectEntity.getEndDate());
        dto.setCreatedAt(projectEntity.getCreatedAt());
        dto.setUpdatedAt(projectEntity.getUpdatedAt());

        if (projectEntity.getPi() != null) {
            dto.setPi(modelMapper.map(projectEntity.getPi(), userDto.class));
        }

        return dto;
    }

    public projectEntity toProjectEntity(projectDto dto) {
        projectEntity entity = new projectEntity();
        entity.setProjectId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setSummary(dto.getSummary());
        entity.setStatus(dto.getStatus());
        entity.setTags(dto.getTags());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getPi() != null) {
            piEntity = modelMapper.map(dto.getPi(), piEntity.class);
            entity.setPi(piEntity);
        }

        return entity;
    }

    public List<projectDto> toProjectDTOList(List<projectEntity> projectEntities) {
        return projectEntities.stream().map(this::toProjectDTO).toList();
    }

    public List<projectEntity> toProjectEntityList(List<projectDto> projectDTOs) {
        return projectDTOs.stream().map(this::toProjectEntity).toList();
    }


    // Milestone

    public milestoneDto toMilestoneDTO(milestoneEntity entity) {
        milestoneDto dto = new milestoneDto();

        dto.setId(entity.getMilestoneId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDueDate(entity.getDueDate());
        dto.setIsCompleted(entity.getIsCompleted());

        if (entity.getProject() != null) {
            projectEntity projectEntity = entity.getProject();
            projectDto projectDto = new projectDto();
            projectDto.setId(projectEntity.getProjectId());
            projectDto.setTitle(projectEntity.getTitle());
            projectDto.setStatus(projectEntity.getStatus());
            dto.setProject(projectDto);
        }

        if (entity.getCreatedBy() != null) {
            memberEntity memberEntity = entity.getCreatedBy();
            userDto userDto = new userDto();
            userDto.setId(memberEntity.getMemberId());
            userDto.setFullname(memberEntity.getFullName());
            userDto.setUsername(memberEntity.getUsername());
            dto.setCreatedBy(userDto);
        }

        return dto;
    }

    public milestoneEntity toMilestoneEntity(milestoneDto dto) {
        milestoneEntity entity = new milestoneEntity();

        entity.setMilestoneId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDueDate(dto.getDueDate());
        entity.setIsCompleted(dto.getIsCompleted());


        if (dto.getProject() != null && dto.getProject().getId() != null) {
            projectEntity projectEntity = projectDao.findById(dto.getProject().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Project not found with id: " + dto.getProject().getId()));
            entity.setProject(projectEntity);
        }

        if (dto.getCreatedBy() != null && dto.getCreatedBy().getId() != null) {
            memberEntity memberEntity = researchMemberDao.findById(dto.getCreatedBy().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Member not found with id: " + dto.getCreatedBy().getId()));
            entity.setCreatedBy(memberEntity);
        }

        return entity;
    }

    public List<milestoneDto> toMilestoneDTOList(List<milestoneEntity> entities) {
        return entities.stream().map(this::toMilestoneDTO).toList();
    }

    public List<milestoneEntity> toMilestoneEntityList(List<milestoneDto> dtos) {
        return dtos.stream().map(this::toMilestoneEntity).toList();
    }

}