package com.bioskop.services;

import java.util.List;

import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;

public interface SalaService {

	public Bioskop saveSala(Long id, Sala sala);

	public Bioskop deleteSala(Long idBioskopa, Long idSale);
	
	
}
