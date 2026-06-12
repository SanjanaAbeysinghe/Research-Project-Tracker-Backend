package com.sanjana.researchtracker.research_tracker.Service;

import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    void saveAdmin(userDto admin);

    userDto getSelectedAdmin(String adminId) throws Exception;

    List<userDto> getAllAdmins();

    void updateAdmin(String adminId, userDto updatedAdmin) throws Exception;

    void deleteAdmin(String adminId) throws Exception;
}