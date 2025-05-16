package com.example.cosmiccraft.controllers;

import com.example.cosmiccraft.models.Category;
import com.example.cosmiccraft.models.Product;
import com.example.cosmiccraft.models.view.ProductView;
import com.example.cosmiccraft.patterns.behavioral.AdminCommandInvoker;
import com.example.cosmiccraft.patterns.behavioral.DeleteProductCommand;
import com.example.cosmiccraft.patterns.behavioral.UpdateProductCommand;
import com.example.cosmiccraft.repositories.CategoryRepository;
import com.example.cosmiccraft.repositories.ProductRepository;
import com.example.cosmiccraft.services.CategoryService;
import com.example.cosmiccraft.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "categoryId", required = false) Long categoryId,
                           @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
                           Model model) {
        List<ProductView> products = productService.getProducts(title, categoryId, maxPrice);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("maxPrice", maxPrice);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@RequestParam("files") MultipartFile[] files,
                                @RequestParam("categoryId") Long categoryId,
                                Product product) {
        Category category = categoryService.getCategoryById(categoryId);
        productService.saveProduct(product, files, category);
        return "redirect:/admin";
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam("files") MultipartFile[] files,
                                @RequestParam("categoryId") Long categoryId,
                                Product product) {
        product.setId(id);
        Category category = categoryService.getCategoryById(categoryId);
        AdminCommandInvoker invoker = new AdminCommandInvoker();
        invoker.setCommand(new UpdateProductCommand(productService,product,files,category));
        invoker.executeCommand();
        return "redirect:/admin";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        AdminCommandInvoker invoker = new AdminCommandInvoker();
        invoker.setCommand(new DeleteProductCommand(productService,id));
        invoker.executeCommand();
        return "redirect:/admin";
    }
}
