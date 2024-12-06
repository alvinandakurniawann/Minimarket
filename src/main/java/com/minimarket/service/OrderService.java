package com.minimarket.service;

import com.minimarket.model.Order;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface OrderService {
    Order createOrder(String paymentMethod);
    Order getOrderById(Long id);
    List<Order> getCurrentUserOrders();
    List<Order> getAllOrders();
    void uploadPaymentProof(Long orderId, MultipartFile file);
    void verifyPayment(Long orderId);
    void cancelOrder(Long orderId);
    Long countOrders();
    List<Order> getPendingOrders();
    List<Order> getOrdersByStatus(String status);
}