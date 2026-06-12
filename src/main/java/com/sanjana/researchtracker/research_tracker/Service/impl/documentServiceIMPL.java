package com.sanjana.researchtracker.research_tracker.Service.impl;

import com.sanjana.researchtracker.research_tracker.Dao.documentDao;
import com.sanjana.researchtracker.research_tracker.Dao.projectDao;
import com.sanjana.researchtracker.research_tracker.Dto.documentDto;
import com.sanjana.researchtracker.research_tracker.Dto.projectDto;
import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import com.sanjana.researchtracker.research_tracker.Entities.documentEntity;
import com.sanjana.researchtracker.research_tracker.Exception.documentNotFoundException;
import com.sanjana.researchtracker.research_tracker.Service.documentService;
import com.sanjana.researchtracker.research_tracker.Util.EntityDTOConversionHandle;
import com.sanjana.researchtracker.research_tracker.Util.IDGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class documentServiceIMPL implements documentService {

    private final documentDao documentDao;
    private final projectDao projectDao;
    private final EntityDTOConversionHandle conversion;

    @Override
    public void uploadDocument(String title, String description, MultipartFile file,
                               String uploadedAt, String projectId, String uploadedBy) throws IOException {

        byte[] fileBytes = file.getBytes();
        String base64File = Base64.getEncoder().encodeToString(fileBytes);

        String uploadTime = uploadedAt != null ? uploadedAt : LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);


        var documentDto = new documentDto();
        documentDto.setTitle(title);
        documentDto.setDescription(description);
        documentDto.setUrlOrPath(base64File);
        documentDto.setUploadedAt(uploadTime);
        projectDto project = new projectDto();
        project.setId(projectId);
        documentDto.setProject(project);

        userDto user = new userDto();
        user.setId(uploadedBy);
        documentDto.setUploadedBy(user);


        var documentEntity = conversion.toDocumentEntity(documentDto);
        documentEntity.setDocumentId(IDGenerator.documentIdGen());
        documentDao.save(documentEntity);
    }

    @Override
    public void deleteDocument(String documentId) throws Exception {
        Optional<documentEntity> found = documentDao.findById(documentId);
        if (found.isEmpty()) {
            throw new documentNotFoundException("Document not found");
        }
        documentDao.deleteById(documentId);
    }



    @Override
    public documentDto getSelectedDocument(String documentId) throws Exception {
        Optional<documentEntity> found = documentDao.findById(documentId);
        if (found.isEmpty()) {
            throw new documentNotFoundException("Document not found");
        }
        return conversion.toDocumentDTO(found.get());
    }


}