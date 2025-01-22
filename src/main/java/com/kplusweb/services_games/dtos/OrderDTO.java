package com.kplusweb.services_games.dtos;

import java.sql.Date;

public record OrderDTO(Long id, Long userId, Long productId, Long subProductId, Date orderDate, String status) {
    
}
