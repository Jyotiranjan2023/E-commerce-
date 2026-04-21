package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Order;

public interface OrderService {

    Order createOrder(String email);
}