package com.kplusweb.services_games.dtos;

import java.time.LocalDateTime;

public record RatingDTO(
    Long id,
    Integer score,
    String comment,
    Long product_id,
    Long user_id,
    LocalDateTime created_at,
    LocalDateTime updated_at
) {
    public RatingDTO(
        Long id,
        Integer score,
        String comment,
        Long product_id,
        Long user_id,
        LocalDateTime created_at,
        LocalDateTime updated_at
    ) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.product_id = product_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
