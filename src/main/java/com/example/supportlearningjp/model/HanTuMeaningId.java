package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@NoArgsConstructor

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class HanTuMeaningId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_han_tu")
    private Kanji kanji;
    @Column(name = "meaning")
    private String meaning;

}
