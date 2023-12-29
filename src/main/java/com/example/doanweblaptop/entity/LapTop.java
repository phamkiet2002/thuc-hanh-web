package com.example.doanweblaptop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "laptop")
public class LapTop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "type")
    private String type;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;

    @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_ncc")
    private NhaCungCap ncc;

    @OneToMany(mappedBy = "lapTop",
            cascade =CascadeType.ALL)
    private List<DanhGia> danhGias;

    @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_danhmuc")
    private DanhMuc danhMuc;

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
