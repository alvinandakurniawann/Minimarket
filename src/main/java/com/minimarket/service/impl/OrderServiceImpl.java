package com.minimarket.service.impl;

import com.minimarket.model.Order;
import com.minimarket.model.User;
import com.minimarket.repository.OrderRepository;
import com.minimarket.service.CartService;
import com.minimarket.service.OrderService;
import com.minimarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(String paymentMethod) {
        User currentUser = userService.getCurrentUser();
        Double totalAmount = cartService.getCartTotal();
        
        Order order = new Order(currentUser, totalAmount, paymentMethod);
        order = orderRepository.save(order);
        
        // Clear the cart after creating order
        cartService.clearCart();
        
        return order;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getCurrentUserOrders() {
        User currentUser = userService.getCurrentUser();
        return orderRepository.findByUserId(currentUser.getId());
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void uploadPaymentProof(Long orderId, MultipartFile file) {
        Order order = getOrderById(orderId);
        if (order != null) {
            // Handle file upload and save path
            // For simplicity, just marking as paid here
            order.setStatus("PAID");
            orderRepository.save(order);
        }
    }

    @Override
    public void verifyPayment(Long orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.markAsVerified();
            orderRepository.save(order);
        }
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = getOrderById(orderId);
        if (order != null && "PENDING".equals(order.getStatus())) {
            orderRepository.deleteById(orderId);
        }
    }

    @Override
    public Long countOrders() {
        return orderRepository.count();
    }

    @Override
    public List<Order> getPendingOrders() {
        return orderRepository.findByStatus("PENDING");
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}