package com.ecommerce.backend.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.OrderRepository;
import com.razorpay.RazorpayClient;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    // 🔥 Inject from application.properties
    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Override
    public Order createOrder(String email) {

        // 🔹 Get cart items
        List<Cart> cartItems = cartRepository.findByUserEmail(email);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // 🔹 Calculate total (TEMP LOGIC)
        double total = 0;
        for (Cart c : cartItems) {
            total += c.getQuantity() * 500; // use fixed test price
        }

        try {
            // 🔹 Create Razorpay client
            RazorpayClient client = new RazorpayClient(key, secret);

            JSONObject options = new JSONObject();
            options.put("amount", (int)(total * 100)); // convert to paisa
            options.put("currency", "INR");
            options.put("receipt", "order_" + System.currentTimeMillis());

            com.razorpay.Order razorpayOrder = client.orders.create(options);

            // 🔹 Save order in DB
            Order order = new Order();
            order.setUserEmail(email);
            order.setTotalAmount(total);
            order.setStatus("CREATED");
            order.setRazorpayOrderId(razorpayOrder.get("id"));

            return orderRepository.save(order);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Razorpay order creation failed");
        }
    }
}