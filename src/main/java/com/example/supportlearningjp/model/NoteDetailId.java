package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class NoteDetailId implements Serializable {
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name="id_detail",referencedColumnName = "id",nullable = false)
    private DetailWord detailWord;
   @Column(name="note")
    private  String note;
}
