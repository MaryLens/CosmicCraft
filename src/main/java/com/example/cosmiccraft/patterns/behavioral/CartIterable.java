package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Cart;

public class CartIterable {
    private final Cart cart;

    public CartIterable(Cart cart) {
        this.cart = cart;
    }

    public CartIterator iterator() {
        return new CartIterator(cart);
    }
}
