package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detail_word")
public class DetailWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true, nullable=false)
    private  Integer id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "word_jp_id",referencedColumnName = "id",nullable = false)
    private  WordJP wordJP;
    @OneToMany(mappedBy = "meaningDetailId.detailWord",cascade = CascadeType.ALL)
    private List<MeaningDetail> meaningDetails=new ArrayList<>();
    @OneToMany(mappedBy = "exampleDetailId.detailWord",cascade = CascadeType.ALL)
    private List<ExampleDetail> exampleDetails=new ArrayList<>();
    @OneToMany(mappedBy = "noteDetailId.detailWord",cascade = CascadeType.ALL)
    private  List<NoteDetail> noteDetails=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="detailWordType",
            joinColumns = @JoinColumn(name = "detail_word_id"),
            inverseJoinColumns = @JoinColumn(name="type_word_id")
    )
    private List<TypeWord> typeWords=new ArrayList<>();

    @Override
    public String toString() {
        return "DetailWord{" +
                "id=" + id +
                ", meaningDetails=" + meaningDetails +
                ", exampleDetails=" + exampleDetails +
                ", noteDetails=" + noteDetails +
                ", typeWords=" + typeWords +
                '}';
    }
}
