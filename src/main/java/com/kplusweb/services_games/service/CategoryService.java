package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.CategoryDTO;
import com.kplusweb.services_games.dtos.ProductDTO;
import com.kplusweb.services_games.entity.Category;
import com.kplusweb.services_games.entity.Product;
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
                        category.getBanner_url(),
                        mapProductsToDTOs(category.getProducts())
                ))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getAcronym(),
                category.getIcon_url(),
                category.getBanner_url(),
                mapProductsToDTOs(category.getProducts())
        );
    }

    public List<CategoryDTO> getTopCategoriesByProductCount() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .sorted((category1, category2) -> Integer.compare(category2.getProducts().size(), category1.getProducts().size()))
                .limit(6)
                .map(category -> new CategoryDTO(
                        category.getId(),
                        category.getName(),
                        category.getAcronym(),
                        category.getIcon_url(),
                        category.getBanner_url(),
                        null
                ))
                .collect(Collectors.toList());
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
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));

        category.setName(categoryDTO.name());
        category.setAcronym(categoryDTO.acronym());
        category.setIcon_url(categoryDTO.icon_url());
        category.setBanner_url(categoryDTO.banner_url());

        categoryRepository.save(category);
        return "Category updated successfully";
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private List<ProductDTO> mapProductsToDTOs(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImage_url(),
                        product.getPrice(),
                        product.getStock_quantity(),
                        product.getSold_quantity(),
                        product.getPosted_date(),
                        product.getStatus().toString(),
                        product.getRatings().stream()
                                .map(rating -> rating.getId())
                                .collect(Collectors.toList()),
                        product.getCategory().getId(),
                        product.getUser().getId(),
                        product.getSubProducts().stream()
                                .map(subProduct -> subProduct.getId())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
