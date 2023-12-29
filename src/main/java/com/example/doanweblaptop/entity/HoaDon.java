package com.example.doanweblaptop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ngaydat")
    private Date ngayDat;
    @Column(name = "diachi")
    private String diachi;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "ghichu")
    private String ghiChu;
    @OneToMany(mappedBy = "id.hoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDons;
    @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User user;
    @Column(name = "custom_name")
    private String customName;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;
}
