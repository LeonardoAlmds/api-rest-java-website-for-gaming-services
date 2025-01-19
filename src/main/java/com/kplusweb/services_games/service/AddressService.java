package com.kplusweb.services_games.service;

import com.kplusweb.services_games.dtos.AddressDTO;
import com.kplusweb.services_games.entity.Address;
import com.kplusweb.services_games.entity.PersonalData;
import com.kplusweb.services_games.exceptions.ResourceNotFoundException;
import com.kplusweb.services_games.repositories.AddressRepository;
import com.kplusweb.services_games.repositories.PersonalDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final PersonalDataRepository personalDataRepository;

    public AddressService(AddressRepository addressRepository, PersonalDataRepository personalDataRepository) {
        this.addressRepository = addressRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();

        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No addresses found.");
        }

        return addresses.stream()
                .map(address -> new AddressDTO(
                        address.getId(),
                        address.getPostalCode(),
                        address.getCity(),
                        address.getNeighborhood(),
                        address.getStreet(),
                        address.getNumber(),
                        address.getCountry(),
                        address.getComplement(),
                        address.getPersonalData().getId()))
                .collect(Collectors.toList());
    }

    public List<AddressDTO> getAddressesByPersonalDataId(Long personalDataId) {
        List<Address> addresses = addressRepository.findByPersonalDataId(personalDataId);

        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No addresses found with personal data id: " + personalDataId);
        }

        return addresses.stream()
                .map(address -> new AddressDTO(
                        address.getId(),
                        address.getPostalCode(),
                        address.getCity(),
                        address.getNeighborhood(),
                        address.getStreet(),
                        address.getNumber(),
                        address.getCountry(),
                        address.getComplement(),
                        address.getPersonalData().getId()))
                .collect(Collectors.toList());
    }

    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No address found with id: " + id));

        return new AddressDTO(
                address.getId(),
                address.getPostalCode(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber(),
                address.getCountry(),
                address.getComplement(),
                address.getPersonalData().getId());
    }

    public String postAddress(AddressDTO addressDTO) {
        PersonalData personalData = personalDataRepository.findById(addressDTO.personal_data())
                .orElseThrow(() -> new ResourceNotFoundException("No personal data found with id: " + addressDTO.personal_data()));

        Optional<Address> existingAddress = addressRepository.findByPostalCodeAndPersonalDataId(addressDTO.postalCode(), addressDTO.personal_data());
        if (existingAddress.isPresent()) {
            throw new IllegalArgumentException("Address already registered for this personal data.");
        }

        Address address = new Address();
        address.setPostalCode(addressDTO.postalCode());
        address.setCity(addressDTO.city());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setStreet(addressDTO.street());
        address.setNumber(addressDTO.number());
        address.setCountry(addressDTO.country());
        address.setComplement(addressDTO.complement());
        address.setPersonalData(personalData);

        addressRepository.save(address);
        return "Address registered successfully";
    }

    public String updateAddress(Long id, AddressDTO addressDTO) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No address found with id: " + id));

        PersonalData personalData = personalDataRepository.findById(addressDTO.personal_data())
                .orElseThrow(() -> new ResourceNotFoundException("No personal data found with id: " + addressDTO.personal_data()));

        Optional<Address> existingAddress = addressRepository.findByPostalCodeAndPersonalDataId(addressDTO.postalCode(), addressDTO.personal_data());
        if (existingAddress.isPresent() && !existingAddress.get().getId().equals(id)) {
            throw new IllegalArgumentException("Address already registered for this personal data.");
        }

        address.setPostalCode(addressDTO.postalCode());
        address.setCity(addressDTO.city());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setStreet(addressDTO.street());
        address.setNumber(addressDTO.number());
        address.setCountry(addressDTO.country());
        address.setComplement(addressDTO.complement());
        address.setPersonalData(personalData);

        addressRepository.save(address);
        return "Address updated successfully";
    }

    public String deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No address found with id: " + id));

        addressRepository.delete(address);
        return "Address deleted successfully";
    }
}
