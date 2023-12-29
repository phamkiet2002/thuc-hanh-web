package com.example.doanweblaptop.dao;

import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.LapTop;
import com.example.doanweblaptop.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LapTopRepository  extends JpaRepository<LapTop,Long> {
    List<LapTop> findByNameContaining(String key);
    List<LapTop> findByPriceBetween(double min, double max);
    List<LapTop> findByDanhMucInAndNccIn(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps);
    List<LapTop> findByDanhMucInAndNccInAndPriceBetween(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps,Double priceMin,Double priceMax);
    List<LapTop> findByDanhMucIn(List<DanhMuc> danhmuc);
}
