package com.example.cosmiccraft.repositories;

import com.example.cosmiccraft.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
