package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Product;

public class DiscountPriceStrategy implements PriceStrategy {
    private final double discountPercentage;

    public DiscountPriceStrategy(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public int calculatePrice(Product product) {
        return (int) (product.getPrice() * (1 - discountPercentage));
    }
}
