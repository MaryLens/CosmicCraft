package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Cart;
import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.models.enums.OrderStatus;
import com.example.cosmiccraft.repositories.UserRepository;
import com.example.cosmiccraft.services.CartService;
import com.example.cosmiccraft.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserRepository userRepository;

    public Order createOrder(User user) {
        Cart cart = cartService.getCartByUser(user);
        return orderService.createOrderFromCart(user, cart);
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        orderService.updateStatus(orderId, status);
    }

    public List<Order> getUserOrders(User user) {
        return orderService.findByUser(user);
    }

    public void cancelOrder(Long orderId, User user) {
        Order order = orderService.findById(orderId);
        if (order.getUser().equals(user) && order.getStatus() == OrderStatus.NEW) {
            orderService.updateStatus(orderId, OrderStatus.CANCELLED);
        }
    }
}
