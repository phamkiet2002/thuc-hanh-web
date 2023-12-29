package com.example.doanweblaptop.controller.Khachhang;

import com.example.doanweblaptop.dao.DanhMucRepository;
import com.example.doanweblaptop.entity.DanhGia;
import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.LapTop;
import com.example.doanweblaptop.entity.NhaCungCap;
import com.example.doanweblaptop.service.Cart.CartService;
import com.example.doanweblaptop.service.DanhGia.DanhGiaService;
import com.example.doanweblaptop.service.DanhMuc.DanhMucService;
import com.example.doanweblaptop.service.Laptop.LapTopService;
import com.example.doanweblaptop.service.NhaCungCap.NhaCungCapService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class PageController {
    private LapTopService lapTopService;
    private NhaCungCapService nhaCungCapService;
    private DanhMucService danhMucService;
    private DanhGiaService danhGiaService;
    private CartService cartService;

    public PageController(LapTopService lapTopService,
                          NhaCungCapService nhaCungCapService, DanhMucService danhMucService,
                          DanhGiaService danhGiaService, CartService cartService) {
        this.lapTopService = lapTopService;
        this.nhaCungCapService = nhaCungCapService;
        this.danhMucService = danhMucService;
        this.danhGiaService = danhGiaService;
        this.cartService = cartService;
    }

    @GetMapping("/list")
    public String showLaptop(Model model) {
        List<LapTop> laptops = lapTopService.findAll();
        model.addAttribute("laptops", laptops);
        model.addAttribute("cart",cartService.getAll());
        model.addAttribute("total",cartService.getAmountVND());
        return "khachhang/index";
    }
    @GetMapping("/store")
    public String store(Model model,@RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "selectedDanhMuc", required = false) ArrayList<Long> selectedDanhMuc,
                        @RequestParam(name = "selectedNhaCungCap", required = false) ArrayList<Long> selectedNhaCungCap,
                        @RequestParam(name = "priceMin", required = false) Double priceMin,
                        @RequestParam(name = "priceMax", required = false) Double priceMax) {
        Page<LapTop>lapTops;
        List<NhaCungCap> nhaCungCaps = nhaCungCapService.findAll();
        List<DanhMuc> danhMucs = danhMucService.findAll();



        if (selectedDanhMuc != null || selectedNhaCungCap != null || (priceMin != null && priceMax != null)) {
            List<DanhMuc> danhMucFilter = (selectedDanhMuc != null) ? danhMucService.findAllById(selectedDanhMuc) : danhMucService.findAll();
            List<NhaCungCap> nhaCungCapFilter = (selectedNhaCungCap != null) ? nhaCungCapService.findAllById(selectedNhaCungCap) : nhaCungCapService.findAll();

            if (priceMin != null && priceMax != null) {
                lapTops = lapTopService.findByDanhMucInAndNccInAndPriceBetween(danhMucFilter, nhaCungCapFilter, priceMin, priceMax,page);
            } else {
                lapTops = lapTopService.findByDanhMucInAndNccIn(danhMucFilter, nhaCungCapFilter,page);
            }
        } else {
            lapTops = lapTopService.findAll(page);
        }
        model.addAttribute("totalPage",lapTops.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("laptopstore", lapTops);
        model.addAttribute("danhmucs", danhMucs);
        model.addAttribute("nhacungcaps", nhaCungCaps);
        model.addAttribute("selectedDanhMuc", selectedDanhMuc);
        model.addAttribute("selectedNhaCungCap", selectedNhaCungCap);
        model.addAttribute("priceMin", priceMin);
        model.addAttribute("priceMax", priceMax);
        model.addAttribute("cart",cartService.getAll());
        model.addAttribute("total",cartService.getAmountVND());
        return "khachhang/index";
    }
    @GetMapping("/update")
    public String showLaptops(Model model,
                              @RequestParam(name = "danhmuc", required = false) ArrayList<Long> danhMuc,
                              @RequestParam(name = "nhacungcap", required = false) ArrayList<Long> nhaCungCap,
                              @RequestParam(name = "priceMin", required = false) Double priceMin,
                              @RequestParam(name = "priceMax", required = false) Double priceMax,
                              RedirectAttributes redirectAttributes
    ) {

        List<DanhMuc>danhMucs= (danhMuc!=null)?danhMucService.findAllById(danhMuc):danhMucService.findAll();
        List<NhaCungCap>nhaCungCaps=(nhaCungCap!=null)?nhaCungCapService.findAllById(nhaCungCap):nhaCungCapService.findAll();
        List<LapTop> lapTops;
        if (priceMin != null && priceMax != null) {
            lapTops = lapTopService.findByDanhMucInAndNccInAndPriceBetween(danhMucs, nhaCungCaps, priceMin, priceMax);
        } else {
            lapTops = lapTopService.findByDanhMucInAndNccIn(danhMucs, nhaCungCaps);
        }
        redirectAttributes.addAttribute("selectedDanhMuc", danhMuc);
        redirectAttributes.addAttribute("selectedNhaCungCap", nhaCungCap);
        redirectAttributes.addAttribute("priceMin", priceMin);
        redirectAttributes.addAttribute("priceMax", priceMax);
        redirectAttributes.addFlashAttribute("laptopstore", lapTops);
        model.addAttribute("cart",cartService.getAll());
        model.addAttribute("total",cartService.getAmountVND());
        return "redirect:/home/store";
    }
}
