package com.kplusweb.services_games.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kplusweb.services_games.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
