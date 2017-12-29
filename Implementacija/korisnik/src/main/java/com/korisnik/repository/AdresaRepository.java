package com.korisnik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korisnik.models.Adresa;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Long> {

	public List<Adresa> findByGeoDuzinaAndGeoSirina(float geoDuzina, float geoSirina);
	public Adresa findByNazivNaseljenogMestaAndNazivUliceAndBroj(String nazivNaseljenogMesta, String nazivUlice, int broj);
	
}
