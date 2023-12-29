package com.example.doanweblaptop.controller.Admin;

import com.example.doanweblaptop.entity.*;
import com.example.doanweblaptop.service.ChiTietHoaDon.ChiTietService;
import com.example.doanweblaptop.service.DanhMuc.DanhMucService;
import com.example.doanweblaptop.service.HoaDon.HoaDonService;
import com.example.doanweblaptop.service.Laptop.LapTopService;
import com.example.doanweblaptop.service.NhaCungCap.NhaCungCapService;
import com.example.doanweblaptop.service.User.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private LapTopService lapTopService;
    private NhaCungCapService nhaCungCapService;
    private DanhMucService danhMucService;
    private UserService userService;
    private HoaDonService hoaDonService;
    private ChiTietService chiTietService;


    public AdminController(LapTopService lapTopService, NhaCungCapService nhaCungCapService, DanhMucService danhMucService, UserService userService, HoaDonService hoaDonService, ChiTietService chiTietService) {
        this.lapTopService = lapTopService;
        this.nhaCungCapService = nhaCungCapService;
        this.danhMucService = danhMucService;
        this.userService = userService;
        this.hoaDonService = hoaDonService;
        this.chiTietService = chiTietService;
    }

    @GetMapping("/home")
    public String showHomeAdmin(){
        return "admin/adminHome";
    }

    @GetMapping("/listlaptop")
    public String listLaptop(Model model,@Param("key") String key,
                             @RequestParam(name = "page",defaultValue = "1")Integer page){
        //List<LapTop> listLaptop = lapTopService.findAll();
        Page<LapTop> listLaptop = lapTopService.findAll(page);

        if (key != null) {
            listLaptop = lapTopService.findByNameContaining(key, page);
            model.addAttribute("key", key);
        }
        else {
            listLaptop = lapTopService.findAll(page);
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalpage",listLaptop.getTotalPages());
        model.addAttribute("laptops", listLaptop);
        return "admin/adminHome";
    }

    @GetMapping("/listncc")
    public String listncc(Model model){
        List<NhaCungCap> listncc = nhaCungCapService.findAll();
        model.addAttribute("nhacungcap", listncc);
        return "admin/adminHome";
    }

    @GetMapping("/listdanhmuc")
    public String listdanhmuc(Model model , @Param("key") String key){
        List<DanhMuc> listdanhmuc; //= danhMucService.findAll();

        if (key!=null)
        {
            listdanhmuc = danhMucService.findByName(key);
        }
        else listdanhmuc = danhMucService.findAll();

        model.addAttribute("dsdanhmuc", listdanhmuc);
        return "admin/adminHome";
    }

    @GetMapping("/listUser")
    public String listuser(Model model, @Param("keyUser") String keyUser,
                           @RequestParam(name = "page", defaultValue = "1") Integer page){
        //List<User> listUser = userService.findAll();
        Page<User> listUser = userService.findAll(page);

        if (keyUser != null){
            listUser = userService.findByNameContainingOrUsernameContaining(keyUser, page);
            model.addAttribute("keyUser", keyUser);
        }
        model.addAttribute("currentPageUser", page);
        model.addAttribute("totalpageUser",listUser.getTotalPages());

        model.addAttribute("dsuser", listUser);
        return "admin/adminHome";
    }
    @GetMapping("/listHoaDon")
    public String ShowHoaDon(Model model){
        List<HoaDon> hoaDon = hoaDonService.findAll();
        model.addAttribute("hoadon", hoaDon);
        model.addAttribute("listStatus",StatusEnum.values());
        return "admin/adminHome";
    }
    @GetMapping("/listChiTietHoaDon")
    public String ShowChiTiet( @RequestParam("hoaDonId")Long id,Model model){
        HoaDon hoaDon = hoaDonService.findById(id);

        List<ChiTietHoaDon> chiTietHoaDons=chiTietService.findListChiTietByHoaDon(hoaDon);
        double total=chiTietHoaDons.stream().mapToDouble(chitiet->chitiet.getPrice()*chitiet.getQuantity()).sum();
        model.addAttribute("chiTietHoaDons",chiTietHoaDons);
        model.addAttribute("total",chiTietService.getAmountVND(total));
        return "admin/adminHome";
    }
    @PostMapping("/updateHoaDon")
    public String updateHoaDon(Model model, @RequestParam("id")Long id,@RequestParam("status")StatusEnum status){
        HoaDon hoaDon=hoaDonService.findById(id);
        List<ChiTietHoaDon> chiTietHoaDons= hoaDon.getChiTietHoaDons();
        if(status== StatusEnum.CONFIRMED) {
            for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
                LapTop lapTop = lapTopService.findById(chiTietHoaDon.getId().getLapTop().getId()).get();
                lapTop.setQuantity(lapTop.getQuantity() - chiTietHoaDon.getQuantity());
                lapTopService.save(lapTop);
            }
        }
        hoaDon.setStatus(status);
        hoaDonService.save(hoaDon);
        return "redirect:/admin/listHoaDon";
    }
}
