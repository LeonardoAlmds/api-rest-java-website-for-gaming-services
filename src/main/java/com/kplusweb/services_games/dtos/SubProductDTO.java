package com.kplusweb.services_games.dtos;

public record SubProductDTO(Long id, String name, String description, Double price, Integer stockQuantity, Integer soldQuantity, Long productId) {
}
