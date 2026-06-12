package com.sanjana.researchtracker.research_tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class userDto implements Serializable {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private Role role;
    private LocalDateTime createdAt;
}