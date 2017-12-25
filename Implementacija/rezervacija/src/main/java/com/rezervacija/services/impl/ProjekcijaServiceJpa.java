package com.rezervacija.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rezervacija.models.Projekcija;
import com.rezervacija.repository.ProjekcijaRepository;
import com.rezervacija.services.ProjekcijaService;

@Service
public class ProjekcijaServiceJpa implements ProjekcijaService {
	
	@Autowired
	private ProjekcijaRepository repository;

	@Override
	public Projekcija findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Projekcija> find() {
		return repository.findAll();
	}

	@Override
	public Projekcija save(Projekcija projekcija) {
		return repository.save(projekcija);
	}

	@Override
	public void delete(Projekcija projekcija) {
		repository.delete(projekcija);
		
	}

	@Override
	public void deleteById(Long id) {
		repository.delete(id);
		
	}

	@Override
	public Projekcija update(Projekcija projekcija, Long id) {
		Projekcija projekcijaZaIzmenu = repository.findOne(id);
		projekcijaZaIzmenu.setDatumProjekcije(projekcija.getDatumProjekcije());
		projekcijaZaIzmenu.setIdFilma(projekcija.getIdFilma());
		projekcijaZaIzmenu.setIdSale(projekcija.getIdSale());
		projekcijaZaIzmenu.setIdBioskopa(projekcija.getIdBioskopa());
		projekcijaZaIzmenu.setNazivBioskopa(projekcija.getNazivBioskopa());
		projekcijaZaIzmenu.setNazivFilma(projekcija.getNazivFilma());
		projekcijaZaIzmenu.setOznakaSale(projekcija.getOznakaSale());
		return repository.save(projekcijaZaIzmenu);
	}

}
