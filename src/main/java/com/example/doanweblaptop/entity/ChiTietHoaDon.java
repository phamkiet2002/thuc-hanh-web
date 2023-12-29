package com.example.doanweblaptop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chitiethoadon")
public class ChiTietHoaDon {
    @EmbeddedId
    private ChiTietHoaDonId id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private Double price;

    //    @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.DETACH,
//            CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinColumn(name = "id_hoadon")
//    private HoaDon hoaDon;
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "chitiethoadons_laptops",
//            joinColumns = @JoinColumn(name = "id_chitiethoadon"),
//            inverseJoinColumns = @JoinColumn(name = "id_laptop"))
//    private Collection<LapTop> lapTops;
    public String getFormattedPrice() {
        try {
            if (this.price != null) {
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                return currencyFormat.format(this.price);
            } else {
                return "0đ";
            }
        } catch (Exception e) {
            return "Error formatting price";
        }
    }
}
