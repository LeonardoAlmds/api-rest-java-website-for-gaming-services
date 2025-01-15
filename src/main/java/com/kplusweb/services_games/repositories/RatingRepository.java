package com.kplusweb.services_games.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kplusweb.services_games.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    <Optional> List<Rating> findByProductId(Long productId);
}
