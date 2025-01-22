package com.kplusweb.services_games.controller;

import com.kplusweb.services_games.dtos.PersonalDataDTO;
import com.kplusweb.services_games.entity.PersonalData;
import com.kplusweb.services_games.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal_data")
public class PersonalDataController {
    private final UserService userService;

    public PersonalDataController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPersonalData(@RequestBody @Valid PersonalDataDTO personalDataDTO) {
        return ResponseEntity.ok(this.userService.registerPersonalData(personalDataDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PersonalDataDTO>> getAllUsers() {
        List<PersonalDataDTO> users = this.userService.getAllPersonalData();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PersonalDataDTO> getUserById(@PathVariable Long id) {
        PersonalDataDTO user = this.userService.getPersonalDataById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePersonalData(@PathVariable Long id, @RequestBody PersonalDataDTO personalDataDTO) {
        String update = this.userService.updatePersonalData(id, personalDataDTO);
        return ResponseEntity.ok(update);
    }
}