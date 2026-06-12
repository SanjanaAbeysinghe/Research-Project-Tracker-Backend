package com.sanjana.researchtracker.research_tracker.Controller;

import com.sanjana.researchtracker.research_tracker.Dto.documentDto;
import com.sanjana.researchtracker.research_tracker.Exception.documentNotFoundException;
import com.sanjana.researchtracker.research_tracker.Service.documentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/document")
@RequiredArgsConstructor
public class documentController {

    private final documentService documentService;

    // Upload a new document
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDocument(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) String uploadedAt,
            @RequestParam String projectId,
            @RequestParam String uploadedBy
    ) {
        try {
            documentService.uploadDocument(title, description, file, uploadedAt, projectId, uploadedBy);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException | documentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  Get document by ID
    @GetMapping("{docId}")
    public ResponseEntity<documentDto> getSelectedDocument(@PathVariable String docId) {
        try {
            return new ResponseEntity<>(documentService.getSelectedDocument(docId), HttpStatus.OK);
        } catch (documentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete document
    @DeleteMapping("{docId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable("docId") String documentId) {
        try {
            documentService.deleteDocument(documentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (documentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}