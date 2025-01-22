package com.kplusweb.services_games.dtos;

import java.util.List;
import java.sql.Date;

public record ProductDTO(
        Long id,
        String name,
        String description,
        String image_url,
        Double price,
        Integer stock_quantity,
        Integer sold_quantity,
        Date posted_date,
        String status,
        Integer rating,
        Long category_id,
        Long seller_id,
        List<Long> subProducts
) {}

