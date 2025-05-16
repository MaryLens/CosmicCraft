package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.enums.OrderStatus;

public interface OrderState {
    void next(Order order);
    void cancel(Order order);
    OrderStatus getStatus();
}
