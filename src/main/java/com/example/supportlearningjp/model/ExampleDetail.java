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
@Table(name = "example_detail")
public class ExampleDetail {
  @EmbeddedId
  private ExampleDetailId exampleDetailId;
  @Column(name = "meaning")
  private String meaning;

}
