package com.minimarket.controller;

import com.minimarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;

    // Lihat Keranjang
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCurrentUserCart());
        model.addAttribute("total", cartService.getCartTotal());
        return "cart/view";
    }

    // Tambah ke Keranjang
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    // Update Quantity
    @PostMapping("/update")
    public String updateCartItem(@RequestParam Long cartItemId, @RequestParam Integer quantity) {
        cartService.updateCartItemQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }

    // Hapus dari Keranjang
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    // Clear Cart
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}