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

import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;
import com.bioskop.services.BioskopService;

@RestController
@RequestMapping("/api/bioskop")
public class BioskopController {

	@Autowired
	private BioskopService bioskopService;

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
	@RequestMapping(value = "/ocena/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Bioskop> insertOcena(@PathVariable Long id, @RequestBody Bioskop bioskop) {

		Bioskop ocenaZaBioskop = bioskopService.saveOcena(bioskop, id);

		return new ResponseEntity<Bioskop>(ocenaZaBioskop, HttpStatus.CREATED);

	}

	// find by naziv
	// localhost:8091/api/bioskop/naziv?naziv=Cinema
	@RequestMapping(value = "/naziv", method = RequestMethod.GET)
	public ResponseEntity<Bioskop> findByNaziv(@RequestParam(value = "naziv") String naziv) {

		Bioskop bioskop = bioskopService.findByNaziv(naziv);

		return new ResponseEntity<Bioskop>(bioskop, HttpStatus.OK);
	}

	// find by adresa
	// @RequestMapping(value = "/adresa", method = RequestMethod.GET)
	// public ResponseEntity<List<Bioskop>> findByAdresa(@RequestBody Adresa
	// adresa) {
	//
	// List<Bioskop> bioskopi = bioskopService.findByAdresa(adresa);
	//
	// return new ResponseEntity<List<Bioskop>>(bioskopi, HttpStatus.OK);
	// }

}
