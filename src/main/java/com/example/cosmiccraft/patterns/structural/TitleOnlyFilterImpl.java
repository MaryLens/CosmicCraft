package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class TitleOnlyFilterImpl implements FilterImplementor {
    @Override
    public List<Product> filter(List<Product> products, String title, Long categoryId, Integer maxPrice) {
        if (title != null && !title.isEmpty()) {
            return products.stream()
                    .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return products;
    }
}
