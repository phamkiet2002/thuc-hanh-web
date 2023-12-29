package com.example.doanweblaptop.service.NhaCungCap;

import com.example.doanweblaptop.entity.NhaCungCap;

import java.util.List;

public interface NhaCungCapService {
    List<NhaCungCap> findAll();
    NhaCungCap findById(Long id);
    List<NhaCungCap> findAllById(List<Long> list);
    NhaCungCap save(NhaCungCap ncc);
    void deleteById(Long id);

    boolean existsNhaCungCapByIdAndLapTopsIsNotNull(Long id);
}
