package com.kplusweb.services_games.entity;

import jakarta.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4)
    private String DDI;

    @Column(nullable = false, length = 2)
    private String DDD;

    @Column(nullable = false, length = 9)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "personal_data_id", nullable = false)
    private PersonalData personalData;

    public Phone() {
    }

    public Phone(String DDI, String DDD, String phone, PersonalData personalData, Long id) {
        this.DDI = DDI;
        this.DDD = DDD;
        this.phone = phone;
        this.personalData = personalData;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDDI() {
        return DDI;
    }

    public void setDDI(String DDI) {
        this.DDI = DDI;
    }

    public String getDDD() {
        return DDD;
    }

    public void setDDD(String DDD) {
        this.DDD = DDD;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
