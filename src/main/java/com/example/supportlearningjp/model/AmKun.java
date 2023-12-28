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
@Table(name = "han_tu_am_kun")
public class AmKun{
    @EmbeddedId
    private AmKunId amKunId;

}
