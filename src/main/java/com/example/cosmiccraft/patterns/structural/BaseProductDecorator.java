package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseProductDecorator implements ProductDecorator {
    private final Product product;

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public int getPrice() {
        return product.getPrice();
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }
}
