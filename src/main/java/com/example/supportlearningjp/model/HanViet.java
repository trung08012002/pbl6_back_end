package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="han_viet")
public class HanViet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true, nullable=false)
    private Integer id;
    @Column(name="tu")
    private String tu;


    @ManyToMany(mappedBy = "hanViets",cascade = CascadeType.ALL)
    private List<Kanji> kanjis=new ArrayList<>();

}
