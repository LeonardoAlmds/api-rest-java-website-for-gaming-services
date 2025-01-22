package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.ProductDTO;
import com.kplusweb.services_games.entity.Product;
import com.kplusweb.services_games.entity.Category;
import com.kplusweb.services_games.repositories.UserRepository;
import com.kplusweb.services_games.repositories.CategoryRepository;
import com.kplusweb.services_games.repositories.ProductRepository;
import com.kplusweb.services_games.repositories.SubProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SubProductRepository subProductRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, SubProductRepository subProductRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.subProductRepository = subProductRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found " + id));
        return convertToDTO(product);
    }

    public String postProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        productRepository.save(product);
        return "Product added successfully";
    }

    public String updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found " + id));

        updateEntity(product, productDTO);
        productRepository.save(product);
        return "Product updated successfully";
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage_url(),
                product.getPrice(),
                product.getStock_quantity(),
                product.getSold_quantity(),
                product.getPosted_date(),
                product.getStatus().toString(),
                product.getRating(),
                product.getCategory().getId(),
                product.getUser().getId(),
                product.getSubProducts() != null ?
                        product.getSubProducts().stream()
                                .map(subProduct -> subProduct.getId())
                                .collect(Collectors.toList()) : null
        );
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        updateEntity(product, productDTO);
        return product;
    }

    private void updateEntity(Product product, ProductDTO productDTO) {
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setImage_url(productDTO.image_url());
        product.setPrice(productDTO.price());
        product.setStock_quantity(productDTO.stock_quantity());
        product.setSold_quantity(productDTO.sold_quantity());
        product.setPosted_date(productDTO.posted_date());
        product.setStatus(Product.Status.valueOf(productDTO.status()));
        product.setRating(productDTO.rating());
        product.setUser(userRepository.findById(productDTO.seller_id())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + productDTO.seller_id())));
        product.setSubProducts(productDTO.subProducts() != null ?
                productDTO.subProducts().stream()
                        .map(subProductId -> subProductRepository.findById(subProductId)
                                .orElseThrow(() -> new RuntimeException("SubProduct not found with ID: " + subProductId)))
                        .collect(Collectors.toList()) : null);

        // Buscar a categoria pelo ID e associá-la ao produto
        Category category = categoryRepository.findById(productDTO.category_id())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productDTO.category_id()));
        product.setCategory(category);
    }
}
