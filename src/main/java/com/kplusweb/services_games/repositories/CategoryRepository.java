package com.kplusweb.services_games.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kplusweb.services_games.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
