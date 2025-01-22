package com.kplusweb.services_games.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kplusweb.services_games.entity.Product;
import com.kplusweb.services_games.entity.SubProduct;
import com.kplusweb.services_games.exceptions.ResourceNotFoundException;
import com.kplusweb.services_games.dtos.SubProductDTO;
import com.kplusweb.services_games.repositories.ProductRepository;
import com.kplusweb.services_games.repositories.SubProductRepository;

@Service
public class SubProductService {
    private final SubProductRepository subProductRepository;
    private final ProductRepository productRepository;

    public SubProductService(SubProductRepository subProductRepository, ProductRepository productRepository) {
        this.subProductRepository = subProductRepository;
        this.productRepository = productRepository;
    }

    public List<SubProductDTO> getAllSubProducts() {
        List<SubProduct> subProducts = subProductRepository.findAll();

        if (subProducts.isEmpty()) {
            throw new ResourceNotFoundException("No sub products found.");
        }

        return subProducts.stream()
                .map(subProduct -> new SubProductDTO(
                        subProduct.getId(),
                        subProduct.getName(),
                        subProduct.getDescription(),
                        subProduct.getPrice(),
                        subProduct.getSoldQuantity(),
                        subProduct.getStockQuantity(),
                        subProduct.getProduct().getId()))
                .collect(Collectors.toList());
    }

    public SubProductDTO getSubProductById(Long id) {
        SubProduct subProduct = subProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No sub product found with id: " + id));

        return new SubProductDTO(
                subProduct.getId(),
                subProduct.getName(),
                subProduct.getDescription(),
                subProduct.getPrice(),
                subProduct.getSoldQuantity(),
                subProduct.getStockQuantity(),
                subProduct.getProduct().getId());
    }

    public String postSubProduct(SubProductDTO subProductDTO) {
        Product product = productRepository.findById(subProductDTO.productId())
                .orElseThrow(() -> new ResourceNotFoundException("No product found with id: " + subProductDTO.productId()));

        SubProduct subProduct = new SubProduct();
            subProduct.setName(subProductDTO.name());
            subProduct.setDescription(subProductDTO.description());
            subProduct.setPrice(subProductDTO.price());
            subProduct.setSoldQuantity(subProductDTO.soldQuantity());
            subProduct.setStockQuantity(subProductDTO.stockQuantity());
            subProduct.setProduct(product);

        subProductRepository.save(subProduct);
        return "Sub product created successfully.";

    }

    public String updateSubProduct(Long id, SubProductDTO subProductDTO) {
        SubProduct subProduct = subProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No sub product found with id: " + subProductDTO.id()));

        Product product = productRepository.findById(subProductDTO.productId())
                .orElseThrow(() -> new ResourceNotFoundException("No product found with id: " + subProductDTO.productId()));

        subProduct.setName(subProductDTO.name());
        subProduct.setDescription(subProductDTO.description());
        subProduct.setPrice(subProductDTO.price());
        subProduct.setSoldQuantity(subProductDTO.soldQuantity());
        subProduct.setStockQuantity(subProductDTO.stockQuantity());
        subProduct.setProduct(product);

        subProductRepository.save(subProduct);
        return "Sub product updated successfully.";
    }

    public String deleteSubProduct(Long id) {
        SubProduct subProduct = subProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No sub product found with id: " + id));

        subProductRepository.delete(subProduct);
        return "Sub product deleted successfully.";
    }
}
