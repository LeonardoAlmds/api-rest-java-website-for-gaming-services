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
                        category.getIcon_url(),
                        category.getBanner_url()
                ))
                .collect(Collectors.toList());
    }

    public CategoryDTO postCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setIcon_url(categoryDTO.icon_url());
        category.setBanner_url(categoryDTO.banner_url());

        Category savedCategory = categoryRepository.save(category);

        return new CategoryDTO(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getIcon_url(),
                savedCategory.getBanner_url()
        );
    }
}

