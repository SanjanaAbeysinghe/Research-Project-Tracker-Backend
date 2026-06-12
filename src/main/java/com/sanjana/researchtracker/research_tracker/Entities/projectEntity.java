package com.sanjana.researchtracker.research_tracker.Entities;

import com.sanjana.researchtracker.research_tracker.Dto.status;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "project")
public class projectEntity {

    @Id
    private String projectId;

    private String title;
    private String summary;

    @Enumerated(EnumType.STRING)
    private status status;


    @ManyToOne
    @JoinColumn(name = "pi_id", referencedColumnName = "piId")
    private piEntity pi;

    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}