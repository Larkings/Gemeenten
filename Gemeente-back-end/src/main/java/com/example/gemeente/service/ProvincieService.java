package com.example.gemeente.service;

import com.example.gemeente.model.Provincie;
import com.example.gemeente.repository.ProvincieRepository;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvincieService {
  private final ProvincieRepository provincieRepository;
  private final Map<String, Provincie> provincieMap = new HashMap<>();
  public ProvincieService(ProvincieRepository provincieRepository) {
    this.provincieRepository = provincieRepository;
  }
  public Provincie getProvincieByName(String name) {
    return provincieMap.get(name);
  }

  @PostConstruct
  public void init() {
    if (provincieRepository.count() == 0) {
      List<Provincie> provincies = new ArrayList<>();
      try {
        File file = new ClassPathResource("provincies.csv").getFile();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
          if (!line.startsWith("PROVINCIE")) {
            Provincie provincie = getProvincie(line);
            provincies.add(provincie);
          }
        }
      } catch (IOException e) {
        throw new RuntimeException("Fout bij het lezen van provincies.csv", e);
      }
      provincieRepository.saveAll(provincies);
      provincies.forEach(provincie -> provincieMap.put(provincie.getProvincie(), provincie));
    }
  }
  
  @NotNull
  private static Provincie getProvincie(String line) {
    String[] details = line.split(",");
    if (details.length < 3) {
      throw new IllegalArgumentException("Ongeldig formaat voor provinciegegevens: " + line);
    }
    Provincie provincie = new Provincie();
    provincie.setProvincie(details[0]);
    provincie.setHoofdstad(details[1]);
    try {
      provincie.setOppervlakte(Integer.parseInt(details[2]));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Ongeldig formaat voor oppervlakte: " + details[2], e);
    }
    return provincie;
  }
}
