package com.minimarket.service.impl;

import com.minimarket.model.Cart;
import com.minimarket.model.Product;
import com.minimarket.model.User;
import com.minimarket.repository.CartRepository;
import com.minimarket.repository.ProductRepository;
import com.minimarket.service.CartService;
import com.minimarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Cart> getCurrentUserCart() {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return cartRepository.findByUserId(currentUser.getId());
        }
        return java.util.Collections.emptyList();
    }

    @Override
    public Cart addToCart(Long productId, Integer quantity) {
        User currentUser = userService.getCurrentUser();
        Product product = productRepository.findById(productId).orElse(null);
        
        if (currentUser != null && product != null && product.getStock() >= quantity) {
            Cart cart = new Cart(currentUser, product, quantity);
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void updateCartItemQuantity(Long cartId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.setQuantity(quantity);
            cart.updateTotalPrice();
            cartRepository.save(cart);
        }
    }

    @Override
    public void clearCart() {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            cartRepository.deleteByUserId(currentUser.getId());
        }
    }

    @Override
    public Double getCartTotal() {
        return getCurrentUserCart().stream()
                .mapToDouble(Cart::getTotalPrice)
                .sum();
    }

    @Override
    public Integer getCartItemCount() {
        return getCurrentUserCart().size();
    }
}