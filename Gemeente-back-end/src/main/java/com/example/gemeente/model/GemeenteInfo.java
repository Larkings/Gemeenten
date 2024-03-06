package com.example.gemeente.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GemeenteInfo {
  private Gemeente gemeente;
  private String provincieNaam;
  private String provincieHoofdstad;
  private int provincieOppervlakte;
}
