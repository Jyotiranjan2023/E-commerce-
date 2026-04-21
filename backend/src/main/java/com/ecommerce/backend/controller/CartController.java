package com.ecommerce.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    // 🔹 Add to cart
    @PostMapping
    public Cart addToCart(@RequestParam Long productId,
                         @RequestParam int quantity) {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return cartService.addToCart(email, productId, quantity);
    }

    // 🔹 View cart
    @GetMapping
    public List<Cart> getCart() {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return cartService.getUserCart(email);
    }

    // 🔹 Remove item
    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "Item removed";
    }
}