package com.sanjana.researchtracker.research_tracker.Service.useRelated;

import com.sanjana.researchtracker.research_tracker.Dao.piDao;
import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import com.sanjana.researchtracker.research_tracker.Entities.piEntity;
import com.sanjana.researchtracker.research_tracker.Exception.userNotFoundException;
import com.sanjana.researchtracker.research_tracker.Service.piService;
import com.sanjana.researchtracker.research_tracker.Util.EntityDTOConversionHandle;
import com.sanjana.researchtracker.research_tracker.Util.IDGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class piServiceIMPL implements piService {

    private final piDao principalInvestigatorDao;
    private final EntityDTOConversionHandle conversionHandling;

    @Override
    public void savePrincipalInvestigator(userDto principalInvestigator) {
        piEntity entity = conversionHandling.toPIEntity(principalInvestigator);
        entity.setPiId(IDGenerator.piIdGen());
        principalInvestigatorDao.save(entity);
    }

    @Override
    public userDto getSelectedPrincipalInvestigator(String piId) {
        Optional<piEntity> foundPI = principalInvestigatorDao.findById(piId);
        if (foundPI.isEmpty()) {
            throw new userNotFoundException("Principal Investigator not found with id: " + piId);
        }
        return conversionHandling.toPrincipalInvestigatorDto(foundPI.get());
    }

    @Override
    public List<userDto> getAllPrincipalInvestigators() {
        return conversionHandling.getPrincipalInvestigatorDtoList(principalInvestigatorDao.findAll());
    }

    @Override
    public void updatePrincipalInvestigator(String piId, userDto updatedPI) {
        Optional<piEntity> foundPI = principalInvestigatorDao.findById(piId);
        if (foundPI.isEmpty()) {
            throw new userNotFoundException("Principal Investigator not found with id: " + piId);
        }
        piEntity entity = foundPI.get();
        entity.setFullName(updatedPI.getFullname());
        entity.setUsername(updatedPI.getUsername());
        entity.setPassword(updatedPI.getPassword());
    }

    @Override
    public void deletePrincipalInvestigator(String piId) {
        Optional<piEntity> foundPI = principalInvestigatorDao.findById(piId);
        if (foundPI.isEmpty()) {
            throw new userNotFoundException("Principal Investigator not found with id: " + piId);
        }
        principalInvestigatorDao.deleteById(piId);
    }
}