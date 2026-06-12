package com.sanjana.researchtracker.research_tracker.Entities;

import com.sanjana.researchtracker.research_tracker.Entities.projectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "document")
public class documentEntity {

    @Id
    private String documentId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "projectId")
    private projectEntity project;

    private String title;
    private String description;
    @Column(name="file_path", columnDefinition="LONGBLOB")
    private byte[] filePath;


    @ManyToOne
    @JoinColumn(name = "uploaded_by", referencedColumnName = "memberId")
    private memberEntity uploadedBy;

    private LocalDateTime uploadedAt;
}