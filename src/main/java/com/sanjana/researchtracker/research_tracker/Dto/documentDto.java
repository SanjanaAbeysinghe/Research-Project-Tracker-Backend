package com.sanjana.researchtracker.research_tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class documentDto implements Serializable {
    private String id;
    private projectDto project;
    private String title;
    private String description;
    private String urlOrPath;
    private userDto uploadedBy;
    private String uploadedAt;
}