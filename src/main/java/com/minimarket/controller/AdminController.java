package com.minimarket.controller;

import com.minimarket.model.Product;
import com.minimarket.service.ProductService;
import com.minimarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productService.countProducts());
        model.addAttribute("totalOrders", orderService.countOrders());
        return "admin/dashboard";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/inventory";
    }

    @GetMapping("/inventory/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product-form";
    }

    @PostMapping("/inventory/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/inventory";
    }

    @GetMapping("/inventory/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "admin/product-form";
    }

    @PostMapping("/inventory/edit")
    public String updateProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/inventory";
    }

    @GetMapping("/inventory/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/inventory";
    }

    @GetMapping("/payments")
    public String paymentVerification(Model model) {
        model.addAttribute("pendingOrders", orderService.getPendingOrders());
        return "admin/payment-verification";
    }

    @PostMapping("/payments/verify/{orderId}")
    public String verifyPayment(@PathVariable Long orderId) {
        orderService.verifyPayment(orderId);
        return "redirect:/admin/payments";
    }

    @GetMapping("/sales-report")
    public String salesReport(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/sales-report";
    }
}