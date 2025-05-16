package com.example.cosmiccraft.repositories;

import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUser(User user);
}
