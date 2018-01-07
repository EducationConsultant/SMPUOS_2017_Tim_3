package com.bioskop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioskop.models.Adresa;
import com.bioskop.repository.AdresaRepository;
import com.bioskop.services.AdresaService;



@Service
public class AdresaServiceJpa implements AdresaService {

	@Autowired
	private AdresaRepository adresaRepository;

	@Override
	public Adresa findOne(Long id) {
		return adresaRepository.findOne(id);
	}

	@Override
	public List<Adresa> findByGeoDuzinaAndGeoSirina(float geoDuzina, float geoSirina) {
		return adresaRepository.findByGeoDuzinaAndGeoSirina(geoDuzina, geoSirina);
	}

	@Override
	public Adresa findByNazivNaseljenogMestaAndNazivUliceAndBroj(String nazivNaseljenogMesta, String nazivUlice, int broj) {
		return adresaRepository.findByNazivNaseljenogMestaAndNazivUliceAndBroj(nazivNaseljenogMesta, nazivUlice, broj);
	}

}
