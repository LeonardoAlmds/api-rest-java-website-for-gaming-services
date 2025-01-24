package com.kplusweb.services_games.controller;

import com.kplusweb.services_games.dtos.PersonalDataDTO;
import com.kplusweb.services_games.service.PersonalDataService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal_data")
public class PersonalDataController {
    private final PersonalDataService personalDataService;

    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPersonalData(@Valid @RequestBody PersonalDataDTO personalDataDTO) {
        try {
            String savedPersonalData = this.personalDataService.postPersonalData(personalDataDTO);
            return ResponseEntity.ok(savedPersonalData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPersonalData() {
        try {
            List<PersonalDataDTO> personalData = this.personalDataService.getAllPersonalData();
            return ResponseEntity.ok(personalData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getPersonalDataById(@PathVariable Long id) {
        try {
            PersonalDataDTO personalData = this.personalDataService.getPersonalDataById(id);
            return ResponseEntity.ok(personalData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePersonalData(@PathVariable Long id, @Valid @RequestBody PersonalDataDTO personalDataDTO) {
        try {
            String updatedPersonalData = this.personalDataService.updatePersonalData(id, personalDataDTO);
            return ResponseEntity.ok(updatedPersonalData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersonalData(@PathVariable Long id) {
        try {
            boolean deletedPersonalData = this.personalDataService.deletePersonalData(id);
            if (deletedPersonalData) {
                return ResponseEntity.ok("Personal data from user " + id + " deleted successfully");
            } else {
                return ResponseEntity.ok("Personal data not found: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}