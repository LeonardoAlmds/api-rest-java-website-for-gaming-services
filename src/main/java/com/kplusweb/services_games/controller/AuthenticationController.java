package com.kplusweb.services_games.controller;

import java.util.List;

import com.kplusweb.services_games.dtos.AuthenticationDTO;
import com.kplusweb.services_games.dtos.LoginResponseDTO;
import com.kplusweb.services_games.dtos.PersonalDataDTO;
import com.kplusweb.services_games.dtos.RegisterDTO;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.repositories.UserRepository;
import com.kplusweb.services_games.security.TokenService;
import com.kplusweb.services_games.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                authenticationDTO.login(),
                authenticationDTO.password()
        );

        var auth = authenticationManager.authenticate(usernamePasswordToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.userRepository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().body("Login already exists");
        }
    
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
    
        User newUser = new User(registerDTO.login(), encryptedPassword, User.Role.USER);
    
        this.userRepository.save(newUser);
    
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/register/personal_data")
    public ResponseEntity<String> registerPersonalData(@RequestBody @Valid PersonalDataDTO personalDataDTO) {
        return ResponseEntity.ok(this.userService.registerPersonalData(personalDataDTO));
    }
    
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateRoleToSeller(@PathVariable Long id) {
        if (this.userRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        } else {
            return ResponseEntity.ok(this.userService.updateRoleToSeller(id));
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
