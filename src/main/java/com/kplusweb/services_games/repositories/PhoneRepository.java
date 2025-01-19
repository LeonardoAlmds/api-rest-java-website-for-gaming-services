package com.kplusweb.services_games.repositories;

import com.kplusweb.services_games.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByPersonalDataId(Long personalDataId);
    Optional<Phone> findByPhoneAndPersonalDataId(String phone, Long personalDataId);
}

