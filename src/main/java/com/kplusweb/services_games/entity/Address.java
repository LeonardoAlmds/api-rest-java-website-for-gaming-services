package com.kplusweb.services_games.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adress")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "cep")
    private String cep;

    @OneToOne
    @JoinColumn(name = "personal_data_id", nullable = false)
    private PersonalData personalData;

    public Address() {
    }

    public Address(Long id, String city, String neighborhood, String street, Integer number, String complement, String cep, PersonalData personalData) {
        this.id = id;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.cep = cep;
        this.personalData = personalData;
    }   
}
