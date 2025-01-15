package com.kplusweb.services_games.dtos;

import java.sql.Date;

// Add the user when all entities are created

public record QuestionsDTO(Long id, String question, String answer, Date created_at) {
    public QuestionsDTO(Long id, String question, String answer, Date created_at) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.created_at = created_at;
    }
}
