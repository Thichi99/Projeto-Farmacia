package com.generation.projetoFarmacia.repository;

import com.generation.projetoFarmacia.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findAllByProductDescriptionContainingIgnoreCase(@Param("productDescription") String description);

   // List<Products> findAllByProductPrice(@Param("ProductPrice") double productPrice);

}
