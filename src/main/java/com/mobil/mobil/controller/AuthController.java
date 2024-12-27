package com.mobil.mobil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobil.mobil.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register"; // Render halaman register
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.findUserByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "auth/register"; // Tetap di halaman register jika gagal
        }
        userService.registerUser(username, password, "USER");
        return "redirect:/auth/login?registered=true"; // Redirect ke login setelah sukses
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }
}
