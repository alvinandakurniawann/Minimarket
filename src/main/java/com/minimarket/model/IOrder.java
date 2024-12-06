package com.minimarket.model;

import java.time.LocalDateTime;

public interface IOrder {
    Long getId();
    void setId(Long id);
    
    User getUser();
    void setUser(User user);
    
    LocalDateTime getOrderDate();
    void setOrderDate(LocalDateTime orderDate);
    
    Double getTotalAmount();
    void setTotalAmount(Double totalAmount);
    
    String getStatus();
    void setStatus(String status);
    
    String getPaymentMethod();
    void setPaymentMethod(String paymentMethod);
    
    String getPaymentProof();
    void setPaymentProof(String paymentProof);
    
    void markAsPaid();
    void markAsVerified();
    void markAsCompleted();
}