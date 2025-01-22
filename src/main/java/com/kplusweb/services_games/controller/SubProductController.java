package com.kplusweb.services_games.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kplusweb.services_games.dtos.SubProductDTO;
import com.kplusweb.services_games.exceptions.ResourceNotFoundException;
import com.kplusweb.services_games.service.SubProductService;

@RestController
@RequestMapping("/sub-products")
public class SubProductController {
    
    private final SubProductService subProductService;

    public SubProductController(SubProductService subProductService) {
        this.subProductService = subProductService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllSubProducts() {
        try {
            List<SubProductDTO> subProducts = subProductService.getAllSubProducts();
            return ResponseEntity.ok(subProducts);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubProductById(@PathVariable Long id) {
        try {
            SubProductDTO subProduct = subProductService.getSubProductById(id);
            return ResponseEntity.ok(subProduct);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postSubProduct(@RequestBody SubProductDTO subProduct) {
        try {
            String savedSubProduct = subProductService.postSubProduct(subProduct);
            return ResponseEntity.ok(savedSubProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSubProduct(@PathVariable Long id, @RequestBody SubProductDTO subProduct) {
        try {
            String updatedSubProduct = subProductService.updateSubProduct(id, subProduct);
            return ResponseEntity.ok(updatedSubProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubProduct(@PathVariable Long id) {
        try {
            String deletedSubProduct = subProductService.deleteSubProduct(id);
            return ResponseEntity.ok(deletedSubProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }
}