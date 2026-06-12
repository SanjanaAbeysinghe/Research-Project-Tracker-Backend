package com.sanjana.researchtracker.research_tracker.Entities;

import com.sanjana.researchtracker.research_tracker.Entities.memberEntity;
import com.sanjana.researchtracker.research_tracker.Entities.projectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "milestone")
public class milestoneEntity {

    @Id
    private String milestoneId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "projectId")
    private projectEntity project;

    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "memberId")
    private memberEntity createdBy;
}