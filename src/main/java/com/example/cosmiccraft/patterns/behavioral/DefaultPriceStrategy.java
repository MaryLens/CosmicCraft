package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Product;

public class DefaultPriceStrategy implements PriceStrategy {
    @Override
    public int calculatePrice(Product product) {
        return product.getPrice();
    }
}
