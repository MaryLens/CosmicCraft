package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiscountedProductDecorator implements ProductDecorator {
    private final ProductDecorator productDecorator;
    private final double discountPercentage;

    @Override
    public Product getProduct() {
        return productDecorator.getProduct();
    }

    @Override
    public int getPrice() {
        return (int) (productDecorator.getPrice() * (1 - discountPercentage));
    }

    @Override
    public String getDescription() {
        return productDecorator.getDescription() + " (Discount: " + (discountPercentage * 100) + "%)";
    }
}
