package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Product;

public class PriceCalculator {
    private PriceStrategy strategy;

    public void setStrategy(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(Product product) {
        return strategy.calculatePrice(product);
    }
}
