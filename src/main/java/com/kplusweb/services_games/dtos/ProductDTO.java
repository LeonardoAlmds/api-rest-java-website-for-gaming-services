package com.kplusweb.services_games.dtos;

import java.sql.Date;

public record ProductDTO(Long id, String name, String description, String image_url, Double price, Integer stock_quantity, Integer sold_quantity, Date posted_date, String status, Integer rating, Long category_id) {
    public ProductDTO(Long id, String name, String description, String image_url, Double price, Integer stock_quantity, Integer sold_quantity, Date posted_date, String status, Integer rating, Long category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.sold_quantity = sold_quantity;
        this.posted_date = posted_date;
        this.status = status;
        this.rating = rating;
        this.category_id = category_id;
    }
}
