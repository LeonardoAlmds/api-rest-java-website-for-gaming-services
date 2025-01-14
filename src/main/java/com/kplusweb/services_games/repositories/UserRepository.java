package com.kplusweb.services_games.repositories;

import com.kplusweb.services_games.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    <Optional>User findById(Long id);

    <Optional>User findByUsername(String username);

    <Optional>User findByEmail(String email);

    <Optional>User findByCpf(String cpf);

    <Optional>User findByPhone(String phone);
}
