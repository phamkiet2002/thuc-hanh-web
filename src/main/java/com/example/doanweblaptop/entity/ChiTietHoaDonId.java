package com.example.doanweblaptop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietHoaDonId implements Serializable {

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_hoadon")
    private HoaDon hoaDon;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_laptop")
    private LapTop lapTop;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public LapTop getLapTop() {
        return lapTop;
    }

    public void setLapTop(LapTop lapTop) {
        this.lapTop = lapTop;
    }

    public ChiTietHoaDonId() {
    }

    public ChiTietHoaDonId(HoaDon hoaDon, LapTop lapTop) {
        this.hoaDon = hoaDon;
        this.lapTop = lapTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDonId that = (ChiTietHoaDonId) o;
        return Objects.equals(hoaDon, that.hoaDon) &&
                Objects.equals(lapTop, that.lapTop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDon, lapTop);
    }

}
