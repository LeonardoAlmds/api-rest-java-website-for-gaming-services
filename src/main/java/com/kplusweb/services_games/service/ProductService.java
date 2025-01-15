package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.ProductDTO;
import com.kplusweb.services_games.entity.Product;
import com.kplusweb.services_games.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
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
                        product.getRating(),
                        product.getCategory()
                ))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

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
            product.getCategory()
        );
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setImage_url(productDTO.image_url());
        product.setPrice(productDTO.price());
        product.setStock_quantity(productDTO.stock_quantity());
        product.setSold_quantity(productDTO.sold_quantity());
        product.setPosted_date(productDTO.posted_date());
        product.setStatus(Product.Status.valueOf(productDTO.status()));
        product.setRating(productDTO.rating());
        product.setCategory(productDTO.category_id());

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getImage_url(),
                savedProduct.getPrice(),
                savedProduct.getStock_quantity(),
                savedProduct.getSold_quantity(),
                savedProduct.getPosted_date(),
                savedProduct.getStatus().toString(),
                savedProduct.getRating(),
                savedProduct.getCategory()
        );
    }
}
