package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CombinedFilterImpl implements FilterImplementor {
    @Override
    public List<Product> filter(List<Product> products, String title, Long categoryId, Integer maxPrice) {
        List<Product> result = products;
        if (title != null && !title.isEmpty()) {
            result = result.stream()
                    .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (categoryId != null) {
            result = result.stream()
                    .filter(p -> p.getCategory().getId().equals(categoryId))
                    .collect(Collectors.toList());
        }
        if (maxPrice != null) {
            result = result.stream()
                    .filter(p -> p.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }
        return result;
    }
}
