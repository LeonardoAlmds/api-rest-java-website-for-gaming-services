package com.kplusweb.services_games.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kplusweb.services_games.dtos.PersonalDataDTO;
import com.kplusweb.services_games.entity.Address;
import com.kplusweb.services_games.entity.PersonalData;
import com.kplusweb.services_games.entity.Phone;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.repositories.AddressRepository;
import com.kplusweb.services_games.repositories.PersonalDataRepository;
import com.kplusweb.services_games.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PersonalDataRepository personalDataRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, PersonalDataRepository personalDataRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.personalDataRepository = personalDataRepository;
        this.addressRepository = addressRepository;
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