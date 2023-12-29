package com.example.doanweblaptop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "sdt")
    private String sdt;
    @OneToMany(mappedBy = "ncc",
                cascade ={CascadeType.MERGE,CascadeType.DETACH,
                        CascadeType.PERSIST,CascadeType.REFRESH})
    private List<LapTop> lapTops;

    @Override
    public String toString() {
        return id + " " + name;
    }
}
