package com.rezervacija.services;

import java.util.List;

import com.rezervacija.models.Projekcija;

public interface ProjekcijaService {

	public Projekcija findOne(Long id);
	public List<Projekcija> find();
	public Projekcija save(Projekcija projekcija);
	public void delete(Projekcija projekcija);
	public void deleteById(Long id);
	public Projekcija update(Projekcija projekcija, Long id);
	public String checkBioskop(Long idBioskopa);
	public String checkFilm(Long idFilma);
}
