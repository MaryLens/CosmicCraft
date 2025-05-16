package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;
import lombok.RequiredArgsConstructor;

public interface ProductDecorator {
    Product getProduct();
    int getPrice();
    String getDescription();
}


