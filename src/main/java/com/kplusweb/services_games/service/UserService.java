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

    public List<PersonalDataDTO> getAllPersonalData() {
        List<PersonalData> personalDataList = personalDataRepository.findAll();

        if (personalDataList == null || personalDataList.isEmpty()) {
            return Collections.emptyList();
        }

        return personalDataList.stream()
                .map(personalData -> new PersonalDataDTO(
                        personalData.getId(),
                        personalData.getName(),
                        personalData.getCpf(),
                        personalData.getBirthDate(),
                        personalData.getAddress() != null ?
                                personalData.getAddress().getId() : null,
                        personalData.getPhones() != null ?
                                personalData.getPhones().stream().map(Phone::getId).collect(Collectors.toList()) : Collections.emptyList(),
                        personalData.getUser() != null ? personalData.getUser().getId() : null
                ))
                .collect(Collectors.toList());
    }

    public PersonalDataDTO getPersonalDataById(Long id) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));

        return new PersonalDataDTO(
                personalData.getId(),
                personalData.getName(),
                personalData.getCpf(),
                personalData.getBirthDate(),
                personalData.getAddress() != null ? personalData.getAddress().getId() : null,
                personalData.getPhones() != null ?
                        personalData.getPhones().stream().map(Phone::getId).collect(Collectors.toList()) : Collections.emptyList(),
                personalData.getUser() != null ? personalData.getUser().getId() : null
        );
    }

    public String updatePersonalData(Long id, PersonalDataDTO personalDataDTO) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));

        personalData.setName(personalDataDTO.name());
        personalData.setCpf(personalDataDTO.cpf());
        personalData.setBirthDate(personalDataDTO.birthDate());

        if (personalDataDTO.phone_id() != null && !personalDataDTO.phone_id().isEmpty()) {
            List<Phone> existingPhones = personalData.getPhones(); // Obter lista atual
            List<Phone> updatedPhones = personalDataDTO.phone_id().stream()
                    .map(phoneId -> existingPhones.stream()
                            .filter(phone -> phone.getId().equals(phoneId))
                            .findFirst()
                            .orElseGet(() -> {
                                Phone phone = new Phone();
                                phone.setId(phoneId);
                                return phone;
                            }))
                    .collect(Collectors.toList());
            personalData.setPhones(updatedPhones);
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
        return "Personal data updated successfully";
    }
}