package com.korisnik.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korisnik.models.Adresa;
import com.korisnik.models.Korisnik;
import com.korisnik.models.TipKorisnika;
import com.korisnik.models.TipStatusaKorisnika;
import com.korisnik.repository.AdresaRepository;
import com.korisnik.repository.KorisnikRepository;
import com.korisnik.services.KorisnikService;

@Service
public class KorisnikServiceJpa implements KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private AdresaRepository adresaRepository;

	@Override
	public Korisnik findByKorisnickoIme(String korisnickoIme) {
		return korisnikRepository.findByKorisnickoIme(korisnickoIme);
	}

	@Override
	public Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka) {
		return korisnikRepository.findByKorisnickoImeAndLozinka(korisnickoIme, lozinka);
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		korisnik.setTipKorisnika(TipKorisnika.REGKORISNIK);
		korisnik.setDatumRegistracije(new Date());
		korisnik.setStatusKorisnika(TipStatusaKorisnika.AKTIVIRAN);
		korisnik.setUlogovan(false);

		Adresa adresa = adresaRepository.findOne(korisnik.getAdresaStanovanja().getId());
		korisnik.setAdresaStanovanja(adresa);

		korisnikRepository.save(korisnik);

		return korisnik;
	}

	@Override
	public List<Korisnik> findAll() {
		List<Korisnik> korisnici = korisnikRepository.findAll();
		return korisnici;
	}

}
