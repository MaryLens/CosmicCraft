package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.enums.OrderStatus;

public class OrderContext {
    private OrderState state;

    public OrderContext(OrderStatus status) {
        switch (status) {
            case NEW:
                state = new NewOrderState();
                break;
            case SHIPPED:
                state = new ShippedOrderState();
                break;
            case DELIVERED:
                state = new DeliveredOrderState();
                break;
            case CANCELLED:
                state = new CancelledOrderState();
                break;
        }
    }

    public void next(Order order) {
        state.next(order);
    }

    public void cancel(Order order) {
        state.cancel(order);
    }
}
