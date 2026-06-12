package com.sanjana.researchtracker.research_tracker.Service;

import com.sanjana.researchtracker.research_tracker.Dto.documentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface documentService {

    void uploadDocument(String title,
                        String description,
                        MultipartFile file,
                        String uploadedAt,
                        String projectId,
                        String uploadedBy) throws IOException;

    void deleteDocument(String documentId) throws Exception;


    documentDto getSelectedDocument(String documentId) throws Exception;


}
