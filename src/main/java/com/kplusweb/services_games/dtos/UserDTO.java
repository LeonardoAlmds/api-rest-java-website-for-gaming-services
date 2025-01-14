package com.kplusweb.services_games.dtos;

public record UserDTO(Long id, String name, String username, String email, String password, String cpf, String phone) {
    public UserDTO(Long id, String name, String username, String email, String password, String cpf, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.phone = phone;
    }
    
}
