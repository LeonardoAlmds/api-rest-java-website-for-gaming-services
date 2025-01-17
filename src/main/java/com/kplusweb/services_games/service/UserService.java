package com.kplusweb.services_games.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.kplusweb.services_games.dtos.PersonalDataDTO;
import com.kplusweb.services_games.entity.Address;
import com.kplusweb.services_games.entity.PersonalData;
import com.kplusweb.services_games.entity.Phone;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.repositories.AddressRepository;
import com.kplusweb.services_games.repositories.PersonalDataRepository;
import com.kplusweb.services_games.repositories.UserRepository;
import com.kplusweb.services_games.security.TokenService;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authorizationManager;
    private final PersonalDataRepository personalDataRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, TokenService tokenService, AuthenticationManager authenticationManager, PersonalDataRepository personalDataRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.authorizationManager = authenticationManager;
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

    public String registerPersonalData(PersonalDataDTO personalDataDTO) {
        PersonalData personalData = new PersonalData();

        personalData.setName(personalDataDTO.name());
        personalData.setCpf(personalDataDTO.cpf());
        personalData.setBirthDate(personalDataDTO.birthDate());

        if (personalDataDTO.phone_id() != null && !personalDataDTO.phone_id().isEmpty()) {
            List<Phone> phones = personalDataDTO.phone_id().stream()
                    .map(phoneId -> {
                        Phone phone = new Phone();
                        phone.setId(phoneId);
                        return phone;
                    })
                    .collect(Collectors.toList());
            personalData.setPhones(phones);
        } else {
            personalData.setPhones(null);
        }

        if (personalDataDTO.addressId() != null) {
            Address address = addressRepository.findById(personalDataDTO.addressId())
                    .orElseThrow(() -> new RuntimeException("Address not found: " + personalDataDTO.addressId()));
            personalData.setAddress(address);
        } else {
            personalData.setAddress(null);
        }

        User user = userRepository.findById(personalDataDTO.user_id())
                .orElseThrow(() -> new RuntimeException("User not found: " + personalDataDTO.user_id()));
        personalData.setUser(user);

        personalDataRepository.save(personalData);
        return "Personal data for user " + personalDataDTO.user_id() + " added successfully";
    }


}
