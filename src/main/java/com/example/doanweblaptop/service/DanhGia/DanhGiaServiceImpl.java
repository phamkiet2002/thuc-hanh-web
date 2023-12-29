package com.example.doanweblaptop.service.DanhGia;

import com.example.doanweblaptop.dao.DanhGiaRepository;
import com.example.doanweblaptop.entity.DanhGia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanhGiaServiceImpl implements DanhGiaService{
    private DanhGiaRepository danhGiaRepository;

    @Autowired
    public DanhGiaServiceImpl(DanhGiaRepository danhGiaRepository) {
        this.danhGiaRepository = danhGiaRepository;
    }

    @Override
    public List<DanhGia> findAll() {
        return danhGiaRepository.findAll();
    }

    @Override
    public DanhGia findById(Long id) {
        Optional<DanhGia> optional = danhGiaRepository.findById(id);
        DanhGia danhgia = null;
        if (optional.isPresent()){
            danhgia = optional.get();
        }
        else
            throw new RuntimeException("Khong tim thay " + id + "nay");
        return danhgia;
    }

    @Override
    public DanhGia save(DanhGia danhMuc) {
        return danhGiaRepository.save(danhMuc);
    }

    @Override
    public void deleteById(Long id) {
        danhGiaRepository.deleteById(id);
    }

    @Override
    public List<DanhGia> findByLapTopId(Long id) {
        return danhGiaRepository.findByLapTopId(id);
    }

    @Override
    public List<DanhGia> findByUserId(Long id) {
        return danhGiaRepository.findByUserId(id);
    }
}
