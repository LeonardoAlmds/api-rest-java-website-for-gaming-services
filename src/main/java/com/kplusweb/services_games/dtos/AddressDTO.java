package com.kplusweb.services_games.dtos;

public record AddressDTO(
        Long id,
        String postalCode,
        String city,
        String neighborhood,
        String street,
        Integer number,
        String country,
        String complement,
        Long personal_data
) {
}