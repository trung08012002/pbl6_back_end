package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class ExampleDetailId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detail",nullable = false,referencedColumnName = "id")
    private DetailWord detailWord;
    @Column(name = "sentence")
    private String sentence;

}
