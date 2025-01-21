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

    @PostMapping("/post")
    public ResponseEntity<String> registerPersonalData(@RequestBody @Valid PersonalDataDTO personalDataDTO) {
        return ResponseEntity.ok(this.userService.registerPersonalData(personalDataDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PersonalData>> getAllUsers() {
        List<PersonalData> users = this.userService.getAllPersonalData();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalData> getUserById(@PathVariable Long id) {
        PersonalData user = this.userService.getPersonalDataById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-by-cpf/{cpf}")
    public ResponseEntity<PersonalData> getUserByCpf(@PathVariable String cpf) {
        PersonalData user = this.userService.getPersonalDataByCpf(cpf);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<PersonalData> getUserByName(@PathVariable String name) {
        PersonalData user = this.userService.getPersonalDataByName(name);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePersonalData(@PathVariable Long id, @RequestBody PersonalDataDTO personalDataDTO) {
        String update = this.userService.updatePersonalData(id, personalDataDTO);
        return ResponseEntity.ok(update);
    }
}