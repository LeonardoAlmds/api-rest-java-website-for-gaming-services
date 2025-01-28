package com.kplusweb.services_games.dtos;

import java.util.List;
import java.time.LocalDateTime;

public record ProductDTO(
        Long id,
        String name,
        String description,
        String image_url,
        Double price,
        Integer stock_quantity,
        Integer sold_quantity,
        LocalDateTime posted_date,
        String status,
        List<Long> ratings,
        Long category_id,
        Long seller_id,
        List<Long> subProducts
) {}

