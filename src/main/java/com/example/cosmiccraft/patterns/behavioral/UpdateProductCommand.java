package com.example.cosmiccraft.patterns.behavioral;

import com.example.cosmiccraft.models.Category;
import com.example.cosmiccraft.models.Product;
import com.example.cosmiccraft.services.ProductService;
import org.springframework.web.multipart.MultipartFile;

public class UpdateProductCommand implements AdminCommand {
    private final ProductService productService;
    private final Product product;
    private final Category category;
    private final MultipartFile[] files;

    public UpdateProductCommand(ProductService productService, Product product, MultipartFile[] files, Category category) {
        this.productService = productService;
        this.product = product;
        this.files = files;
        this.category = category;
    }

    @Override
    public void execute() {
        productService.updateProduct(product, files, category);
    }
}
