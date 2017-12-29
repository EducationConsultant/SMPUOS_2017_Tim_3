package com.rezervacija.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rezervacija.models.Rezervacija;
import com.rezervacija.services.RezervacijaService;

@RestController
@RequestMapping("rezervacija")
public class RezervacijaController {

	@Autowired
	private RezervacijaService rezervacijaService;

	// find all
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Rezervacija>> getRezervacije() {
		List<Rezervacija> rezervacije = rezervacijaService.find();
		return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
	}

	// find by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Rezervacija> getRezervacija(@PathVariable Long id) {
		Rezervacija rezervacija = rezervacijaService.findOne(id);
		return new ResponseEntity<Rezervacija>(rezervacija, HttpStatus.OK);
	}

	// insert
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Rezervacija> insertRezervacija(@Valid @RequestBody Rezervacija rezervacija) {
		Rezervacija sacuvanaRezervacija = rezervacijaService.save(rezervacija);
		return new ResponseEntity<Rezervacija>(sacuvanaRezervacija, HttpStatus.CREATED);
	}

	// delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Rezervacija> deleteRezervacija(@PathVariable Long id) {
		Rezervacija rezervacija = rezervacijaService.findOne(id);
		rezervacijaService.delete(rezervacija);
		return new ResponseEntity<Rezervacija>(rezervacija, HttpStatus.OK);
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Rezervacija> updateRezervacija(@PathVariable Long id,
			@Valid @RequestBody Rezervacija rezervacija) {
		Rezervacija savedRezervacija = rezervacijaService.update(rezervacija, id);
		return new ResponseEntity<Rezervacija>(savedRezervacija, HttpStatus.OK);
	}

	// deaktivacija
	@RequestMapping(value = "/deaktivacija/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Rezervacija> deaktivacija(@PathVariable Long id) {
		Rezervacija savedRezervacija = rezervacijaService.deaktivacija(id);
		return new ResponseEntity<Rezervacija>(savedRezervacija, HttpStatus.OK);
	}

	// pregled aktivnih po idKorisnika
	@RequestMapping(value = "/aktivne/{idKorisnika}", method = RequestMethod.GET)
	public ResponseEntity<List<Rezervacija>> pregledAktRezKor(@PathVariable Long idKorisnika) {
		List<Rezervacija> rezervacije = rezervacijaService.pregledAktRezKor(idKorisnika);
		return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
	}

	// pregled svih rezervacija po idKorisnika
	@RequestMapping(value = "/sve/{idKorisnika}", method = RequestMethod.GET)
	public ResponseEntity<List<Rezervacija>> pregledSvihRezKor(@PathVariable Long idKorisnika) {
		List<Rezervacija> rezervacije = rezervacijaService.pregledSvihRezKor(idKorisnika);
		return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
	}
	
	
	//pregled aktivnih rezervacija po projekcjama  -- MALA NAPOMENA --------------- BITNO!!!
	// NA FRONTENDU : sto se tice podataka o projekciji, prikazati samo :
	// datum projekcije, ime filma, ime bioskopa, oznaka sale, broj aktivnih rezervacija
	// bez id - eva i bez broja otkazanih rezervacija
	@RequestMapping(value="/aktivne/projekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Rezervacija>> pregledSvihAktivnihPoProjekciji() {
		List<Rezervacija> rezervacije = rezervacijaService.pregledAktivnihPoProjekcijama();
		return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
		
	}
	
	//pregled otkazanih rezervacija po projekcjama  -- MALA NAPOMENA---- ISTO KAO GORE
	// bez broja aktivnih rezervacija
	@RequestMapping(value="/otkazane/projekcije", method = RequestMethod.GET)
	public ResponseEntity<List<Rezervacija>> pregledSvihOtkazanihPoProjekciji() {
		List<Rezervacija> rezervacije = rezervacijaService.pregledOtkazanihPoProjekcijama();
		return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
		
	}
	
	//metoda koja vraca aktivne rezervacije za projekciju
	@RequestMapping(value="/aktivne/projekcija/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Rezervacija>> getAktivneRezervacijeZaProjekciju(@PathVariable Long id) {
		List<Rezervacija> rezervacije = rezervacijaService.getAktivneRezervacijeZaProjekciju(id);
		return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
		
	}
	
	//metoda koja vraca otkazane rezervacije za projekciju
	@RequestMapping(value="/otkazane/projekcija/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Rezervacija>> getOtkazaneRezervacijeZaProjekciju(@PathVariable Long id) {
		List<Rezervacija> rezervacije = rezervacijaService.getOtkazaneRezervacijeZaProjekciju(id);
		return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
		
	}
	
}
