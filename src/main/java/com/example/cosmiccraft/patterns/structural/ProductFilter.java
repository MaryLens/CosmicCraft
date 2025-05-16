package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Product;

import java.util.List;

public abstract class ProductFilter {
    protected FilterImplementor implementor;

    protected ProductFilter(FilterImplementor implementor) {
        this.implementor = implementor;
    }

    public abstract List<Product> filterProducts(List<Product> products, String title, Long categoryId, Integer maxPrice);
}
