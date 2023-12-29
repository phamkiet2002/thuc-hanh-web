package com.example.doanweblaptop.service.ChiTietHoaDon;

import com.example.doanweblaptop.entity.ChiTietHoaDon;
import com.example.doanweblaptop.entity.HoaDon;

import java.util.List;

public interface ChiTietService {
    ChiTietHoaDon save(ChiTietHoaDon chiTietHoaDon);
    List<ChiTietHoaDon> findListChiTietByHoaDon(HoaDon hoaDon);
    public String getAmountVND(Double money);

    List<ChiTietHoaDon> countByQuantity(ChiTietHoaDon chiTietHoaDon);
}
