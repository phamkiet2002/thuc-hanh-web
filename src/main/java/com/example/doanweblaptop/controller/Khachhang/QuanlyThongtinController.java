package com.example.doanweblaptop.controller.Khachhang;

import com.example.doanweblaptop.entity.User;
import com.example.doanweblaptop.service.Cart.CartService;
import com.example.doanweblaptop.service.User.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/thongtin")
public class QuanlyThongtinController {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;
    private CartService cartService;

    public QuanlyThongtinController(CartService cartService,UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder=passwordEncoder;
        this.cartService = cartService;
    }
    @GetMapping("/user")
    public String thongtin(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);



        model.addAttribute("cart",cartService.getAll());
        model.addAttribute("total",cartService.getAmountVND());

        //return "fragmentKhachhang/thongtinkhachhang";
        return "khachhang/index";
    }
    @GetMapping("/ShowFormSuaThongtin")
    public String showFormThemSua( Model model, Principal principal) {
        String username = principal.getName();
        User userdetail = userService.findByUsername(username);
        model.addAttribute("userdetail", userdetail);
        return "fragmentKhachhang/formCapnhat";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("userdetail") User userdetail){
        String encodedPassword = passwordEncoder.encode(userdetail.getPassword());
        userdetail.setPassword(encodedPassword);
        userService.save(userdetail);return "redirect:/thongtin/user";
    }


}
