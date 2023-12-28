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
public class MeaningDetailId implements Serializable {
    @Column(name = "meaning")
    private String meaningDetail;
    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_detail",referencedColumnName = "id",nullable = false)
    private DetailWord detailWord;

}
