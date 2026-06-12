package com.sanjana.researchtracker.research_tracker.Service.useRelated;

import com.sanjana.researchtracker.research_tracker.Dao.adminDao;
import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import com.sanjana.researchtracker.research_tracker.Entities.adminEntity;
import com.sanjana.researchtracker.research_tracker.Exception.userNotFoundException;
import com.sanjana.researchtracker.research_tracker.Service.AdminService;
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
public class adminServiceIMPL implements AdminService {

    private final adminDao adminDao;
    private final EntityDTOConversionHandle conversionHandling;

    @Override
    public void saveAdmin(userDto admin) {
        adminEntity entity = conversionHandling.toAdminEntity(admin);
        entity.setAdminId(IDGenerator.adminIdGen());
        adminDao.save(entity);
    }

    @Override
    public userDto getSelectedAdmin(String adminId) {
        Optional<adminEntity> foundAdmin = adminDao.findById(adminId);
        if (foundAdmin.isEmpty()) {
            throw new userNotFoundException("Admin not found with id: " + adminId);
        }
        return conversionHandling.toAdminDto(foundAdmin.get());
    }

    @Override
    public List<userDto> getAllAdmins() {
        return conversionHandling.getAdminDtoList(adminDao.findAll());
    }

    @Override
    public void updateAdmin(String adminId, userDto updatedAdmin) {
        Optional<adminEntity> foundAdmin = adminDao.findById(adminId);
        if (foundAdmin.isEmpty()) {
            throw new userNotFoundException("Admin not found with id: " + adminId);
        }
        adminEntity entity = foundAdmin.get();
        entity.setFullName(updatedAdmin.getFullname());
        entity.setUsername(updatedAdmin.getUsername());
        entity.setPassword(updatedAdmin.getPassword());
    }

    @Override
    public void deleteAdmin(String adminId) {
        Optional<adminEntity> foundAdmin = adminDao.findById(adminId);
        if (foundAdmin.isEmpty()) {
            throw new userNotFoundException("Admin not found with id: " + adminId);
        }
        adminDao.deleteById(adminId);
    }
}