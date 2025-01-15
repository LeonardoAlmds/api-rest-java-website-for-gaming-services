package com.kplusweb.services_games.dtos;

import java.util.List;

public record CategoryDTO(Long id, String name, String acronym, String icon_url, String banner_url, List<ProductDTO> products) {
}
