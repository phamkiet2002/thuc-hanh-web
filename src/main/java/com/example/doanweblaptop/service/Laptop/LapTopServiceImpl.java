package com.example.doanweblaptop.service.Laptop;

import com.example.doanweblaptop.dao.DanhMucRepository;
import com.example.doanweblaptop.dao.LapTopRepository;
import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.LapTop;
import com.example.doanweblaptop.entity.NhaCungCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LapTopServiceImpl implements LapTopService {
    private LapTopRepository lapTopRepository;
    private DanhMucRepository danhMucRepository;

    @Autowired
    public LapTopServiceImpl(LapTopRepository lapTopRepository, DanhMucRepository danhMucRepository) {
        this.lapTopRepository = lapTopRepository;
        this.danhMucRepository = danhMucRepository;
    }

    @Override
    public List<LapTop> findAll() {
        return lapTopRepository.findAll();
    }

    @Override
    public Optional<LapTop> findById(Long theId) {
        if (theId == null) theId = 1L;
        return lapTopRepository.findById(theId);
    }

    @Override
    public LapTop save(LapTop theLaptop) {
        return lapTopRepository.save(theLaptop);
    }

    @Override
    public void deleteById(Long theId) {
        lapTopRepository.deleteById(theId);
    }

    @Override
    public List<LapTop> findByNameContaining(String key) {
        return lapTopRepository.findByNameContaining(key);
    }

    @Override
    public Page<LapTop> findAll(Integer page) {
        Pageable pages = PageRequest.of(page - 1, 9);
        return lapTopRepository.findAll(pages);
    }

    @Override
    public Page<LapTop> findByNameContaining(String key, Integer page) {
        List<LapTop> lapTop = lapTopRepository.findByNameContaining(key);
        Pageable pageable = PageRequest.of(page - 1, 9);
        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > lapTop.size() ? lapTop.size() : pageable.getOffset() + pageable.getPageSize());
        lapTop = lapTop.subList(start, end);
        return new PageImpl<LapTop>(lapTop, pageable, lapTopRepository.findByNameContaining(key).size());
    }

    @Override
    public List<LapTop> findByPriceBetween(double min, double max) {
        List<LapTop> lapTops = lapTopRepository.findByPriceBetween(min, max);
        if (lapTops.isEmpty()){
            throw new RuntimeException("Không tìm thấy thông tin với khoảng giá đã nhập");
        }
        return lapTops;
    }

    @Override
    public Page<LapTop> findByPriceBetween(double min, double max, Integer page) {
        List<LapTop> lapTop = lapTopRepository.findByPriceBetween(min,max);
        Pageable pageable = PageRequest.of(page - 1, 9);
        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > lapTop.size() ? lapTop.size() : pageable.getOffset() + pageable.getPageSize());
        lapTop = lapTop.subList(start, end);
        return new PageImpl<LapTop>(lapTop, pageable, lapTopRepository.findByPriceBetween(min, max).size());
    }

    @Override
    public List<LapTop> findByDanhMucIn(List<DanhMuc> danhmuc) {
        return lapTopRepository.findByDanhMucIn(danhmuc);
    }
    @Override
    public Page<LapTop> findByDanhMucInAndNccIn(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps, Integer page) {
        List<LapTop> lapTops=lapTopRepository.findByDanhMucInAndNccIn(danhMucs,nhaCungCaps);
        Pageable pageable=PageRequest.of(page-1,9);
        Integer start=(int)pageable.getOffset();
        Integer end=(int) ((pageable.getOffset()+pageable.getPageSize())>lapTops.size()?lapTops.size():pageable.getOffset()+pageable.getPageSize());
        lapTops=lapTops.subList(start,end);
        return new PageImpl<LapTop>(lapTops,pageable,lapTopRepository.findByDanhMucInAndNccIn(danhMucs,nhaCungCaps).size());
    }

    @Override
    public Page<LapTop> findByDanhMucInAndNccInAndPriceBetween(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps, Double priceMin, Double priceMax, Integer page) {
        List<LapTop> lapTops=lapTopRepository.findByDanhMucInAndNccInAndPriceBetween(danhMucs,nhaCungCaps,priceMin,priceMax);
        Pageable pageable=PageRequest.of(page-1,9);
        Integer start=(int)pageable.getOffset();
        Integer end=(int) ((pageable.getOffset()+pageable.getPageSize())>lapTops.size()?lapTops.size():pageable.getOffset()+pageable.getPageSize());
        lapTops=lapTops.subList(start,end);
        return new PageImpl<LapTop>(lapTops,pageable,lapTopRepository.findByDanhMucInAndNccInAndPriceBetween(danhMucs,nhaCungCaps,priceMin,priceMax).size());
    }
    @Override
    public List<LapTop> findByDanhMucInAndNccIn(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps) {
        return lapTopRepository.findByDanhMucInAndNccIn(danhMucs,nhaCungCaps);
    }

    @Override
    public List<LapTop> findByDanhMucInAndNccInAndPriceBetween(List<DanhMuc> danhMucs, List<NhaCungCap> nhaCungCaps, Double priceMin, Double priceMax) {
        return lapTopRepository.findByDanhMucInAndNccInAndPriceBetween(danhMucs,nhaCungCaps,priceMin,priceMax);
    }

}
