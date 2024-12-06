package com.minimarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/login";  // Redirect ke halaman login
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "user/dashboard";
    }
}