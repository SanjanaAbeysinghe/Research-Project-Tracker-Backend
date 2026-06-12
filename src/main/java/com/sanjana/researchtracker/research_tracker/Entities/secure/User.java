package com.sanjana.researchtracker.research_tracker.Entities.secure;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String fullname;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    private String role;

}
