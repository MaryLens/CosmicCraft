package com.example.cosmiccraft.repositories;

import com.example.cosmiccraft.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
