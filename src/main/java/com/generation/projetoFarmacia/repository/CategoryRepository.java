package com.generation.projetoFarmacia.repository;

import com.generation.projetoFarmacia.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameContainingIgnoreCase(@Param("name") String name);

    List<Category> findAllByDescriptionContainingIgnoreCase(@Param("category") String category);
}
