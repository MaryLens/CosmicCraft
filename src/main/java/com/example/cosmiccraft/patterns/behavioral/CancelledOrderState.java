package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.enums.OrderStatus;

public class CancelledOrderState implements OrderState {
    @Override
    public void next(Order order) {
        // Cannot move forward
    }

    @Override
    public void cancel(Order order) {
        // Already cancelled
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CANCELLED;
    }
}
