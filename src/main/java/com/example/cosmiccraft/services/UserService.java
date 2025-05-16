package com.example.cosmiccraft.services;


import com.example.cosmiccraft.models.Cart;
import com.example.cosmiccraft.models.Image;
import com.example.cosmiccraft.models.User;
import com.example.cosmiccraft.models.Wishlist;
import com.example.cosmiccraft.models.enums.Role;
import com.example.cosmiccraft.repositories.CartRepository;
import com.example.cosmiccraft.repositories.UserRepository;
import com.example.cosmiccraft.repositories.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);

        // Create wishlist and cart
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlistRepository.save(wishlist);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        log.info("User created: {}", user.getEmail());
        return true;
    }

    public void updateUser(User user, String name, MultipartFile avatar) {
        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }
        if (avatar != null && !avatar.isEmpty()) {
            Image image = new Image();
            image.setOriginalFileName(avatar.getOriginalFilename());
            try {
                image.setBytes(avatar.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            user.setAvatar(image);
        }
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
