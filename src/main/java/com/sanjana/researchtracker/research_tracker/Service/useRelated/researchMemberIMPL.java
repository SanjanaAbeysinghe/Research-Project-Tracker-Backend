package com.sanjana.researchtracker.research_tracker.Service.useRelated;

import com.sanjana.researchtracker.research_tracker.Dao.researchMemberDao;
import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import com.sanjana.researchtracker.research_tracker.Entities.memberEntity;
import com.sanjana.researchtracker.research_tracker.Exception.userNotFoundException;
import com.sanjana.researchtracker.research_tracker.Service.researchMemberService;
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
public class researchMemberIMPL implements researchMemberService {

    private final researchMemberDao researchMemberDao;
    private final EntityDTOConversionHandle conversionHandling;

    @Override
    public void saveResearchMember(userDto researchMember) {
        memberEntity entity = conversionHandling.toMemberEntity(researchMember);
        entity.setMemberId(IDGenerator.researchMemberIdGen());
        researchMemberDao.save(entity);
    }

    @Override
    public userDto getSelectedResearchMember(String memberId) {
        Optional<memberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new userNotFoundException("Research Member not found with id: " + memberId);
        }
        return conversionHandling.toResearchMemberDto(foundMember.get());
    }

    @Override
    public List<userDto> getAllResearchMembers() {
        return conversionHandling.getResearchMemberDtoList(researchMemberDao.findAll());
    }

    @Override
    public void updateResearchMember(String memberId, userDto updatedMember) {
        Optional<memberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new userNotFoundException("Research Member not found with id: " + memberId);
        }
        memberEntity entity = foundMember.get();
        entity.setFullName(updatedMember.getFullname());
        entity.setUsername(updatedMember.getUsername());
        entity.setPassword(updatedMember.getPassword());
    }

    @Override
    public void deleteResearchMember(String memberId) {
        Optional<memberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new userNotFoundException("Research Member not found with id: " + memberId);
        }
        researchMemberDao.deleteById(memberId);
    }
}