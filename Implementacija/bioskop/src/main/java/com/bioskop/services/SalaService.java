package com.bioskop.services;

import java.util.List;

import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;

public interface SalaService {

	public Sala findOne(Long id);
	public List<Sala> find();
	public Sala save(Sala sala);
	public void delete(Sala sala);
	public void deleteById(Long id);
	public void update(Sala novaSala, Long salaId);
	public List<Sala> findByBioskop(Bioskop bioskop);
	
	
}
