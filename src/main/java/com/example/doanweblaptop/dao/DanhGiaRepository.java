package com.example.doanweblaptop.dao;

import com.example.doanweblaptop.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanhGiaRepository extends JpaRepository<DanhGia,Long> {

    List<DanhGia> findByLapTopId(Long id);
    List<DanhGia> findByUserId(Long id);
}
