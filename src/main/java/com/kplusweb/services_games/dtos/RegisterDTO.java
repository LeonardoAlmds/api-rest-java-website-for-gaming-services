package com.kplusweb.services_games.dtos;

public record RegisterDTO(String login, String password) {
    public RegisterDTO {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}
