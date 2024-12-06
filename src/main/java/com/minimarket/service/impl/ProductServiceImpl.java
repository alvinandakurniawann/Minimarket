package com.minimarket.service.impl;

import com.minimarket.model.Product;
import com.minimarket.repository.ProductRepository;
import com.minimarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Long countProducts() {
        return productRepository.count();
    }

    @Override
    public List<Product> getNewProducts() {
        // This could be customized based on your needs
        return productRepository.findTop10ByOrderByIdDesc();
    }

    @Override
    public void updateStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setStock(product.getStock() + quantity);
            productRepository.save(product);
        }
    }
}