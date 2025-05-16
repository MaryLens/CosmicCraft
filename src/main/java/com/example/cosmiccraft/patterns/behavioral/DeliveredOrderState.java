package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.enums.OrderStatus;

public class DeliveredOrderState implements OrderState {
    @Override
    public void next(Order order) {
        // No further state
    }

    @Override
    public void cancel(Order order) {
        // Cannot cancel delivered order
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.DELIVERED;
    }
}
