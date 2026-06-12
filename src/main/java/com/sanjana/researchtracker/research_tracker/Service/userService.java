package com.sanjana.researchtracker.research_tracker.Service;

import com.sanjana.researchtracker.research_tracker.Dto.secure.SecureUserDto;

import java.util.List;

public interface userService {
    void saveUser(SecureUserDto userDto);
    SecureUserDto getSelectedUser(String userId) throws Exception;
    List<SecureUserDto> getAllUsers();
    void deleteUser(String userId) throws Exception;
}

