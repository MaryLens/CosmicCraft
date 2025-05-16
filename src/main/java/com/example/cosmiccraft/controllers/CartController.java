package com.example.cosmiccraft.controllers;

import com.example.cosmiccraft.models.Cart;
import com.example.cosmiccraft.models.Product;
import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.models.view.ProductView;
import com.example.cosmiccraft.patterns.behavioral.CartIterable;
import com.example.cosmiccraft.patterns.structural.OrderFacade;
import com.example.cosmiccraft.services.CartService;
import com.example.cosmiccraft.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final OrderFacade orderFacade;

    @GetMapping
    public String cart(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartService.getCartByUser(user);
        model.addAttribute("cartIterable", new CartIterable(cart));
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam("quantity") int quantity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProductView product = productService.getProductById(productId);
        cartService.addToCart(user, product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateCartItem(@PathVariable Long productId, @RequestParam("quantity") int quantity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.updateCartItem(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.removeFromCart(user, productId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderFacade.createOrder(user);
        return "redirect:/orders";
    }
}
