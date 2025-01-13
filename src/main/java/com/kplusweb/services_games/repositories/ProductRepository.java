package com.kplusweb.services_games.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kplusweb.services_games.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    <Optional>Product findById(Long id);

    <Optional>Product findByName(String name);

}
