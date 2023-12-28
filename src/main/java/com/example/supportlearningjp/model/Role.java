package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    List<User> users=new ArrayList<>();
}
