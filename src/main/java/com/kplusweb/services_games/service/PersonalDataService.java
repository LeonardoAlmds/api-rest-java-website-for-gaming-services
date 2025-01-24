package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.PersonalDataDTO;
import com.kplusweb.services_games.entity.Address;
import com.kplusweb.services_games.entity.PersonalData;
import com.kplusweb.services_games.entity.Phone;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.repositories.AddressRepository;
import com.kplusweb.services_games.repositories.PersonalDataRepository;
import com.kplusweb.services_games.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalDataService {
    private final PersonalDataRepository personalDataRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public PersonalDataService(PersonalDataRepository personalDataRepository , UserRepository userRepository, AddressRepository addressRepository) {
        this.personalDataRepository = personalDataRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public List<PersonalDataDTO> getAllPersonalData() {
        List<PersonalData> personalDataList = personalDataRepository.findAll();

        if (personalDataList == null || personalDataList.isEmpty()) {
            return Collections.emptyList();
        }

        return personalDataList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PersonalDataDTO getPersonalDataById(Long id) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));

        return convertToDTO(personalData);
    }

    private PersonalDataDTO convertToDTO(PersonalData personalData) {
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

    public String postPersonalData(PersonalDataDTO personalDataDTO) {
        PersonalData personalData = new PersonalData();
        mapPersonalData(personalData, personalDataDTO);
        personalDataRepository.save(personalData);
        return "Personal data for user " + personalDataDTO.user_id() + " added successfully";
    }

    public String updatePersonalData(Long id, PersonalDataDTO personalDataDTO) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));
        mapPersonalData(personalData, personalDataDTO);
        personalDataRepository.save(personalData);
        return "Personal data updated successfully";
    }

    private void mapPersonalData(PersonalData personalData, PersonalDataDTO personalDataDTO) {
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
    }

    public boolean deletePersonalData(Long id) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));
        personalDataRepository.delete(personalData);
        return true;
    }
}
