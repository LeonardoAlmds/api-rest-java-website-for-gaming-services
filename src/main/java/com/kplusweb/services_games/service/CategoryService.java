package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.CategoryDTO;
import com.kplusweb.services_games.entity.Category;
import com.kplusweb.services_games.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryDTO(
                        category.getId(),
                        category.getName(),
                        category.getAcronym(),
                        category.getIcon_url(),
                        category.getBanner_url()
                ))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found" + id));

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getAcronym(),
                category.getIcon_url(),
                category.getBanner_url());
    }

    public String postCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setAcronym(categoryDTO.acronym());
        category.setIcon_url(categoryDTO.icon_url());
        category.setBanner_url(categoryDTO.banner_url());

        categoryRepository.save(category);        

        return "Category added successfully";
    }

    public String updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found" + id));

        category.setName(categoryDTO.name());
        category.setAcronym(categoryDTO.acronym());
        category.setIcon_url(categoryDTO.icon_url());
        category.setBanner_url(categoryDTO.banner_url());

        categoryRepository.save(category);
        return "Updated Category";
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }

        return false;
    }
}

