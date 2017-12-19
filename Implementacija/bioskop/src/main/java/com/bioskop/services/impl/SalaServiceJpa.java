package com.bioskop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;
import com.bioskop.repository.BioskopRepository;
import com.bioskop.repository.SalaRepository;
import com.bioskop.services.SalaService;

@Service
public class SalaServiceJpa implements SalaService {

	@Autowired
	private BioskopRepository bioskopRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	@Override
	public Bioskop saveSala(Long id, Sala sala) {
		Bioskop bioskop = bioskopRepository.findOne(id);
		List<Sala> sale = bioskop.getSale();
		salaRepository.save(sala);
		sale.add(sala);
		bioskop.setSale(sale);
		
		return bioskopRepository.save(bioskop);
	}

	@Override
	public Bioskop deleteSala(Long id, Long idSale) {
		Bioskop bioskop = bioskopRepository.findOne(id);
		List<Sala> sale = bioskop.getSale();
		Sala salaZaObrisati = salaRepository.findOne(idSale);
		salaRepository.delete(salaZaObrisati);
		sale.remove(salaZaObrisati);
		bioskop.setSale(sale);
		
		return bioskopRepository.save(bioskop);
	}

	@Override
	public Bioskop updateSala(Long idBioskopa, Long idSale, Sala sala) {
		Bioskop bioskopZaIzmenu = bioskopRepository.findOne(idBioskopa);
		List<Sala> sale = bioskopZaIzmenu.getSale();

		
		for (Sala s : sale) {
			if(s.getId().equals(idSale)) {
				s.setBrojSedistaKolone(sala.getBrojSedistaKolone());
				s.setBrojSedistaRedovi(sala.getBrojSedistaRedovi());
				s.setKapacitet(sala.getBrojSedistaKolone() * sala.getBrojSedistaRedovi() );
				s.setOznakaSale(sala.getOznakaSale());
				s.setTip(sala.getTip());
				salaRepository.save(s);
			
			}
		}
		
		
		return 	bioskopRepository.save(bioskopZaIzmenu);
	}



}
