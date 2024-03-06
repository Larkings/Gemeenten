package com.example.gemeente.controller;

import com.example.gemeente.model.Gemeente;
import com.example.gemeente.model.Provincie;
import com.example.gemeente.service.GemeenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gemeenten")
public class GemeenteController {

  private final GemeenteService gemeenteService;

  public GemeenteController(GemeenteService gemeenteService) {
    this.gemeenteService = gemeenteService;
  }

  @GetMapping
  public ResponseEntity<List<Gemeente>> getAllGemeenten() {
    List<Gemeente> gemeenten = gemeenteService.getAllGemeenten();
    gemeenten.forEach(g -> g.getProvincie().getGemeenten().clear());
    return ResponseEntity.ok(gemeenten);
  }

  @GetMapping("/naam/{gemeente}")
  public ResponseEntity<Gemeente> getGemeenteByGemeente(@PathVariable String gemeente) {
    Gemeente g = gemeenteService.getGemeenteByGemeente(gemeente);
    g.getProvincie().getGemeenten().clear();
    return ResponseEntity.ok(g);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Gemeente> getGemeenteById(@PathVariable int id) {
    Gemeente g = gemeenteService.getGemeenteById(id);
    g.getProvincie().getGemeenten().clear();
    return ResponseEntity.ok(g);
  }

  @GetMapping("/sorteer/inwoners")
  public ResponseEntity<List<Gemeente>> getGemeentenGesorteerdOpInwoners() {
    List<Gemeente> gemeenten = gemeenteService.getGemeentenGesorteerdOpInwoners();
    gemeenten.forEach(g -> g.getProvincie().getGemeenten().clear());
    return ResponseEntity.ok(gemeenten);
  }

  @GetMapping("/sorteer/alfabetisch")
  public ResponseEntity<List<Gemeente>> getGemeentenGesorteerdAlfabetisch() {
    List<Gemeente> gemeenten = gemeenteService.getGemeentenGesorteerdAlfabetisch();
    gemeenten.forEach(g -> g.getProvincie().getGemeenten().clear());
    return ResponseEntity.ok(gemeenten);
  }
}
