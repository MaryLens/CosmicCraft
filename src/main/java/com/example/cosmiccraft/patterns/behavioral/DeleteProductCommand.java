package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.services.ProductService;

public class DeleteProductCommand implements AdminCommand {
    private final ProductService productService;
    private final Long productId;

    public DeleteProductCommand(ProductService productService, Long productId) {
        this.productService = productService;
        this.productId = productId;
    }

    @Override
    public void execute() {
        productService.deleteProduct(productId);
    }
}
