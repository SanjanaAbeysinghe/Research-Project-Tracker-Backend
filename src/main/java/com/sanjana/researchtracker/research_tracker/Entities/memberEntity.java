package com.sanjana.researchtracker.research_tracker.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "member")
public class memberEntity {

    @Id
    private String memberId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullName;
    private String position;
    private String email;
    private LocalDateTime joinedAt;

    @ManyToOne
    @JoinColumn(name = "pi_id", referencedColumnName = "piId")
    private piEntity supervisor;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<milestoneEntity> milestonesCreated;

    @OneToMany(mappedBy = "uploadedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<documentEntity> documentsUploaded;
}