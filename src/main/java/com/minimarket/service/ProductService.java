package com.minimarket.service;

import com.minimarket.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getProductsByCategory(String category);
    List<Product> searchProducts(String keyword);
    Long countProducts();
    List<Product> getNewProducts();
    void updateStock(Long productId, Integer quantity);
}