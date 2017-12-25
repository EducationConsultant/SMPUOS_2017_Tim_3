package com.rezervacija.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rezervacija.models.Rezervacija;
import com.rezervacija.models.RezervacijaTip;
import com.rezervacija.repository.RezervacijaRepository;
import com.rezervacija.services.RezervacijaService;

@Service
public class RezervacijaServiceJpa implements RezervacijaService {

	@Autowired
	private RezervacijaRepository repository;
	
	@Override
	public Rezervacija findOne(Long id) {
		return  repository.findOne(id);
	}

	@Override
	public List<Rezervacija> find() {
		return repository.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		return repository.save(rezervacija);
	}

	@Override
	public void delete(Rezervacija rezervacija) {
		repository.delete(rezervacija);
		
	}

	@Override
	public void deleteById(Long id) {
		repository.delete(id);
		
	}

	@Override
	public Rezervacija update(Rezervacija rezervacija, Long id) {
		Rezervacija rezervacijaZaIzmenu = repository.findOne(id);
		rezervacijaZaIzmenu.setTip(rezervacija.getTip());
		rezervacijaZaIzmenu.setBrojRedaSedista(rezervacija.getBrojRedaSedista());
		rezervacijaZaIzmenu.setBrojSedista(rezervacija.getBrojSedista());
		rezervacijaZaIzmenu.setDatumIstekaRezervacije(rezervacija.getDatumIstekaRezervacije());
		rezervacijaZaIzmenu.setDatumProjekcije(rezervacija.getDatumProjekcije());
		rezervacijaZaIzmenu.setDatumRezervacije(rezervacija.getDatumRezervacije());
		//rezervacijaZaIzmenu.setIdBioskopa(rezervacija.getIdBioskopa());
		rezervacijaZaIzmenu.setIdFilma(rezervacija.getIdFilma());
		rezervacijaZaIzmenu.setIdSale(rezervacija.getIdSale());
		//rezervacijaZaIzmenu.setNazivBioskopa(rezervacija.getNazivBioskopa());
		rezervacijaZaIzmenu.setNazivFilma(rezervacija.getNazivFilma());
		rezervacijaZaIzmenu.setOznakaSale(rezervacija.getOznakaSale());
		//rezervacijaZaIzmenu.setIdKorisnika(rezervacija.getIdKorisnika());
		
		Rezervacija sacuvana = repository.save(rezervacijaZaIzmenu);
		return sacuvana;
	}

	@Override
	public Rezervacija deaktivacija(Long id) {
		Rezervacija rezervacija = repository.findOne(id);
		rezervacija.setTip(RezervacijaTip.OTKAZANA);
		return repository.save(rezervacija);
	}
}
