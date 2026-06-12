package com.sanjana.researchtracker.research_tracker.Controller.common;

import com.sanjana.researchtracker.research_tracker.Dto.userDto;
import com.sanjana.researchtracker.research_tracker.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class adminController {

    private final AdminService adminService;

    //  Save Admin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveAdmin(@RequestBody userDto userDTO) {
        adminService.saveAdmin(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //  Get Selected Admin
    @GetMapping(value = "{adminId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<userDto> getSelectedAdmin(@PathVariable String adminId) {
        try {
            userDto selectedAdmin = adminService.getSelectedAdmin(adminId);
            return new ResponseEntity<>(selectedAdmin, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  Get All Admins
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<userDto>> getAllAdmins() {
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    //  Update Admin
    @PatchMapping
    public ResponseEntity<Void> updateAdmin(@RequestParam("id") String adminId,
                                            @RequestBody userDto updatedAdmin) {
        try {
            adminService.updateAdmin(adminId, updatedAdmin);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //  Delete Admin
    @DeleteMapping("{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String adminId) {
        try {
            adminService.deleteAdmin(adminId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}