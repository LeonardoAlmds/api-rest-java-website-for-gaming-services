package com.kplusweb.services_games.dtos;

import com.kplusweb.services_games.entity.User;

public record RegisterDTO(String login, String password, User.Role role) {
}
