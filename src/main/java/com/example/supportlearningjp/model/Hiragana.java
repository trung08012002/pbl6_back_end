package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="hiragana")
public class Hiragana {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true, nullable=false)
    private Integer id;

    @Column(name="img")
    private String img;

    @Column(name="chu_cai")
    private String chu_cai;

    @Column(name = "phien_am")
    private String phien_am;
}
