package com.example.cosmiccraft.services;

import com.example.cosmiccraft.models.Product;
import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.models.Wishlist;
import com.example.cosmiccraft.models.view.ProductView;
import com.example.cosmiccraft.repositories.ProductRepository;
import com.example.cosmiccraft.repositories.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    @Transactional
    public Wishlist getWishlistByUser(User user) {
        return wishlistRepository.findByUser(user).orElseThrow();
    }

    public void addToWishlist(User user, ProductView productView) {
        Product product = productRepository.findById(productView.getId()).orElseThrow();
        Wishlist wishlist = getWishlistByUser(user);
        if (!wishlist.getProducts().contains(product)) {
            wishlist.getProducts().add(product);
            wishlistRepository.save(wishlist);
        }
    }

    public void removeFromWishlist(User user, Long productId) {
        Wishlist wishlist = getWishlistByUser(user);
        wishlist.getProducts().removeIf(p -> p.getId().equals(productId));
        wishlistRepository.save(wishlist);
    }
}
