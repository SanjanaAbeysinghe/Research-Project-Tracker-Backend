package com.sanjana.researchtracker.research_tracker.Controller.common;

import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import com.sanjana.researchtracker.research_tracker.Service.piService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/principalInvestigator")
@RequiredArgsConstructor
public class piController {

    private final piService principalInvestigatorService;

    //  Save Principal Investigator
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savePrincipalInvestigator(@RequestBody userDto userDTO) {
        principalInvestigatorService.savePrincipalInvestigator(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //  Get Selected Principal Investigator
    @GetMapping(value = "{piId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<userDto> getSelectedPrincipalInvestigator(@PathVariable String piId) {
        try {
            userDto selectedPI = principalInvestigatorService.getSelectedPrincipalInvestigator(piId);
            return new ResponseEntity<>(selectedPI, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  Get All Principal Investigators
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<userDto>> getAllPrincipalInvestigators() {
        return new ResponseEntity<>(principalInvestigatorService.getAllPrincipalInvestigators(), HttpStatus.OK);
    }

    // Update Principal Investigator
    @PatchMapping
    public ResponseEntity<Void> updatePrincipalInvestigator(@RequestParam("id") String piId,
                                                            @RequestBody userDto toBeUpdatedPI) {
        try {
            principalInvestigatorService.updatePrincipalInvestigator(piId, toBeUpdatedPI);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //  Delete Principal Investigator
    @DeleteMapping("{piId}")
    public ResponseEntity<Void> deletePrincipalInvestigator(@PathVariable String piId) {
        try {
            principalInvestigatorService.deletePrincipalInvestigator(piId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}