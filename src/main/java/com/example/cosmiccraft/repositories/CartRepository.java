package com.example.cosmiccraft.repositories;

import com.example.cosmiccraft.models.Cart;
import com.example.cosmiccraft.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
