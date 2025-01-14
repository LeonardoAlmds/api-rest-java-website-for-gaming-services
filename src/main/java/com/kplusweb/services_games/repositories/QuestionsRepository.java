package com.kplusweb.services_games.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kplusweb.services_games.entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    <Optional> List<Questions> findByUserId(Long userId);
}
