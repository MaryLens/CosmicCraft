package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.enums.OrderStatus;

public class ShippedOrderState implements OrderState {
    @Override
    public void next(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.SHIPPED;
    }
}
