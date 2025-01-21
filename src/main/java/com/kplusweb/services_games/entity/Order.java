package com.kplusweb.services_games.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "sub_product_id")
    private SubProduct sub_product;    

    @Column(nullable = false)
    private Date order_date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order() {}

    public Order(Long id, User user, Product product, SubProduct sub_product, Date order_date, Status status) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.sub_product = sub_product;
        this.order_date = order_date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public SubProduct getSubProduct() {
        return sub_product;
    }

    public Date getOrderDate() {
        return order_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSubProduct(SubProduct sub_product) {
        this.sub_product = sub_product;
    }

    public void setOrderDate(Date order_date) {
        this.order_date = order_date;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        PENDING,
        DELIVERED,
        CANCELED
    }
}
