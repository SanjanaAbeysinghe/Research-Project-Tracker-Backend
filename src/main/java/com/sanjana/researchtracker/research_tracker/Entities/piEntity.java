package com.sanjana.researchtracker.research_tracker.Entities;

import com.sanjana.researchtracker.research_tracker.Dto.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pi")
public class piEntity {

    @Id
    private String piId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;

}