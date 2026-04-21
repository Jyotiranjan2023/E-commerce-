package com.ecommerce.backend.service;

import java.util.List;
import com.ecommerce.backend.model.Cart;

public interface CartService {

    Cart addToCart(String email, Long productId, int quantity);

    List<Cart> getUserCart(String email);

    void removeFromCart(Long cartId);
}