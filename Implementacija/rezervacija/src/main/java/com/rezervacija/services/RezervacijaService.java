package com.rezervacija.services;

import java.util.List;

import com.rezervacija.models.Rezervacija;

public interface RezervacijaService {

	public Rezervacija findOne(Long id);
	public List<Rezervacija> find();
	public Rezervacija save(Rezervacija rezervacija);
	public void delete(Rezervacija rezervacija);
	public void deleteById(Long id);
	public Rezervacija update(Rezervacija novaRezevacija, Long id);
	
}
