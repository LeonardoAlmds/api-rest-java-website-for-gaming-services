package com.kplusweb.services_games.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String updateRoleToSeller(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setRole(User.Role.SELLER);
        userRepository.save(user);

        var name = user.getUsername();

        return "User " + name + " is now a seller";
    }
}