package com.minimarket.service;

import com.minimarket.model.User;
import com.minimarket.model.Order;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    User login(String username, String password);  // Tambahkan method login
    User getCurrentUser();
    User findByEmail(String email);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
    List<User> getUsersByRole(String role);
    List<Order> getUserOrders(Long userId);
}