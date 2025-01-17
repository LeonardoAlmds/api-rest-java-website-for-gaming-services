package com.kplusweb.services_games.dtos;

public record AddressDTO(
        Long id,
        String city,
        String neighborhood,
        String street,
        Integer number,
        String complement,
        String postalCode,
        String country,
        Long personal_data
) {
}
