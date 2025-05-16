package com.example.cosmiccraft.controllers;

import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.patterns.structural.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping
    public String orders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orders", orderFacade.getUserOrders(user));
        return "orders";
    }

    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderFacade.cancelOrder(orderId, user);
        return "redirect:/orders";
    }
}
