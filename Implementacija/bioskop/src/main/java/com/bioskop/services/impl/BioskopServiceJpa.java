package com.bioskop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;
import com.bioskop.repository.AdresaRepository;
import com.bioskop.repository.BioskopRepository;
import com.bioskop.repository.SalaRepository;
import com.bioskop.services.BioskopService;

@Service
public class BioskopServiceJpa implements BioskopService {

	@Autowired
	private BioskopRepository repository;
	
	@Autowired 
	private AdresaRepository adresaRepository;
	
	
	@Override
	public Bioskop findOne(Long id) {
		return  repository.findOne(id);
	}

	@Override
	public List<Bioskop> find() {
		return repository.findAll();
	}

	@Override
	public Bioskop save(Bioskop bioskop) {
		Adresa sacuvanaAdresa = adresaRepository.save(bioskop.getAdresa());
		bioskop.setAdresa(sacuvanaAdresa);
		return repository.save(bioskop);
	}
	


	@Override
	public void delete(Bioskop bioskop) {
		repository.delete(bioskop);
		
	}

	@Override
	public void deleteById(Long id) {
		repository.delete(id);
	}

	@Override
	public Bioskop update(Bioskop bioskop, Long id) {
		Bioskop bioskopZaIzmenu = repository.findOne(id);
		bioskopZaIzmenu.setAdresa(bioskop.getAdresa());
		bioskopZaIzmenu.setNaziv(bioskop.getNaziv());
		bioskopZaIzmenu.setSale(bioskop.getSale());
		
		Bioskop sacuvan = repository.save(bioskopZaIzmenu);
		
		return sacuvan;
	}
	
	
	@Override
	public Bioskop saveOcena(Bioskop bioskop, Long id) {
		Bioskop bioskopZaOCeniti = repository.findOne(id);
		bioskopZaOCeniti.setOcena(bioskop.getOcena());
		
		return repository.save(bioskopZaOCeniti);
	}

	@Override
	public Bioskop findByNaziv(String naziv) {
		return repository.findByNaziv(naziv);
	}

	@Override
	public List<Bioskop> findByAdresa(Adresa adresa) {
		return repository.findByAdresa(adresa);
	}




}
