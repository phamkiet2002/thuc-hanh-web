package com.example.doanweblaptop.service.DanhMuc;

import com.example.doanweblaptop.dao.DanhMucRepository;
import com.example.doanweblaptop.entity.DanhGia;
import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.LapTop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanhMucServiceImpl implements DanhMucService{
    private DanhMucRepository danhMucRepository;
    @Autowired
    public DanhMucServiceImpl(DanhMucRepository danhMucRepository) {
        this.danhMucRepository = danhMucRepository;
    }

    @Override
    public List<DanhMuc> findAll() {
        return danhMucRepository.findAll();
    }

    @Override
    public DanhMuc findById(Long id) {
        Optional<DanhMuc> optional = danhMucRepository.findById(id);
        DanhMuc danhmuc = null;
        if (optional.isPresent()){
            danhmuc = optional.get();
        }
        else
            throw new RuntimeException("Khong tim thay " + id + "nay");
        return danhmuc;
    }

    @Override
    public DanhMuc save(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public void deleteById(Long id) {
        danhMucRepository.deleteById(id);
    }

    @Override
    public List<DanhMuc> findByIdIn(List<Long> list) {
        return danhMucRepository.findByIdIn(list);
    }
    @Override
    public List<DanhMuc> findAllById(List<Long> list) {
        return danhMucRepository.findAllById(list);
    }

    @Override
    public List<DanhMuc> findByName(String key) {
        return danhMucRepository.findByName(key);
    }
}
