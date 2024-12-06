package com.minimarket.model;

public interface ICart {
    Long getId();
    void setId(Long id);
    
    User getUser();
    void setUser(User user);
    
    Product getProduct();
    void setProduct(Product product);
    
    Integer getQuantity();
    void setQuantity(Integer quantity);
    
    Double getTotalPrice();
    void setTotalPrice(Double totalPrice);
    
    void updateTotalPrice();
}