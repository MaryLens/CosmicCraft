package com.example.cosmiccraft.services;

import com.example.cosmiccraft.models.Cart;
import com.example.cosmiccraft.models.CartItem;
import com.example.cosmiccraft.models.Product;
import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.models.view.ProductView;
import com.example.cosmiccraft.patterns.behavioral.DefaultPriceStrategy;
import com.example.cosmiccraft.patterns.behavioral.DiscountPriceStrategy;
import com.example.cosmiccraft.patterns.behavioral.PriceCalculator;
import com.example.cosmiccraft.repositories.CartRepository;
import com.example.cosmiccraft.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseThrow();
    }

    public void addToCart(User user, ProductView productView, int quantity) {
        Product product = productRepository.findById(productView.getId()).orElseThrow();
        Cart cart = getCartByUser(user);
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(new CartItem());
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(item.getQuantity() + quantity);
        if (!cart.getItems().contains(item)) {
            cart.getItems().add(item);
        }
        cartRepository.save(cart);
    }

    public void updateCartItem(User user, Long productId, int quantity) {
        Cart cart = getCartByUser(user);
        cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    if (quantity <= 0) {
                        cart.getItems().remove(item);
                    } else {
                        item.setQuantity(quantity);
                    }
                });
        cartRepository.save(cart);
    }

    public void removeFromCart(User user, Long productId) {
        Cart cart = getCartByUser(user);
        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }

    public int calculateTotalPrice(Cart cart) {
        PriceCalculator calculator = new PriceCalculator();
        int total = 0;
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.isOnSale()) {
                calculator.setStrategy(new DiscountPriceStrategy(0.2));
            } else {
                calculator.setStrategy(new DefaultPriceStrategy());
            }
            total += calculator.calculate(product) * item.getQuantity();
        }
        return total;
    }
}
