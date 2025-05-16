package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Order;
import com.example.cosmiccraft.models.User;

public class UserNotificationObserver implements OrderObserver {
    private final User user;

    public UserNotificationObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(Order order) {
        System.out.println("Notification to " + user.getEmail() + ": Order #" + order.getId() + " is now " + order.getStatus());
    }
}
