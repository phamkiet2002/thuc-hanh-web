package com.example.doanweblaptop.service.DanhMuc;

import com.example.doanweblaptop.entity.DanhGia;
import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.LapTop;
import com.example.doanweblaptop.entity.NhaCungCap;

import java.util.List;

public interface DanhMucService {
    List<DanhMuc> findAll();
    DanhMuc findById(Long id);
    DanhMuc save(DanhMuc danhMuc);
    void deleteById(Long id);
    List<DanhMuc> findByIdIn(List<Long> list);
    List<DanhMuc> findAllById(List<Long> list);

    List<DanhMuc> findByName(String key);
}
