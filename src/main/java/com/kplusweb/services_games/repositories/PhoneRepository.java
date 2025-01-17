package com.kplusweb.services_games.repositories;

import com.kplusweb.services_games.entity.Phone;
import com.kplusweb.services_games.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByPersonalDataId(Long personalDataId);
}

