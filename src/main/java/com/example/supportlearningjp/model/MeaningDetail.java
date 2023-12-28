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
@Table(name = "meaning_detail")

public class MeaningDetail {
    @EmbeddedId
    private MeaningDetailId meaningDetailId;

    @Override
    public String toString() {
        return "MeaningDetail{" +
                "meaningDetailId=" + meaningDetailId +
                '}';
    }
}
