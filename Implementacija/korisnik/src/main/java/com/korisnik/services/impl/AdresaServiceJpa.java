package com.korisnik.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korisnik.models.Adresa;
import com.korisnik.repository.AdresaRepository;
import com.korisnik.services.AdresaService;

@Service
public class AdresaServiceJpa implements AdresaService {

	@Autowired
	private AdresaRepository adresaRepository;

	@Override
	public Adresa findOnde(Long id) {
		return adresaRepository.findOne(id);
	}

}
