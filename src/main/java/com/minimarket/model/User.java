package com.minimarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements IUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;
    private String phone;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public void setFullName(String fullName) {
        this.name = fullName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getPhoneNumber() {
        return phone;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }
    
    // Additional methods needed by the service
    public String getName() {
        return getFullName();
    }
    
    public void setName(String name) {
        setFullName(name);
    }
}