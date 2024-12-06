package com.minimarket.controller;

import com.minimarket.model.Product;
import com.minimarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    // Katalog Produk
    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/catalog";
    }

    // Detail Produk
    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }

    // Pencarian Produk
    @GetMapping("/search")
    public String searchProducts(@RequestParam String keyword, Model model) {
        model.addAttribute("products", productService.searchProducts(keyword));
        return "product/catalog";
    }

    // Filter by Category
    @GetMapping("/category/{category}")
    public String filterByCategory(@PathVariable String category, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(category));
        return "product/catalog";
    }
}