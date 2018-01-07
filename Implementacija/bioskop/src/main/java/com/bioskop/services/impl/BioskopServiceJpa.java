package com.bioskop.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioskop.models.Adresa;
import com.bioskop.models.AdresaKoordinate;
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

	// rangiran prikaz
	@Override
	public List<Bioskop> find() {
		//return repository.findAll();
		return repository.findAllByOrderByProsecnaOcenaDesc();
	}

	@Override
	public Bioskop save(Bioskop bioskop) {
		bioskop.setBrojac(0);
		
		Adresa novaAdresa = adresaRepository.findByNazivNaseljenogMestaAndNazivUliceAndBroj(bioskop.getAdresaBioskopa().getNazivNaseljenogMesta(), bioskop.getAdresaBioskopa().getNazivUlice(), bioskop.getAdresaBioskopa().getBroj());
		if(novaAdresa != null){
			bioskop.setAdresaBioskopa(novaAdresa);
		}else{
			novaAdresa = adresaRepository.save(bioskop.getAdresaBioskopa());
			bioskop.setAdresaBioskopa(novaAdresa);
		}
		
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
		Bioskop stariBioskop = repository.findOne(id);
		Adresa staraAdresa = stariBioskop.getAdresaBioskopa();
		Adresa novaAdresa = adresaRepository.findByNazivNaseljenogMestaAndNazivUliceAndBrojAndGeoDuzinaAndGeoSirina
				(bioskop.getAdresaBioskopa().getNazivNaseljenogMesta(), 
				 bioskop.getAdresaBioskopa().getNazivUlice(), 
				 bioskop.getAdresaBioskopa().getBroj(),
				 bioskop.getAdresaBioskopa().getGeoDuzina(),
				 bioskop.getAdresaBioskopa().getGeoSirina());
		
		if(novaAdresa != null){
			bioskop.setAdresaBioskopa(novaAdresa);
		}else{
			bioskop.getAdresaBioskopa().setId(null);
			novaAdresa = adresaRepository.save(bioskop.getAdresaBioskopa());
			bioskop.setAdresaBioskopa(novaAdresa);
			novaAdresa.getBioskopi().add(bioskop);
			staraAdresa.getBioskopi().remove(stariBioskop);
			adresaRepository.save(novaAdresa);
			adresaRepository.save(staraAdresa);
		}
		stariBioskop.setAdresaBioskopa(bioskop.getAdresaBioskopa());
		stariBioskop.setNaziv(bioskop.getNaziv());
		stariBioskop.setSale(bioskop.getSale());
		Bioskop sacuvan = repository.save(stariBioskop);
		return sacuvan;
	}
	
	
	@Override
	public Bioskop saveOcena(Bioskop bioskop, Long id) {
		Bioskop bioskopZaOCeniti = repository.findOne(id);
		
		float sumaOcena = bioskopZaOCeniti.getSumaOcena() + bioskop.getOcena();
		bioskopZaOCeniti.setSumaOcena(sumaOcena);
		
		int brojac = bioskopZaOCeniti.getBrojac() + 1;
		bioskopZaOCeniti.setBrojac(brojac);
		
		float prosecna = sumaOcena / (float) brojac;
		bioskopZaOCeniti.setProsecnaOcena(prosecna);
		
		bioskopZaOCeniti.setOcena(bioskop.getOcena());
		
		return repository.save(bioskopZaOCeniti);
	}

	@Override
	public Bioskop findByNaziv(String naziv) {
		return repository.findByNaziv(naziv);
	}

	@Override
	public List<Bioskop> findByAdresaBioskopa(Adresa adresa) {
		return repository.findByAdresaBioskopa(adresa);
	}

	@Override
	public List<Bioskop> rangiranje() {
		List<Bioskop> bioskopi = repository.findAllByOrderByProsecnaOcenaDesc();
		return bioskopi;
	}

	@Override
	public List<Bioskop> findByLocation(AdresaKoordinate adresaKoordinate) {
		float geoDuzinaCentar = adresaKoordinate.getGeoDuzinaCentar();
		float geoSirinaCentar = adresaKoordinate.getGeoSirinaCentar();
		float poluprecnik = adresaKoordinate.getPoluprecnik();
		List<Adresa> adrese = adresaRepository.findAll();
		List<Bioskop> bioskopi = new ArrayList<Bioskop>();
		
		for (Adresa adresa : adrese) {
			// (x2-x1)^2 + (y2-y1)^2 <= r^2
			// x1,y1 - centar
			// x2,y2 - tacka
			// y - geografka sirina
			// x - geografksa duzina

			double xOduzimanje = Math.pow((adresa.getGeoDuzina() - geoDuzinaCentar), 2);
			double yOduzimanje = Math.pow((adresa.getGeoSirina() - geoSirinaCentar), 2);
			double r = Math.pow(poluprecnik, 2);

			double xPlusy = xOduzimanje + yOduzimanje;

			if (xPlusy <= r) {
				for (Bioskop k : adresa.getBioskopi()) {
					bioskopi.add(k);
				}
			}
		}

		return bioskopi;
	}




}
