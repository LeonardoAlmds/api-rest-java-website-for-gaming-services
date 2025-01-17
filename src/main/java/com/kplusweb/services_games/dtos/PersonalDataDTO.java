package com.kplusweb.services_games.dtos;

import java.sql.Date;
import java.util.List;

public record PersonalDataDTO(
        Long id,
        String name,
        String cpf,
        Date birthDate,
        Long addressId,
        List<Long> phone_id,
        Long user_id
) {
}
