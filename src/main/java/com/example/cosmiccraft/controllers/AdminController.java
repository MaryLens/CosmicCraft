package com.example.cosmiccraft.controllers;

import com.example.cosmiccraft.models.Category;
import com.example.cosmiccraft.models.enums.OrderStatus;
import com.example.cosmiccraft.services.CategoryService;
import com.example.cosmiccraft.services.OrderService;
import com.example.cosmiccraft.services.ProductService;
import com.example.cosmiccraft.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String adminPanel(Model model) {
        try{
        model.addAttribute("products", productService.getProducts(null, null, null));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("isAdmin", true);
        return "admin";
    } catch (Exception e) {
        e.printStackTrace();
        return "error"; // Создайте страницу error.html для отображения ошибок
    }
    }

    @PostMapping("/category/create")
    public String createCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("/category/update/{id}")
    public String updateCategory(@PathVariable Long id, Category category) {
        category.setId(id);
        categoryService.saveCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin";
    }

    @PostMapping("/order/update/{id}")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam("status") OrderStatus status) {
        orderService.updateStatus(id, status);
        return "redirect:/admin";
    }
}
