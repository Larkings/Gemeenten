package com.example.gemeente.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "_Gemeente")
public class Gemeente {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column(nullable = false, unique = true)
  private String gemeente;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "provincie_id",nullable = false)
  private Provincie provincie;

  private int inwoners;
}

