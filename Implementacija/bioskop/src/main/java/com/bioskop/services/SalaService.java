package com.bioskop.services;

import java.util.List;

import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;

public interface SalaService {

	public Sala saveSala(Long id, Sala sala);

	public Bioskop deleteSala(Long idBioskopa, Long idSale);

	public Bioskop updateSala(Long idBioskopa, Long idSale, Sala sala);

	public Sala findSalaPoBioskopu(Long idBioskopa, Long idSale);

	public List<Sala> findSveSalePoBioskopu(Long idBioskopa);
	
	
}
