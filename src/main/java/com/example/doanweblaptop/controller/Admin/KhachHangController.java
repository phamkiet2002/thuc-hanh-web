package com.example.doanweblaptop.controller.Admin;

import com.example.doanweblaptop.entity.User;
import com.example.doanweblaptop.service.User.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder;

    public KhachHangController(UserService userService,BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }



    @GetMapping("/ShowFormThemSuaKhachHang")
    public String showFormThemSua(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "form/formThemSuaUser";
    }

    @PostMapping("/save")
    public String saveKhachHang(@ModelAttribute("user") User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.save(user);


        return "redirect:/admin/listUser";
    }

    @GetMapping("/delete")
    public String deleteKhachHang(@RequestParam("userid") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/listUser";
    }

    @GetMapping("/showFormUpdateUser")
    public String showUpdate(@RequestParam("userid") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "form/formThemSuaUser";
    }
}
