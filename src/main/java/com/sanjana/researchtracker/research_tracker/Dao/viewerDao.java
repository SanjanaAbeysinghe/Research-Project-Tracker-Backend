package com.sanjana.researchtracker.research_tracker.Dao;

import com.sanjana.researchtracker.research_tracker.Entities.viewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface viewerDao extends JpaRepository<viewerEntity, String> {
}