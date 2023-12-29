package com.example.doanweblaptop.dao;


import com.example.doanweblaptop.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhaCungCapRepository  extends JpaRepository<NhaCungCap,Long> {
    List<NhaCungCap> findByIdIn(List<Long> ids);

    boolean existsNhaCungCapByIdAndLapTopsIsNotNull(Long id);
}
