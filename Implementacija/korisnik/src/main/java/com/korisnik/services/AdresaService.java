package com.korisnik.services;

import java.util.List;

import com.korisnik.models.Adresa;

public interface AdresaService {

	public Adresa findOne(Long id);

	public List<Adresa> findByGeoDuzinaAndGeoSirina(float geoDuzina, float geoSirina);

}
