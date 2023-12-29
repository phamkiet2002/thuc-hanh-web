package com.example.doanweblaptop.controller.Khachhang;

import com.example.doanweblaptop.dto.CartDTO;
import com.example.doanweblaptop.entity.*;
import com.example.doanweblaptop.service.Cart.CartService;
import com.example.doanweblaptop.service.ChiTietHoaDon.ChiTietService;
import com.example.doanweblaptop.service.DanhGia.DanhGiaService;
import com.example.doanweblaptop.service.DanhMuc.DanhMucService;
import com.example.doanweblaptop.service.Laptop.LapTopService;
import com.example.doanweblaptop.service.User.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/detail")
public class LaptopDetailController {
    private LapTopService lapTopService;
    private DanhGiaService danhGiaService;
    private UserService userService;
    private CartService cartService;
    private ChiTietService chiTietService;


    public LaptopDetailController(ChiTietService chiTietService,CartService cartService,UserService userService, LapTopService lapTopService, DanhGiaService danhGiaService) {
        this.lapTopService = lapTopService;
        this.danhGiaService = danhGiaService;
        this.userService = userService;
        this.cartService = cartService;
        this.chiTietService = chiTietService;
    }

    @GetMapping("/laptopdetail")
    public String showDetail(@RequestParam("laptopid") Long idlaptop,
                             Model model) {
        Optional<LapTop> opt = lapTopService.findById(idlaptop);
        LapTop lapTop = null;
        if (opt.isPresent()) {
            lapTop = opt.get();
        }


        //model.addAttribute("soluong", chiTietService.countByQuantity());

        List<DanhGia> danhGia = (List<DanhGia>) danhGiaService.findByLapTopId(idlaptop);
        model.addAttribute("listdanhgia", danhGia);
        model.addAttribute("item",new CartDTO());
        model.addAttribute("laptopdetail", lapTop);

        model.addAttribute("cart",cartService.getAll());
        model.addAttribute("total",cartService.getAmountVND());

        //theem danh gia theo id laptop va id user
        DanhGia danhGia1 = new DanhGia();
        model.addAttribute("danhgia", danhGia1);

        //return "fragmentKhachhang/product";
        return "khachhang/index";
    }

    @PostMapping("/savedanhgia")
    public String danhgia(@ModelAttribute("danhgia") DanhGia danhGia,
                          Principal principal,
                          @RequestParam("laptopId") Long laptopid) {

        if (principal == null || principal.getName() == null) {
            return "redirect:/account/loginpage";
        }
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Optional<LapTop> lapTopOptional = lapTopService.findById(laptopid);
        if (user != null && lapTopOptional.isPresent()) {
            LapTop lapTop = lapTopOptional.get();
            danhGia.setLapTop(lapTop);
            danhGia.setUser(user);
            danhGia.setNgayDanhGia(new Date());
            danhGiaService.save(danhGia);
        }
        return "redirect:/detail/laptopdetail?laptopid=" + laptopid;
    }
}
