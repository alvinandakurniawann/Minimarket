package com.minimarket.model;

public interface IUser {
    Long getId();
    void setId(Long id);
    
    String getUsername();
    void setUsername(String username);
    
    String getEmail();
    void setEmail(String email);
    
    String getPassword();
    void setPassword(String password);
    
    String getRole();
    void setRole(String role);
    
    String getFullName();
    void setFullName(String fullName);
    
    String getAddress();
    void setAddress(String address);
    
    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);
}