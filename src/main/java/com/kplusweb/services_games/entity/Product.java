package com.kplusweb.services_games.entity;

import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image_url;

    @Column
    private Double price;

    @Column(nullable = false)
    private Integer stock_quantity;

    @Column(nullable = false)
    private Integer sold_quantity;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime posted_date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User user;  
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubProduct> subProducts;    

    public Product() {}

    public Product(Long id, String name, String description, String image_url, Double price, Integer stock_quantity, Integer sold_quantity, Status status, List<Rating> ratings, Category category, User user, List<SubProduct> subProducts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.sold_quantity = sold_quantity;
        this.status = status;
        this.ratings = ratings;
        this.category = category;
        this.user = user;
        this.subProducts = subProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Integer getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(Integer sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public LocalDateTime getPosted_date() {
        return posted_date;
    }

    public void setPosted_date(LocalDateTime posted_date) {
        this.posted_date = posted_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SubProduct> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<SubProduct> subProducts) {
        this.subProducts = subProducts;
    }

    public enum Status {
        AVAILABLE,
        OUT_OF_STOCK,
        DISCONTINUED
    }
}
