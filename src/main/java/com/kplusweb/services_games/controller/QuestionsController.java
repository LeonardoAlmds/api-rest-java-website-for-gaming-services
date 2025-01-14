package com.kplusweb.services_games.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kplusweb.services_games.dtos.QuestionsDTO;

import com.kplusweb.services_games.service.QuestionsService;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
    private final QuestionsService questionsService;

    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping("/get-by-user-id")
    public ResponseEntity<List<QuestionsDTO>> getQuestionsByUserId(Long userId) {
        List<QuestionsDTO> questions = questionsService.getQuestionsByUserId(userId);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/post")
    public ResponseEntity<QuestionsDTO> createQuestion(QuestionsDTO questionsDTO) {
        QuestionsDTO savedQuestion = questionsService.addQuestion(questionsDTO);
        return ResponseEntity.ok(savedQuestion);
    }
}
