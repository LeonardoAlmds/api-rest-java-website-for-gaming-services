package com.kplusweb.services_games.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kplusweb.services_games.dtos.UserDTO;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .collect(Collectors.toList());
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setCpf(userDTO.cpf());
        user.setPhone(userDTO.phone());
        user.setPassword(userDTO.password());

        User savedUser = userRepository.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCpf(),
                savedUser.getPhone(),
                savedUser.getPassword()
        );
    }

}
