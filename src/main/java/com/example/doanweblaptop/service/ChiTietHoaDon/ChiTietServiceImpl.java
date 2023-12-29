package com.example.doanweblaptop.service.ChiTietHoaDon;

import com.example.doanweblaptop.dao.ChiTietHoaDonRepository;
import com.example.doanweblaptop.entity.ChiTietHoaDon;
import com.example.doanweblaptop.entity.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Service
public class ChiTietServiceImpl implements ChiTietService{
    ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    public ChiTietServiceImpl(ChiTietHoaDonRepository chiTietHoaDonRepository) {
        this.chiTietHoaDonRepository = chiTietHoaDonRepository;
    }

    @Override
    public ChiTietHoaDon save(ChiTietHoaDon chiTietHoaDon) {
        return chiTietHoaDonRepository.save(chiTietHoaDon);
    }

    @Override
    public List<ChiTietHoaDon> findListChiTietByHoaDon(HoaDon hoaDon) {
        return chiTietHoaDonRepository.findChiTietHoaDonsById_HoaDon(hoaDon);
    }


    @Override
    public String getAmountVND(Double money) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(money);
    }

    @Override
    public List<ChiTietHoaDon> countByQuantity(ChiTietHoaDon chiTietHoaDon) {
        return null;
    }


}
