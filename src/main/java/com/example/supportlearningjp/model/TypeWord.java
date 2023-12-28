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
@Table(name = "type_word")
public class TypeWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true, nullable=false)
    private Integer id;
    @Column(name = "type")
    private String type;
    @ManyToMany(mappedBy = "typeWords",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<DetailWord> detailWords=new ArrayList<>();

}
