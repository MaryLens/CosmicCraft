package com.example.cosmiccraft.repositories;

import com.example.cosmiccraft.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByTitleContainingIgnoreCase(String title);

}
