package com.example.doanweblaptop.service.DanhGia;

import com.example.doanweblaptop.entity.DanhGia;
import com.example.doanweblaptop.entity.DanhMuc;

import java.util.List;

public interface DanhGiaService {
    List<DanhGia> findAll();
    DanhGia findById(Long id);
    DanhGia save(DanhGia danhMuc);
    void deleteById(Long id);
    List<DanhGia> findByLapTopId(Long id);
    List<DanhGia> findByUserId(Long id);

}
