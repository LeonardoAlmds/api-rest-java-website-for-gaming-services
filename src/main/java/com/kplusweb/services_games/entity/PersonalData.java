package com.kplusweb.services_games.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(mappedBy = "personalData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PersonalData() {
    }

    public PersonalData(Long id, String name, String cpf, Date birthDate, List<Phone> phones, Address address, User user) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.phones = phones;
        this.address = address;
        this.user = user;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PersonalData orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
