package com.example.doanweblaptop.controller;

import com.example.doanweblaptop.dto.UserDTO;
import com.example.doanweblaptop.entity.Role;
import com.example.doanweblaptop.entity.User;
import com.example.doanweblaptop.entity.UserDetail;
import com.example.doanweblaptop.service.User.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
public class LoginController {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    public LoginController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/loginpage")
    public String login() {
        return "loginlogout/login";
    }

    @GetMapping("/taotk")
    public String showFormThemSua(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "loginlogout/register";
    }

    @PostMapping("/save")
    public String saveKhachHang(@ModelAttribute("userDTO") UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmpassword())) {
            userDTO.setConfirmpassword(null);
            return "loginlogout/register";
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userService.save(userDTO);
        return "redirect:/account/loginpage";
    }
}
