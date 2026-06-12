package com.sanjana.researchtracker.research_tracker.Service;

import com.sanjana.researchtracker.research_tracker.Dto.userDto;

import java.util.List;

public interface piService {

    void savePrincipalInvestigator(userDto principalInvestigator);

    userDto getSelectedPrincipalInvestigator(String piId) throws Exception;

    List<userDto> getAllPrincipalInvestigators();

    void updatePrincipalInvestigator(String piId, userDto updatedPI) throws Exception;

    void deletePrincipalInvestigator(String piId) throws Exception;
}