package com.example.doanweblaptop.service.HoaDon;

import com.example.doanweblaptop.dao.HoaDonRepository;
import com.example.doanweblaptop.entity.HoaDon;
import com.example.doanweblaptop.entity.NhaCungCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService{
    private HoaDonRepository hoaDonRepository;
    @Autowired
    public HoaDonServiceImpl(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon save(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon findById(Long id) {
            Optional<HoaDon> optional = hoaDonRepository.findById(id);
            HoaDon hoaDon = null;
            if (optional.isPresent()){
                hoaDon = optional.get();
            }
            else
                throw new RuntimeException("Khong tim thay " + id + "nay");
            return hoaDon;
    }
}
