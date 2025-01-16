package com.kplusweb.services_games.controller;

import com.kplusweb.services_games.dtos.AuthenticationDTO;
import com.kplusweb.services_games.dtos.LoginResponseDTO;
import com.kplusweb.services_games.dtos.RegisterDTO;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.repositories.UserRepository;
import com.kplusweb.services_games.security.TokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.userRepository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().body("Login already exists");
        }
    
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
    
        User newUser = new User(registerDTO.login(), encryptedPassword, User.Role.USER);
    
        this.userRepository.save(newUser);
    
        return ResponseEntity.ok("User registered successfully");
    }
    
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateRoleToSeller(@PathVariable Long id) {
        User user = this.userRepository.findById(id).orElse(null);
    
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
    
        user.setRole(User.Role.SELLER);
    
        this.userRepository.save(user);
    
        return ResponseEntity.ok("User role updated successfully");
    }
    
}
