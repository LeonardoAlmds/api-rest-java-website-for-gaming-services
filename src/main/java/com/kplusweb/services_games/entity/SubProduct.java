package com.kplusweb.services_games.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_products")
public class SubProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock_quantity;

    @Column(nullable = false)
    private Integer sold_quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;    

    public SubProduct() {}

    public SubProduct(Long id, String name, String description, Double price, Integer stock_quantity, Integer sold_quantity, Product product) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.sold_quantity = sold_quantity;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stock_quantity;
    }

    public Integer getSoldQuantity() {
        return sold_quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStockQuantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public void setSoldQuantity(Integer sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
