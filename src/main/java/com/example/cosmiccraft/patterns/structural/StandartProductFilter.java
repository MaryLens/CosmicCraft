package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;

import java.util.List;

public class StandartProductFilter extends ProductFilter {
    public StandartProductFilter(FilterImplementor implementor) {
        super(implementor);
    }

    @Override
    public List<Product> filterProducts(List<Product> products, String title, Long categoryId, Integer maxPrice) {
        return implementor.filter(products, title, categoryId, maxPrice);
    }
}
