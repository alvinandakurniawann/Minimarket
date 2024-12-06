package com.minimarket.model;

public interface IProduct {
    Long getId();
    void setId(Long id);
    
    String getName();
    void setName(String name);
    
    String getDescription();
    void setDescription(String description);
    
    Double getPrice();
    void setPrice(Double price);
    
    Integer getStock();
    void setStock(Integer stock);
    
    String getCategory();
    void setCategory(String category);
    
    String getImageUrl();
    void setImageUrl(String imageUrl);
}