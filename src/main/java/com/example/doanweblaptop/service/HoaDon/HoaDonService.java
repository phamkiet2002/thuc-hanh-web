package com.example.doanweblaptop.service.HoaDon;

import com.example.doanweblaptop.entity.HoaDon;

import java.util.List;

public interface HoaDonService {
    List<HoaDon> findAll();
    HoaDon save(HoaDon hoaDon);
    HoaDon findById(Long id);
}
