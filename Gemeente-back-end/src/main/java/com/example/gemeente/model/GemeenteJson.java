package com.example.gemeente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GemeenteJson {
  private int id;
  private String gemeente;
  private String provincie;
  private int inwoners;
}
