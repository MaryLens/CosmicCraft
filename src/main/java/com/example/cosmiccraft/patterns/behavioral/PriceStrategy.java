package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Product;

public interface PriceStrategy {
    int calculatePrice(Product product);
}
