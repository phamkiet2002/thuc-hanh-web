package com.example.doanweblaptop.controller.Admin;

import com.example.doanweblaptop.entity.HoaDon;
import com.example.doanweblaptop.service.HoaDon.HoaDonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {
    private HoaDonService hoaDonService;

    public HoaDonController(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }


}
