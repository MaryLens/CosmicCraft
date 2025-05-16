package com.example.cosmiccraft.services;

import com.example.cosmiccraft.models.*;
import com.example.cosmiccraft.models.enums.OrderStatus;
import com.example.cosmiccraft.patterns.behavioral.OrderContext;
import com.example.cosmiccraft.patterns.behavioral.OrderSubject;
import com.example.cosmiccraft.patterns.behavioral.UserNotificationObserver;
import com.example.cosmiccraft.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderSubject orderSubject;
    private final CartService cartService;

    public Order createOrderFromCart(User user, Cart cart) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);
        order.setTotal(cartService.calculateTotalPrice(cart));

        orderRepository.save(order);
        cart.getItems().clear();
        orderSubject.addObserver(new UserNotificationObserver(order.getUser()));
        orderSubject.notifyObservers(order);
        return order;
    }

    public void updateStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        OrderContext context = new OrderContext(order.getStatus());
        if (status == OrderStatus.CANCELLED) {
            context.cancel(order);
        } else {
            context.next(order);
        }
        orderRepository.save(order);
        orderSubject.addObserver(new UserNotificationObserver(order.getUser()));
        orderSubject.notifyObservers(order);
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
