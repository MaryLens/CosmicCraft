package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;

import java.util.List;

public interface FilterImplementor {
    List<Product> filter(List<Product> products, String title, Long categoryId, Integer maxPrice);
}
