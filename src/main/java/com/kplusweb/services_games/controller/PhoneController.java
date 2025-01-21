package com.kplusweb.services_games.controller;

import com.kplusweb.services_games.dtos.PhoneDTO;
import com.kplusweb.services_games.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<?> getAll() {
        try {
            List<PhoneDTO> phones = phoneService.getAllPhones();
            return ResponseEntity.ok(phones);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            PhoneDTO phone = phoneService.getPhoneById(id);
            return ResponseEntity.ok(phone);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/personal/{idPersonalData}")
    public ResponseEntity<?> getPhonesByIdPersonalData(@PathVariable Long idPersonalData) {
        try {
            List<PhoneDTO> phones = phoneService.getPhonesByIdPersonalData(idPersonalData);
            return ResponseEntity.ok(phones);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postPhone(@RequestBody PhoneDTO phoneDTO) {
        try {
            String savedPhone = phoneService.postPhone(phoneDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPhone);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePhone(@PathVariable Long id, @RequestBody PhoneDTO phoneDTO) {
        try {
            String updatedPhone = phoneService.updatePhone(id, phoneDTO);
            return ResponseEntity.ok(updatedPhone);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePhone(@PathVariable Long id) {
        try {
            String response = phoneService.deletePhone(id);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }
}
