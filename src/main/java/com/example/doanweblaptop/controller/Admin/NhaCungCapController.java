package com.example.doanweblaptop.controller.Admin;

import com.example.doanweblaptop.entity.NhaCungCap;
import com.example.doanweblaptop.service.NhaCungCap.NhaCungCapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequestMapping("/nhacungcap")
public class NhaCungCapController {
    private NhaCungCapService nhaCungCapService;

    public NhaCungCapController(NhaCungCapService nhaCungCapService) {
        this.nhaCungCapService = nhaCungCapService;
    }


    @GetMapping("/ShowFormThemSuaNcc")
    public String showForm(Model model) {
        NhaCungCap ncc = new NhaCungCap();
        model.addAttribute("nhacungcap", ncc);
        return "form/formThemSuaNcc";
    }

    @PostMapping("/save")
    public String savencc(@ModelAttribute("nhacungcap") NhaCungCap id) {
        nhaCungCapService.save(id);
        return "redirect:/admin/listncc";
    }

    @GetMapping("/delete")
    public String deleteNcc(Model model, @RequestParam("nhacuncapid") Long id,
                            Exception e) {
        if (nhaCungCapService.existsNhaCungCapByIdAndLapTopsIsNotNull(id)) {
            return "redirect:/admin/listncc?error";
        }

        nhaCungCapService.deleteById(id);
        return "redirect:/admin/listncc";
    }

    @GetMapping("/ShowFormUpDateNcc")
    public String ShowUpdate(@RequestParam("nhacungcapid") Long id, Model model) {
        NhaCungCap ncc = nhaCungCapService.findById(id);
        model.addAttribute("nhacungcap", ncc);
        return "form/formThemSuaNcc";
    }
}
