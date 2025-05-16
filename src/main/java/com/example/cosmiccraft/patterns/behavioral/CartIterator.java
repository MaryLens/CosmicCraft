package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Cart;
import com.example.cosmiccraft.models.CartItem;

import java.util.Iterator;

public class CartIterator implements Iterator<CartItem> {
    private final Cart cart;
    private int index;

    public CartIterator(Cart cart) {
        this.cart = cart;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < cart.getItems().size();
    }

    @Override
    public CartItem next() {
        return cart.getItems().get(index++);
    }
}
