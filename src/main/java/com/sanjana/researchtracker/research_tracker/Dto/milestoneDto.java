package com.sanjana.researchtracker.research_tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class milestoneDto implements Serializable {
    private String id;
    private projectDto project;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean isCompleted;
    private userDto createdBy;
}