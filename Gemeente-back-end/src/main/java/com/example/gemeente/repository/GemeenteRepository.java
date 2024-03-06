package com.example.gemeente.repository;

import com.example.gemeente.model.Gemeente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GemeenteRepository extends JpaRepository<Gemeente, Integer> {

  boolean existsByGemeente(String gemeente);

  Optional<Gemeente> findByGemeente(String gemeente);
  Optional<Gemeente> findById(int id);
  List<Gemeente> findAllByOrderByInwonersAsc();
  List<Gemeente> findAllByOrderByGemeenteAsc();
}
