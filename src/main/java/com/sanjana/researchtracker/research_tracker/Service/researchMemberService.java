package com.sanjana.researchtracker.research_tracker.Service;

import com.sanjana.researchtracker.research_tracker.Dto.userDto;

import java.util.List;

public interface researchMemberService {

    void saveResearchMember(userDto researchMember);

    userDto getSelectedResearchMember(String memberId) throws Exception;

    List<userDto> getAllResearchMembers();

    void updateResearchMember(String memberId, userDto updatedMember) throws Exception;

    void deleteResearchMember(String memberId) throws Exception;
}