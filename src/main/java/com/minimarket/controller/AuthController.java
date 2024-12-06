package com.minimarket.controller;

import com.minimarket.model.User;
import com.minimarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Menampilkan form login
    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        // Jika sudah login, redirect ke dashboard
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/dashboard";
        }
        return "user/login";
    }

    // Memproses form login
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
        try {
            User user = userService.login(username, password);
            
            if (user != null) {
                session.setAttribute("user", user);
                if ("ADMIN".equals(user.getRole())) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "redirect:/dashboard";
                }
            }
            
            model.addAttribute("error", "Username atau password salah");
            return "user/login";
        } catch (Exception e) {
            model.addAttribute("error", "Terjadi kesalahan saat login");
            return "user/login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}