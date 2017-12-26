package com.rezervacija.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rezervacija.controllers.ProjekcijaController.BioskopServiceClient;
import com.rezervacija.models.Projekcija;
import com.rezervacija.repository.ProjekcijaRepository;
import com.rezervacija.services.ProjekcijaService;

@Service
public class ProjekcijaServiceJpa implements ProjekcijaService {
	
	@Autowired
	private ProjekcijaRepository repository;

	@Autowired
	private BioskopServiceClient bioskopServiceClient; // feign client
	
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

	/*USING LOAD-BALANCING*/
	@Override
	@HystrixCommand(fallbackMethod="fallbackCheckBioskop")
	public String checkBioskop(Long idBioskopa) {
		//System.err.println("idbioskopa U PROJEKCIJA SERVICE JE:" + idBioskopa);
		String nazivBioskopa = bioskopServiceClient.checkBioskop(idBioskopa);
		//System.err.println("NAZIV BIOSKOPA U PROJEKCIJA SERVICE JE:" + nazivBioskopa);
		return nazivBioskopa;
	}

	public String fallbackCheckBioskop(Long bioskopId){
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "!!!";
	}
}
