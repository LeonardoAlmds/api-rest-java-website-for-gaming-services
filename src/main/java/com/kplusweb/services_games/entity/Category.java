package com.kplusweb.services_games.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String acronym;

    @Column(nullable = false)
    private String icon_url;

    @Column(nullable = false)
    private String banner_url;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    // Construtores
    public Category() {}

    public Category(Long id, String name, String acronym, String icon_url, String banner_url, List<Product> products) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.icon_url = icon_url;
        this.banner_url = banner_url;
        this.products = products;
    }

    // Getters e Setters
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
