package com.example.doanweblaptop.controller.Admin;

import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.service.DanhMuc.DanhMucService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/danhmuc")
public class DanhMucController {
    private DanhMucService danhMucService;

    public DanhMucController(DanhMucService danhMucService) {
        this.danhMucService = danhMucService;
    }

    @GetMapping("/ShowFormThemSuaDanhMuc")
    public String ShowFormThemSua(Model model){
        DanhMuc danhmuc = new DanhMuc();
        //List<DanhMuc> danhmuc ;
//        if (key!=null)
//        {
//            danhmuc = danhMucService.findByName(key);
//        }
        //else danhmuc = danhMucService.findAll();

        model.addAttribute("danhmuc", danhmuc);
        return "form/formThemSuaDanhMuc";
    }

    @PostMapping("/save")
    public String saveDanhmuc(@ModelAttribute("danhmuc") DanhMuc id){
        danhMucService.save(id);
        return "redirect:/admin/listdanhmuc";
    }

    @GetMapping("/delete")
    public String deletedanhmuc(@RequestParam("danhmucid") Long id, Model model){
        danhMucService.deleteById(id);
        return "redirect:/admin/listdanhmuc";
    }

    @GetMapping("/ShowFormUpdateDanhMuc")
    public String showUpDate(@RequestParam("danhmucid") Long id, Model model){
        DanhMuc danhmuc = danhMucService.findById(id);
        model.addAttribute("danhmuc", danhmuc);
        return "form/formThemSuaDanhMuc";
    }
}
