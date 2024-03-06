package com.example.gemeente.service;

import com.example.gemeente.model.Gemeente;
import com.example.gemeente.model.GemeenteJson;
import com.example.gemeente.model.Provincie;
import com.example.gemeente.repository.GemeenteRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GemeenteService {

  private final GemeenteRepository gemeenteRepository;
  private final ProvincieService provincieService;

  public GemeenteService(GemeenteRepository gemeenteRepository, ProvincieService provincieService) {
    this.gemeenteRepository = gemeenteRepository;
    this.provincieService = provincieService;
  }

  @PostConstruct
  public void init() throws IOException {
    if (gemeenteRepository.count() == 0) {
      InputStream inputStream = new ClassPathResource("gemeenten.json").getInputStream();
      ObjectMapper mapper = new ObjectMapper();
      List<GemeenteJson> gemeentenJson = mapper.readValue(inputStream, new TypeReference<>() {});
      List<Gemeente> gemeenten = new ArrayList<>();

      for (GemeenteJson gemeenteJson : gemeentenJson) {
        if (!gemeenteRepository.existsByGemeente(gemeenteJson.getGemeente())) {
          Provincie provincie = provincieService.getProvincieByName(gemeenteJson.getProvincie());
          Gemeente gemeente = new Gemeente();
          gemeente.setId(gemeente.getId());
          gemeente.setGemeente(gemeenteJson.getGemeente());
          gemeente.setProvincie(provincie);
          gemeente.setInwoners(gemeenteJson.getInwoners());
          provincie.getGemeenten().add(gemeente);
          gemeenten.add(gemeente);
        }
      }

      gemeenteRepository.saveAll(gemeenten);
    }
  }

  public List<Gemeente> getAllGemeenten() {
    return gemeenteRepository.findAll();
  }

  public Gemeente getGemeenteByGemeente(String gemeente) {
    return gemeenteRepository.findByGemeente(gemeente)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gemeente niet gevonden"));
  }

  public Gemeente getGemeenteById(int id) {
    return gemeenteRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gemeente niet gevonden"));
  }

  public List<Gemeente> getGemeentenGesorteerdOpInwoners() {
    return gemeenteRepository.findAllByOrderByInwonersAsc();
  }

  public List<Gemeente> getGemeentenGesorteerdAlfabetisch() {
    return gemeenteRepository.findAllByOrderByGemeenteAsc();
  }
}
