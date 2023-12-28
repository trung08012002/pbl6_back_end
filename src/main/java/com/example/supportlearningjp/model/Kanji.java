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
@Table(name = "kanji")
public class Kanji {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique=true, nullable=false)
  private Integer id;
  @Column(name = "tu")
  private String tu;
  @Column(name = "number_of_stroke")
  private Integer numberOfStroke;
  @OneToMany(mappedBy ="hanTuMeaningId.kanji",cascade = CascadeType.ALL)
  private List<HanTuMeaning> hanTuMeanings=new ArrayList<>();

    @OneToMany(mappedBy ="amOnId.kanji",cascade = CascadeType.ALL)
    private Set<AmOn> amOn=new HashSet<>();
  @OneToMany(mappedBy ="amKunId.kanji",cascade = CascadeType.ALL)
  private Set<AmKun> amKun=new HashSet<>();
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "kanji_han_viet",
          joinColumns = @JoinColumn(name = "kanji_id"),
          inverseJoinColumns = @JoinColumn(name="han_viet_id")
  )
  Set<HanViet> hanViets= new HashSet<>();

}
