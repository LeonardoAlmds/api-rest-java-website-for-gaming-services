package com.kplusweb.services_games.controller;

import com.kplusweb.services_games.dtos.PhoneDTO;
import com.kplusweb.services_games.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PhoneDTO>> getAll() {
        List<PhoneDTO> phone = phoneService.getAllPhones();
        return ResponseEntity.ok(phone);
    }

    @GetMapping("{id}")
    public ResponseEntity<PhoneDTO> getById(@PathVariable Long id) {
        PhoneDTO phone = phoneService.getPhoneById(id);
        return ResponseEntity.ok(phone);
    }

    @GetMapping("/personal/{idPersonalData}")
    public ResponseEntity<List<PhoneDTO>> getPhonesByIdPersonalData(@PathVariable Long idPersonalData) {
        List<PhoneDTO> phones = phoneService.getPhonesByIdPersonalData(idPersonalData);
        return ResponseEntity.ok(phones);
    }

    @PostMapping("/post")
    public ResponseEntity<String> postPhone(@RequestBody PhoneDTO phoneDTO) {
        String savedPhone = phoneService.postPhone(phoneDTO);
        return ResponseEntity.ok(savedPhone);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updatePhone(@PathVariable Long id, @RequestBody PhoneDTO phoneDTO) {
        String phoneSave = phoneService.updatePhone(id, phoneDTO);
        return ResponseEntity.ok(phoneSave);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePhone(@PathVariable Long id) {
        String response = phoneService.deletePhone(id);
        if ("Phone deleted successfully".equals(response)) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}