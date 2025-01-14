package com.kplusweb.services_games.service;

import java.util.List;

import com.kplusweb.services_games.dtos.QuestionsDTO;
import com.kplusweb.services_games.entity.Questions;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kplusweb.services_games.repositories.QuestionsRepository;

@Service
public class QuestionsService {
    private final QuestionsRepository questionsRepository;

    public QuestionsService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public List<QuestionsDTO> getQuestionsByUserId(Long userId) {
        List<Questions> questions = questionsRepository.findByUserId(userId);
        return questions.stream()
                .map(question -> new QuestionsDTO(
                        question.getId(),
                        question.getQuestion(),
                        question.getAnswer(),
                        question.getCreated_at(),
                        question.getUser()
                ))
                .collect(Collectors.toList());
    }

    public QuestionsDTO addQuestion(QuestionsDTO questionsDTO) {
        Questions question = new Questions();
        question.setQuestion(questionsDTO.question());
        question.setAnswer(questionsDTO.answer());
        question.setCreated_at(questionsDTO.created_at());
        question.setUser(questionsDTO.user_id());

        Questions savedQuestion = questionsRepository.save(question);

        return new QuestionsDTO(
                savedQuestion.getId(),
                savedQuestion.getQuestion(),
                savedQuestion.getAnswer(),
                savedQuestion.getCreated_at(),
                savedQuestion.getUser()
        );
    }
}
