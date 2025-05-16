package com.example.cosmiccraft.repositories;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
