package com.kplusweb.services_games.repositories;

import com.kplusweb.services_games.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
