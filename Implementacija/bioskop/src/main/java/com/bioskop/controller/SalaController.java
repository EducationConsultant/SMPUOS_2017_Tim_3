package com.bioskop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;
import com.bioskop.services.BioskopService;
import com.bioskop.services.SalaService;


@RestController
@RequestMapping("/api/sala")
public class SalaController {

	@Autowired
	private SalaService salaService;

	// unos sale u okviru bioskopa
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Bioskop> insertSala(@PathVariable Long id, @RequestBody Sala sala) {

		Bioskop bioskopZaIzmenu = salaService.saveSala(id, sala);

		return new ResponseEntity<Bioskop>(bioskopZaIzmenu, HttpStatus.CREATED);
	}

	
	// unos sale u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}/{idSale}", method = RequestMethod.DELETE)
	public ResponseEntity<Bioskop> deleteSala(@PathVariable Long idBioskopa,@PathVariable Long idSale) {

		Bioskop bioskopZaIzmenu = salaService.deleteSala(idBioskopa, idSale);

		return new ResponseEntity<Bioskop>(bioskopZaIzmenu, HttpStatus.OK);
	}
	
	// update sale u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}/{idSale}", method = RequestMethod.PUT)
	public ResponseEntity<Bioskop> updateSala(@PathVariable Long idBioskopa, @PathVariable Long idSale, @Valid @RequestBody Sala sala) { 

		Bioskop savedBioskop = salaService.updateSala(idBioskopa, idSale, sala);

		return new ResponseEntity<Bioskop>(savedBioskop, HttpStatus.OK);

	}
	
}
