package com.kplusweb.services_games.dtos;

public record CategoryDTO(Long id, String name, String acronym, String icon_url, String banner_url) {
    public CategoryDTO(Long id, String name, String acronym, String icon_url, String banner_url) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.icon_url = icon_url;
        this.banner_url = banner_url;
    }
}