package com.kplusweb.services_games.repositories;

import com.kplusweb.services_games.entity.PersonalData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    <Optional>PersonalData findByCpf(String cpf);

    <Optional>PersonalData findByName(String name);
}
