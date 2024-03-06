package com.example.gemeente.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "_Provincie")
public class Provincie {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column(nullable = false, unique = true)
  private String provincie;
  @Column(nullable = false, unique = true)
  private String hoofdstad;
  @Column(nullable = false)
  private int oppervlakte;

  @JsonManagedReference
  @OneToMany(mappedBy = "provincie")
  private List<Gemeente> gemeenten = new ArrayList<>();
}
