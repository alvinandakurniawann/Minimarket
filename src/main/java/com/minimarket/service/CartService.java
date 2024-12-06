package com.minimarket.service;

import com.minimarket.model.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getCurrentUserCart();
    Cart addToCart(Long productId, Integer quantity);
    void removeFromCart(Long cartId);
    void updateCartItemQuantity(Long cartId, Integer quantity);
    void clearCart();
    Double getCartTotal();
    Integer getCartItemCount();
}