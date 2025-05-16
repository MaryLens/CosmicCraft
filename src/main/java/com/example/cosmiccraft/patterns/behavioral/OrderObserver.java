package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.User;

import java.util.ArrayList;
import java.util.List;

public interface OrderObserver {
    void update(Order order);
}
