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
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Projekcija> insertProjekcija(@Valid @RequestBody Projekcija projekcija) {
		projekcijaService.save(projekcija);
		//String idBioskopa = Long.toString(projekcija.getIdBioskopa());
		//String nazivBioskopa = projekcijaService.checkBioskop(idBioskopa);
		//projekcija.setNazivBioskopa(nazivBioskopa);
		//projekcijaService.save(projekcija);
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
	
	@FeignClient("bioskop-service")//the application.name for the user service
	public interface BioskopServiceClient {
		@RequestMapping(value = "bioskop/checkBioskop", method = RequestMethod.GET)// the endpoint which will be balanced over
		String checkBioskop(@RequestParam(name="bioskopId") String bioskopId);// the method specification must be the same as for users/checkUser
	}
	

}
