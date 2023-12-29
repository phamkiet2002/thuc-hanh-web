package com.example.doanweblaptop.controller.Khachhang;

import com.example.doanweblaptop.dto.CartDTO;
import com.example.doanweblaptop.entity.CartItem;
import com.example.doanweblaptop.entity.LapTop;
import com.example.doanweblaptop.service.Cart.CartService;
import com.example.doanweblaptop.service.Laptop.LapTopService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/carts")
public class CartController {
    LapTopService laptopService;
    CartService cartService;

    public CartController(LapTopService laptopService, CartService cartService) {
        this.laptopService = laptopService;
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String shoppingCart(Model model){
        model.addAttribute("cart",cartService.getAll());
        model.addAttribute("total",cartService.getAmountVND());
        return "fragmentKhachhang/cart-laptop";
        //return "khachhang/index";
    }
    @GetMapping("/add")
    public String add(@RequestParam("laptopId") Long id, HttpServletRequest request){
        String referer = request.getHeader("Referer");
        Optional<LapTop> opt=laptopService.findById(id);
        if(opt.isPresent()){
            LapTop laptop=opt.get();
            CartDTO cartDTO =new CartDTO();
            cartDTO.setId(laptop.getId());
            cartDTO.setLapTop(laptop);
            cartDTO.setName(laptop.getName());
            cartDTO.setQty(1);
            cartDTO.setPrice(laptop.getPrice());
            cartService.add(cartDTO);
        }

        return "redirect:"+referer;
    }
    @GetMapping("/addDetail")
    public String addDetail(@ModelAttribute("item") CartDTO cartDTO, @RequestParam("laptopId") Long id, @RequestParam("qty") int qty, HttpServletRequest request){
        String referer = request.getHeader("Referer");
        Optional<LapTop> opt=laptopService.findById(id);
        if(opt.isPresent()){
            LapTop laptop=opt.get();
            cartDTO.setId(laptop.getId());
            cartDTO.setLapTop(laptop);
            cartDTO.setName(laptop.getName());
            cartDTO.setQty(qty);
            cartDTO.setPrice(laptop.getPrice());
            cartService.addTotal(cartDTO,qty);
        }
        return "redirect:"+referer;
    }
    @GetMapping("/remove")
    public String remove(@RequestParam("laptopId") Long id,HttpServletRequest request){
        String referer = request.getHeader("Referer");
        cartService.remove(id);
        return "redirect:"+referer;
    }
    @GetMapping("/clear")
    public String clear(){
        cartService.clear();
        return "redirect:/carts/cart";
    }
    @PostMapping("/update")
    public String update(@RequestParam(name = "id")Long id,@RequestParam(name = "qty")int qty){
        cartService.update(id,qty);
        return "redirect:/carts/cart";
    }
}
