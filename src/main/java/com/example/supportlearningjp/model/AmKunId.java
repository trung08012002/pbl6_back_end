package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class AmKunId implements Serializable {
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_han_tu",referencedColumnName = "id")
    private Kanji kanji;
    @Column(name = "am_on")
    private String amOn;


}
