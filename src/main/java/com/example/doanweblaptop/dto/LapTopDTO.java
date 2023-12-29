package com.example.doanweblaptop.dto;


import com.example.doanweblaptop.entity.DanhGia;
import com.example.doanweblaptop.entity.DanhMuc;
import com.example.doanweblaptop.entity.NhaCungCap;
import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class LapTopDTO {
    private Long id;

    private String name;

    private Double price;

    private String type;

    private Integer quantity;

    private MultipartFile image;

    private String description;
    private NhaCungCap ncc;
    private DanhMuc danhMuc;
    private List<DanhGia>danhGias;

    public LapTopDTO() {
    }

    public LapTopDTO(Long id, String name, Double price, String type, Integer quantity, MultipartFile image, String description, NhaCungCap ncc, DanhMuc danhMuc, List<DanhGia> danhGias) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.ncc = ncc;
        this.danhMuc = danhMuc;
        this.danhGias = danhGias;
    }

    public List<DanhGia> getDanhGias() {
        return danhGias;
    }

    public void setDanhGias(List<DanhGia> danhGias) {
        this.danhGias = danhGias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NhaCungCap getNcc() {
        return ncc;
    }

    public void setNcc(NhaCungCap ncc) {
        this.ncc = ncc;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
}
