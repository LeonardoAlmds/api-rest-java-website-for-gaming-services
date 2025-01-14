package com.kplusweb.services_games.controller;

import com.kplusweb.services_games.dtos.CategoryDTO;
import com.kplusweb.services_games.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping("/post")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        String savedCategory = categoryService.postCategory(categoryDTO);
        return ResponseEntity.ok(savedCategory);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
       categoryService.updateCategory(id, categoryDTO);
       return ResponseEntity.ok("Category updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
