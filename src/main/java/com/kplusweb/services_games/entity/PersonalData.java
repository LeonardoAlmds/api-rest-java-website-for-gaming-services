package com.kplusweb.services_games.entity;

import jakarta.persistence.*;

@Entity
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @Column(name = "phone", length = 11)
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public PersonalData() {
    }

    public PersonalData(Long id, String cpf, String phone, User user, Address address) {
        this.id = id;
        this.cpf = cpf;
        this.phone = phone;
        this.user = user;
        this.address = address;
    }
}
