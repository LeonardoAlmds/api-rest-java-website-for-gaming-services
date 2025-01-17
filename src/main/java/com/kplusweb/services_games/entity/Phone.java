package com.kplusweb.services_games.entity;

import jakarta.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personal_data_id", nullable = false)
    private PersonalData personalData;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    public Phone() {
    }

    public Phone(Long id, PersonalData personalData, String number) {
        this.id = id;
        this.personalData = personalData;
        this.number = number;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
