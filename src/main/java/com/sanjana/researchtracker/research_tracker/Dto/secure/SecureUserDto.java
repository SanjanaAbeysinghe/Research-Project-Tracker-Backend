package com.sanjana.researchtracker.research_tracker.Dto.secure;

import com.sanjana.researchtracker.research_tracker.Dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SecureUserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private Role role; // This allows the client to choose the role
    private LocalDateTime createdAt;
}
