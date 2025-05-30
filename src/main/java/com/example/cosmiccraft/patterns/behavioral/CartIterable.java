package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Cart;
import com.example.cosmiccraft.models.CartItem;
import lombok.Data;

@Data
public class CartIterable implements Iterable<CartItem>{
    private final Cart cart;

    public CartIterable(Cart cart) {
        this.cart = cart;
    }

    public CartIterator iterator() {
        return new CartIterator(cart);
    }
}
