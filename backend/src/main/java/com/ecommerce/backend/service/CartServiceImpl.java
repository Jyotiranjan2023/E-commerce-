package com.ecommerce.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(String email, Long productId, int quantity) {

        Cart cart = new Cart();
        cart.setUserEmail(email);
        cart.setProductId(productId);
        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getUserCart(String email) {
        return cartRepository.findByUserEmail(email);
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}