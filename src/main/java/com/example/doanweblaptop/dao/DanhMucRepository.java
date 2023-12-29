package com.example.doanweblaptop.dao;

import com.example.doanweblaptop.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanhMucRepository  extends JpaRepository<DanhMuc,Long> {
    List<DanhMuc> findByIdIn(List<Long> list);

    List<DanhMuc> findByName(String key);

}
