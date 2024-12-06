package com.minimarket.controller;

import com.minimarket.model.Order;
import com.minimarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    // Checkout
    @PostMapping("/checkout")
    public String checkout(@RequestParam String paymentMethod) {
        Order order = orderService.createOrder(paymentMethod);
        return "redirect:/orders/" + order.getId();
    }

    // Detail Order
    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order/detail";
    }

    // Riwayat Order
    @GetMapping("/history")
    public String orderHistory(Model model) {
        model.addAttribute("orders", orderService.getCurrentUserOrders());
        return "order/history";
    }

    // Upload Bukti Pembayaran
    @PostMapping("/{id}/upload-payment")
    public String uploadPaymentProof(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        orderService.uploadPaymentProof(id, file);
        return "redirect:/orders/" + id;
    }

    // Cancel Order
    @PostMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "redirect:/orders/history";
    }
}