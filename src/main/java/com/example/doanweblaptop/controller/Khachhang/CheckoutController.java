package com.example.doanweblaptop.controller.Khachhang;

import com.example.doanweblaptop.dto.CartDTO;
import com.example.doanweblaptop.entity.ChiTietHoaDon;
import com.example.doanweblaptop.entity.ChiTietHoaDonId;
import com.example.doanweblaptop.entity.HoaDon;
import com.example.doanweblaptop.entity.StatusEnum;
import com.example.doanweblaptop.service.Cart.CartService;
import com.example.doanweblaptop.service.ChiTietHoaDon.ChiTietService;
import com.example.doanweblaptop.service.HoaDon.HoaDonService;
import com.example.doanweblaptop.service.User.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    UserService userService;
    ChiTietService chiTietService;
   HoaDonService hoaDonService;
    CartService cartService;

    public CheckoutController(UserService userService, ChiTietService chiTietService, HoaDonService hoaDonService, CartService cartService) {
        this.userService = userService;
        this.chiTietService = chiTietService;
        this.hoaDonService = hoaDonService;
        this.cartService = cartService;
    }

    @GetMapping("/form")
    public  String check(Model model){
//        Collection<CartDTO> cart =(Collection<CartDTO>) session.getAttribute("cart");
        HoaDon hoaDon=new HoaDon();
        model.addAttribute("hoadon",hoaDon);
        model.addAttribute("cart",cartService.getAll());
//       model.addAttribute("cart",cart);
        model.addAttribute("total",cartService.getAmountVND());
        //return"fragmentKhachhang/checkout";

        return "khachhang/index";
    }
    @PostMapping("/add")
    public String addHoadon(Model model, @ModelAttribute("hoadon")HoaDon hoaDon, Principal principal){
        if(principal.getName()==null){
            return "redirect:/account/loginpage";
        }
        else hoaDon.setUser(userService.findByUsername(principal.getName()));
        hoaDon.setStatus(StatusEnum.UNCONFIRMED);

        // Set ChiTietHoaDon for each item in the cart
        List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
        for (CartDTO item : cartService.getAll()) {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId(hoaDon, item.getLapTop());
            chiTietHoaDon.setId(chiTietHoaDonId);
            chiTietHoaDon.setQuantity(item.getQty());
            chiTietHoaDon.setPrice(item.getPrice()* item.getQty());
            chiTietHoaDons.add(chiTietHoaDon);
        }
        hoaDon.setNgayDat(new Date());
        hoaDon.setChiTietHoaDons(chiTietHoaDons);

        // Save the HoaDon and related ChiTietHoaDon entities
        hoaDonService.save(hoaDon);

        // Clear the cart after successfully placing the order
        cartService.clear();

        //return"fragmentKhachhang/checkout"; // Redirect to a success page

        return  "redirect:/home/store";
    }


}
