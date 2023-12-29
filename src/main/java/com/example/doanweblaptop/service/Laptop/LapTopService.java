package com.example.doanweblaptop.service.Laptop;

import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.LapTop;
import com.example.doanweblaptop.entity.NhaCungCap;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface LapTopService {
    List<LapTop> findAll();

    Optional<LapTop> findById(Long theId);

    LapTop save(LapTop theLaptop);

    void deleteById(Long theId);

    List<LapTop> findByNameContaining(String key);

    Page<LapTop> findAll(Integer page);

    Page<LapTop> findByNameContaining(String key, Integer page);

    List<LapTop> findByPriceBetween(double min, double max);

    Page<LapTop> findByPriceBetween(double min, double max, Integer page);

    List<LapTop> findByDanhMucIn(List<DanhMuc> danhmuc);

    List<LapTop> findByDanhMucInAndNccIn(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps);

    List<LapTop> findByDanhMucInAndNccInAndPriceBetween(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps, Double priceMin, Double priceMax);

    Page<LapTop> findByDanhMucInAndNccIn(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps, Integer page);

    Page<LapTop> findByDanhMucInAndNccInAndPriceBetween(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps, Double priceMin, Double priceMax, Integer page);
}


