package com.sanjana.researchtracker.research_tracker.Dao.secure;

import com.sanjana.researchtracker.research_tracker.Entities.secure.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
