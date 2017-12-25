package com.rezervacija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rezervacija.models.Rezervacija;

@Repository
public interface RezervacijaRepository  extends JpaRepository<Rezervacija, Long>{
	public List<Rezervacija> findByIdKorisnika(Long idKorisnika);
	//public List<Rezervacija> findByDatumProjekcijeAndNazivFilmaAndNazivBioskopaAndOznakaSale();
	
}
