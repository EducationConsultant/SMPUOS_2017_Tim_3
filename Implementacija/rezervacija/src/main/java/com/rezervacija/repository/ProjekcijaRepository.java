package com.rezervacija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rezervacija.models.Projekcija;

@Repository
public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long>{

	//public List<Projekcija> findByDatumProjekcijeAndNazivFilmaAndNazivBioskopaAndOznakaSaleAndBrojAktivnihRezervacija();
}
