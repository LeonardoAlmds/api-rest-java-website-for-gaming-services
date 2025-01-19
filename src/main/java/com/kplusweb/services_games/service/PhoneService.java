package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.PhoneDTO;
import com.kplusweb.services_games.entity.PersonalData;
import com.kplusweb.services_games.entity.Phone;
import com.kplusweb.services_games.exceptions.ResourceNotFoundException;
import com.kplusweb.services_games.repositories.CategoryRepository;
import com.kplusweb.services_games.repositories.PersonalDataRepository;
import com.kplusweb.services_games.repositories.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneService {
    private final CategoryRepository categoryRepository;
    private final PersonalDataRepository personalDataRepository;
    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository, CategoryRepository categoryRepository, PersonalDataRepository personalDataRepository) {
        this.phoneRepository = phoneRepository;
        this.categoryRepository = categoryRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public List<PhoneDTO> getAllPhones() {
        List<Phone> phones = phoneRepository.findAll();

        if (phones.isEmpty()) {
            throw new ResourceNotFoundException("No phones found.");
        }

        return phones.stream()
                .map(phone -> new PhoneDTO(
                        phone.getId(),
                        phone.getDDI(),
                        phone.getDDD(),
                        phone.getPhone(),
                        phone.getPersonalData().getId()))
                .collect(Collectors.toList());
    }

    public List<PhoneDTO> getPhonesByIdPersonalData(Long idPersonalData) {
        List<Phone> phones = phoneRepository.findByPersonalDataId(idPersonalData);

        if (phones.isEmpty()) {
            throw new ResourceNotFoundException("No phones found with personal data id: " + idPersonalData);
        }

        return phones.stream()
                .map(phone -> new PhoneDTO(
                        phone.getId(),
                        phone.getDDI(),
                        phone.getDDD(),
                        phone.getPhone(),
                        phone.getPersonalData().getId()))
                .toList();
    }

    public PhoneDTO getPhoneById(Long id) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No phone found with id: " + id));

        return new PhoneDTO(
                phone.getId(),
                phone.getDDI(),
                phone.getDDD(),
                phone.getPhone(),
                phone.getPersonalData().getId());
    }

    public String postPhone(PhoneDTO phoneDTO) {
        PersonalData personalData = personalDataRepository.findById(phoneDTO.personal_data())
                .orElseThrow(() -> new ResourceNotFoundException("No personal data found with id: " + phoneDTO.personal_data()));

        Optional<Phone> existingPhone = phoneRepository.findByPhoneAndPersonalDataId(phoneDTO.phone(), phoneDTO.personal_data());
        if (existingPhone.isPresent()) {
            throw new IllegalArgumentException("Phone already registered for this personal data.");
        }

        Phone phone = new Phone();
        phone.setDDI(phoneDTO.DDI());
        phone.setDDD(phoneDTO.DDD());
        phone.setPhone(phoneDTO.phone());
        phone.setPersonalData(personalData);

        phoneRepository.save(phone);
        return "Phone registered successfully";
    }

    public String updatePhone(Long id, PhoneDTO phoneDTO) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No phone found with id: " + id));

        PersonalData personalData = personalDataRepository.findById(phoneDTO.personal_data())
                .orElseThrow(() -> new ResourceNotFoundException("No personal data found with id: " + phoneDTO.personal_data()));

        Optional<Phone> existingPhone = phoneRepository.findByPhoneAndPersonalDataId(phoneDTO.phone(), phoneDTO.personal_data());
        if (existingPhone.isPresent() && !existingPhone.get().getId().equals(id)) {
            throw new IllegalArgumentException("Phone already registered for this personal data.");
        }

        phone.setDDI(phoneDTO.DDI());
        phone.setDDD(phoneDTO.DDD());
        phone.setPhone(phoneDTO.phone());
        phone.setPersonalData(personalData);

        phoneRepository.save(phone);
        return "Phone updated successfully";
    }


    public String deletePhone(Long id) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No phone found with id: " + id));

        phoneRepository.delete(phone);
        return "Phone deleted successfully";
    }
}
