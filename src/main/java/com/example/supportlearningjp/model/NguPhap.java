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
@Table(name="ngu_phap")
public class NguPhap {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "level")
    private Level level;

    @Column(name = "ngu_phap")
    private String nguphap;

    @Column(name = "meaning")
    private String meaning;

    @ManyToOne
    @JoinColumn(name = "type_ngu_phap")
    private TypeNguPhap type;

}
