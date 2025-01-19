package com.kplusweb.services_games.repositories;

import com.kplusweb.services_games.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByPersonalDataId(Long personalDataId);
    Optional<Address> findByPostalCodeAndPersonalDataId(String postalCode, Long personalDataId);
}

