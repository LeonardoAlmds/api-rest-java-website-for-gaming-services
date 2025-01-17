package com.kplusweb.services_games.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @OneToMany(mappedBy = "personalData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public PersonalData() {
    }

    public PersonalData(Long id, String cpf, List<Phone> phones, User user, Address address) {
        this.id = id;
        this.cpf = cpf;
        this.phones = phones;
        this.user = user;
        this.address = address;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
