package com.kplusweb.services_games.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kplusweb.services_games.dtos.UserDTO;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/post")
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        UserDTO savedUser = userService.addUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }
}
