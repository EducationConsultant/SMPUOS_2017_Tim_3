package com.rezervacija.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.rezervacija.models.Projekcija;
import com.rezervacija.models.Rezervacija;
import com.rezervacija.services.ProjekcijaService;

@RestController
@RequestMapping("projekcija")
public class ProjekcijaController {

	@Autowired
	private ProjekcijaService projekcijaService;

	// find all
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Projekcija>> getProjekcije() {
		List<Projekcija> projekcije = projekcijaService.find();
		return new ResponseEntity<List<Projekcija>>(projekcije, HttpStatus.OK);
	}

	// find by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Projekcija> getProjekcija(@PathVariable Long id) {
		Projekcija projekcija = projekcijaService.findOne(id);
		return new ResponseEntity<Projekcija>(projekcija, HttpStatus.OK);
	}

	// insert
	// unosi se samo idBioskopa, idFilma, idSale u okviru bioskopa, datumprojekcije
	// ostala polja se sama popune (povezivanje aplikacija)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Projekcija> insertProjekcija(@Valid @RequestBody Projekcija projekcija) {
		projekcijaService.save(projekcija);
		
		String nazivBioskopa = projekcijaService.checkBioskop(projekcija.getIdBioskopa());
		projekcija.setNazivBioskopa(nazivBioskopa);
		
		projekcijaService.save(projekcija);
		
		String nazivFilma = projekcijaService.checkFilm(projekcija.getIdFilma());
		projekcija.setNazivFilma(nazivFilma);
		
		String oznakaSale = projekcijaService.checkSala(projekcija.getIdBioskopa(), projekcija.getIdSale());
		projekcija.setOznakaSale(oznakaSale);
		
		projekcijaService.save(projekcija);
		return new ResponseEntity<Projekcija>(projekcija, HttpStatus.CREATED);
	}

	// delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Projekcija> deleteProjekcija(@PathVariable Long id) {
		Projekcija projekcija = projekcijaService.findOne(id);
		projekcijaService.delete(projekcija);
		return new ResponseEntity<Projekcija>(projekcija, HttpStatus.OK);
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Projekcija> updateProjekcijaa(@PathVariable Long id,
			@Valid @RequestBody Projekcija projekcija) {
		Projekcija savedProjekcija = projekcijaService.update(projekcija, id);
		return new ResponseEntity<Projekcija>(savedProjekcija, HttpStatus.OK);
	}
	
	@FeignClient("bioskop-service")//the application.name for the bioskop service
	public interface BioskopServiceClient {
		@RequestMapping(value = "bioskop/checkBioskop", method = RequestMethod.GET)// the endpoint which will be balanced over
		String checkBioskop(@RequestParam(name="bioskopId") Long bioskopId);// the method specification must be the same as for bioskop/checkBioskop
	}
	
	@FeignClient("film-service")//the application.name for the film service
	public interface FilmServiceClient {
		@RequestMapping(value = "film/checkFilm", method = RequestMethod.GET)// the endpoint which will be balanced over
		String checkBioskop(@RequestParam(name="filmId") Long filmId);// the method specification must be the same as for film/checkFilm
	}
	
	@FeignClient("bioskop-service")//the application.name for the bioskop service
	public interface SalaServiceClient {
		@RequestMapping(value = "sala/checkSala", method = RequestMethod.GET)// the endpoint which will be balanced over
		String checkSala(@RequestParam(name="bioskopId") Long bioskopId, @RequestParam(name="salaId") Long salaId );// the method specification must be the same as for sala/checkSala
	}
	

	//metoda koja vraca broj zauzetih mesta u datom redu za tu projekciju
	@RequestMapping(value="/zauzeta/{projekcijaId}/{red}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getBrojZauzetihMesta(@PathVariable Long projekcijaId,
			@PathVariable int red) {
		int brojZauzetihMesta = projekcijaService.getBrojZauzetihMesta(projekcijaId, red);
		return new ResponseEntity<Integer>(brojZauzetihMesta, HttpStatus.OK);
		
	}
	
	//metoda koja vraca broj zauzetih mesta u datom redu za tu projekciju, prilikom izmene rezervacije,
	//pa ne uzima u obzir mesta rezervisana od strane menjane rezervacije
	@RequestMapping(value="/zauzetaIzmena/{rezervacijaId}/{projekcijaId}/{red}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getBrojZauzetihMestaIzmena(@PathVariable Long rezervacijaId,
			@PathVariable Long projekcijaId,
			@PathVariable int red) {
		int brojZauzetihMesta = projekcijaService.getBrojZauzetihMestaIzmena(rezervacijaId, projekcijaId, red);
		return new ResponseEntity<Integer>(brojZauzetihMesta, HttpStatus.OK);
		
	}
}
