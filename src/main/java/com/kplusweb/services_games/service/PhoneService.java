package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.PhoneDTO;
import com.kplusweb.services_games.entity.PersonalData;
import com.kplusweb.services_games.entity.Phone;
import com.kplusweb.services_games.repositories.CategoryRepository;
import com.kplusweb.services_games.repositories.PersonalDataRepository;
import com.kplusweb.services_games.repositories.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {
    private final CategoryRepository categoryRepository;
    private final PersonalDataRepository personalDataRepository;
    PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository, CategoryRepository categoryRepository, PersonalDataRepository personalDataRepository) {
        this.phoneRepository = phoneRepository;
        this.categoryRepository = categoryRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public List<PhoneDTO> getAllPhones() {
        return  phoneRepository.findAll()
                .stream()
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
            throw new RuntimeException("No phones found with personal id: " + idPersonalData);
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
                .orElseThrow(() -> new RuntimeException("No phone found with id: " + id));

        return new PhoneDTO(
                phone.getId(),
                phone.getDDI(),
                phone.getDDD(),
                phone.getPhone(),
                phone.getPersonalData().getId());
    }

    public String postPhone(PhoneDTO phoneDTO) {
        PersonalData personalData = personalDataRepository.findById(phoneDTO.personal_data())
                .orElseThrow(() -> new RuntimeException("No personal data found with id: " + phoneDTO.personal_data()));

        Phone phone = new Phone();
        phone.setDDI(phoneDTO.DDI());
        phone.setDDD(phoneDTO.DDD());
        phone.setPhone(phoneDTO.phone());
        phone.setPersonalData(personalData);

        phoneRepository.save(phone);
        return "Phone registered successfully";
    }
}
