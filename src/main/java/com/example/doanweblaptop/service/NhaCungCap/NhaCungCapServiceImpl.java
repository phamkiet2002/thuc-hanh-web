package com.example.doanweblaptop.service.NhaCungCap;

import com.example.doanweblaptop.dao.NhaCungCapRepository;
import com.example.doanweblaptop.entity.NhaCungCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
    private NhaCungCapRepository nhaCungCapRepository;

    @Autowired
    public NhaCungCapServiceImpl(NhaCungCapRepository nhaCungCapRepository) {
        this.nhaCungCapRepository = nhaCungCapRepository;
    }

    @Override
    public List<NhaCungCap> findAll() {
        return nhaCungCapRepository.findAll();
    }

    @Override
    public NhaCungCap findById(Long id) {
        Optional<NhaCungCap> optional = nhaCungCapRepository.findById(id);
        NhaCungCap ncc = null;
        if (optional.isPresent()){
            ncc = optional.get();
        }
        else
            throw new RuntimeException("Khong tim thay " + id + "nay");
        return ncc;
    }
    @Override
    public List<NhaCungCap> findAllById(List<Long> list) {
        return nhaCungCapRepository.findAllById(list);
    }
    @Override
    public NhaCungCap save(NhaCungCap ncc) {
        return nhaCungCapRepository.save(ncc);
    }

    @Override
    public void deleteById(Long id) {
        nhaCungCapRepository.deleteById(id);
    }

    @Override
    public boolean existsNhaCungCapByIdAndLapTopsIsNotNull(Long id) {
        return nhaCungCapRepository.existsNhaCungCapByIdAndLapTopsIsNotNull(id);
    }
}
