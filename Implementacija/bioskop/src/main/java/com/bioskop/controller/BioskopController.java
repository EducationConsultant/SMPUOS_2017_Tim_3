package com.bioskop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;
import com.bioskop.services.BioskopService;


import com.bioskop.models.AdresaKoordinate;


@RestController
@RequestMapping("bioskop")
public class BioskopController {

	@Autowired
	private BioskopService bioskopService;
	
	// rangirani su vec po prosecnoj oceni,ne treba prikazivati i polje ocena, samo prosecna ocena
	// find all
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Bioskop>> getBioskopi() {
		List<Bioskop> bioskopi = bioskopService.find();
		return new ResponseEntity<List<Bioskop>>(bioskopi, HttpStatus.OK);
	}

	// find by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Bioskop> getBioskop(@PathVariable Long id) {
		Bioskop bioskop = bioskopService.findOne(id);
		return new ResponseEntity<Bioskop>(bioskop, HttpStatus.OK);
	}

	// insert
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Bioskop> insertBioskop(@Valid @RequestBody Bioskop bioskop) {
		Bioskop sacuvanBioskop = bioskopService.save(bioskop);
		return new ResponseEntity<Bioskop>(sacuvanBioskop, HttpStatus.CREATED);
	}

	// delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Bioskop> deleteBioskop(@PathVariable Long id) {
		Bioskop bioskop = bioskopService.findOne(id);
		bioskopService.delete(bioskop);
		return new ResponseEntity<Bioskop>(bioskop, HttpStatus.OK);
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Bioskop> updateBioskop(@PathVariable Long id, @Valid @RequestBody Bioskop bioskop) {
		Bioskop savedBioskop = bioskopService.update(bioskop, id);
		return new ResponseEntity<Bioskop>(savedBioskop, HttpStatus.OK);
	}

	// ocenjivanje
	@RequestMapping(value = "/ocena/{id}/{username}", method = RequestMethod.PUT)
	public ResponseEntity<Bioskop> insertOcena(@PathVariable("id") Long id, @PathVariable("username") String username, @RequestBody Bioskop bioskop) {
		Bioskop ocenaZaBioskop = bioskopService.saveOcena(bioskop, id, username);
		return new ResponseEntity<Bioskop>(ocenaZaBioskop, HttpStatus.CREATED);
	}

	// find by naziv
	// localhost:8765/bioskop-service/bioskop/naziv?naziv=Cinema
	@RequestMapping(value = "/naziv", method = RequestMethod.GET)
	public ResponseEntity<Bioskop> findByNaziv(@RequestParam(value = "naziv") String naziv) {
		Bioskop bioskop = bioskopService.findByNaziv(naziv);
		return new ResponseEntity<Bioskop>(bioskop, HttpStatus.OK);
	}

	// pregled korisnika na osnovu koordinata mesta stanovanja
	@RequestMapping(value = "/koordinate", method = RequestMethod.PUT)
	public ResponseEntity<List<Bioskop>> getLocation(@RequestBody AdresaKoordinate adresaKoordinate) {
		List<Bioskop> bioskopi = bioskopService.findByLocation(adresaKoordinate);
		return new ResponseEntity<List<Bioskop>>(bioskopi, HttpStatus.OK);
	}
	
	// uvezivanje sa rezervacijom
	//localhost:8765/bioskop-service/bioskop/checkBioskop?bioskopId=1
	@RequestMapping(value = "/checkBioskop", method = RequestMethod.GET)
	public String checkBioskop(@RequestParam(name="bioskopId") Long bioskopId){
		Bioskop bioskop = bioskopService.findOne(bioskopId);
		return bioskop.getNaziv();
	}
	


}
