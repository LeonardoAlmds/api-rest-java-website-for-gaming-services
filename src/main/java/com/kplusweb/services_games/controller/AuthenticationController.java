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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                authenticationDTO.login(),
                authenticationDTO.password()
        );

        var auth = authenticationManager.authenticate(usernamePasswordToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO RegisterDTO) {
        if(this.userRepository.findByLogin(RegisterDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(RegisterDTO.password());
        User newUser = new User(RegisterDTO.login(), encryptedPassword, RegisterDTO.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
