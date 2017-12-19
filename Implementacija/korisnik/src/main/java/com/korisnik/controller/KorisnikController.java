package com.korisnik.controller;

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

import com.korisnik.models.Adresa;
import com.korisnik.models.Korisnik;
import com.korisnik.models.KorisnikLogin;
import com.korisnik.services.AdresaService;
import com.korisnik.services.KorisnikService;

@RestController
@RequestMapping("/api/korisnik")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private AdresaService adresaService;

	@RequestMapping(value = "/adrese/{id}", method = RequestMethod.GET)
	public ResponseEntity<Adresa> getAdresa(@PathVariable Long id) {
		Adresa adresa = adresaService.findOnde(id);
		return new ResponseEntity<Adresa>(adresa, HttpStatus.OK);

	}

	@RequestMapping(value = "/registracija", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> registracija(@Valid @RequestBody Korisnik korisnik) {
		Korisnik savedKorisnik = korisnikService.save(korisnik);

		return new ResponseEntity<Korisnik>(savedKorisnik, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/korisnici", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> getKorisnici() {
		List<Korisnik> korisnici = korisnikService.findAll();
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);

	}

	@RequestMapping(value = "/login", method = RequestMethod.PUT)
	public ResponseEntity<Korisnik> login(@RequestBody KorisnikLogin korisnikLogin) {
		Korisnik ulogovaniKorisnik = korisnikService.login(korisnikLogin);

		return new ResponseEntity<Korisnik>(ulogovaniKorisnik, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<Korisnik> logout(@RequestBody KorisnikLogin korisnikLogin) {
		Korisnik ulogovaniKorisnik = korisnikService.logout(korisnikLogin);

		return new ResponseEntity<Korisnik>(ulogovaniKorisnik, HttpStatus.OK);
	}

	// id korisnika kog aktiviram
	@RequestMapping(value = "/aktivacija/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Korisnik> aktivacija(@PathVariable Long id) {

		Korisnik aktiviraniKorisnik = korisnikService.aktivacijaKorisnika(id);

		return new ResponseEntity<Korisnik>(aktiviraniKorisnik, HttpStatus.OK);
	}

	// id korisnika kog dekativiram
	@RequestMapping(value = "/deaktivacija/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Korisnik> deaktivacija(@PathVariable Long id) {

		Korisnik dekativiraniKorisnik = korisnikService.deaktivacijaKorisnika(id);

		return new ResponseEntity<Korisnik>(dekativiraniKorisnik, HttpStatus.OK);
	}

	@RequestMapping(value = "/pregledAktivnih", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> pregledAktivnih() {
		List<Korisnik> aktivniKorisnici = korisnikService.aktivniKorisnici();
		return new ResponseEntity<List<Korisnik>>(aktivniKorisnici, HttpStatus.OK);
	}

	@RequestMapping(value = "/pregledDeaktiviranih", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> pregledDeaktiviranih() {
		List<Korisnik> deaktiviraniKorisnici = korisnikService.dektiviraniKorisnici();
		return new ResponseEntity<List<Korisnik>>(deaktiviraniKorisnici, HttpStatus.OK);
	}

	// koriscen je KorisnikLogin, da bi se izbeglo kreiranje jos jednog modela
	// sa atributima ime i prezime
	@RequestMapping(value = "/filterImePrezime", method = RequestMethod.PUT)
	public ResponseEntity<List<Korisnik>> pregledPoImenuPrezimenu(@RequestBody KorisnikLogin korisnik) {
		List<Korisnik> pronadjeniKorisnici = korisnikService.findByImeAndPrezime(korisnik.getKorisnickoIme(),
				korisnik.getLozinka());

		return new ResponseEntity<List<Korisnik>>(pronadjeniKorisnici, HttpStatus.OK);
	}

}
