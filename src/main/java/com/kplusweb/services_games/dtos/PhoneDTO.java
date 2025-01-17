package com.kplusweb.services_games.dtos;

public record PhoneDTO(
        Long id,
        String DDI,
        String DDD,
        String phone,
        Long personal_data
) {
}
