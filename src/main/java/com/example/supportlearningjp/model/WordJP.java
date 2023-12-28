package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="word_jp")

public class WordJP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true, nullable=false)
    private Integer id;
    @Column(name="tu")
    private String tu;
    @Column(name="pronounce")
    private String pronounce;
    @Column(name="hiragana")
    private String hiragana;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="wordjp_han_viet",
    joinColumns = {@JoinColumn(name="word_jp_id")},
            inverseJoinColumns = {@JoinColumn(name = "han_viet_id")}
    )
    List<HanViet> HanViets=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "wordJP")
    List<DetailWord> detailWords=new ArrayList<>();

    @Override
    public String toString() {
        return "WordJP{" +
                "id=" + id +
                ", tu='" + tu + '\'' +
                ", pronounce='" + pronounce + '\'' +
                ", hiragana='" + hiragana + '\'' +
                ", HanViets=" + HanViets +
                ", detailWords=" + detailWords +
                '}';
    }
}
