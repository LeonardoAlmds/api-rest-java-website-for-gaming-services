package com.kplusweb.services_games.controller;

import com.kplusweb.services_games.dtos.PersonalDataDTO;
import com.kplusweb.services_games.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
