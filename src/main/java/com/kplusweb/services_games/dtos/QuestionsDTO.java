package com.kplusweb.services_games.dtos;

import java.sql.Date;

public record QuestionsDTO(Long id, String question, String answer, Date created_at, Long user_id) {
    public QuestionsDTO(Long id, String question, String answer, Date created_at, Long user_id) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.created_at = created_at;
        this.user_id = user_id;
    }
}
