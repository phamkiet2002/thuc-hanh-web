package com.example.doanweblaptop.dao;

import com.example.doanweblaptop.entity.ChiTietHoaDon;
import com.example.doanweblaptop.entity.ChiTietHoaDonId;
import com.example.doanweblaptop.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, ChiTietHoaDonId> {
    List<ChiTietHoaDon> findChiTietHoaDonsById_HoaDon(HoaDon hoaDon);


   List<ChiTietHoaDon> countByQuantity(ChiTietHoaDon chiTietHoaDon);
}
