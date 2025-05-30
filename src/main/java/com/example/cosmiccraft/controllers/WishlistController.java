package com.example.cosmiccraft.controllers;

import com.example.cosmiccraft.models.Product;
import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.models.view.ProductView;
import com.example.cosmiccraft.services.ProductService;
import com.example.cosmiccraft.services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    private final ProductService productService;

    @GetMapping
    public String wishlist(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("wishlist", wishlistService.getWishlistByUser(user));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        return "wishlist";
    }

    @PostMapping("/add/{productId}")
    public String addToWishlist(@PathVariable Long productId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProductView productView = productService.getProductById(productId);
        wishlistService.addToWishlist(user, productView);
        return "redirect:/wishlist";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromWishlist(@PathVariable Long productId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        wishlistService.removeFromWishlist(user, productId);
        return "redirect:/wishlist";
    }
}
