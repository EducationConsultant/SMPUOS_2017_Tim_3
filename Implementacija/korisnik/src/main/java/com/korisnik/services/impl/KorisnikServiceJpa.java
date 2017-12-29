package com.korisnik.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korisnik.models.Adresa;
import com.korisnik.models.AdresaKoordinate;
import com.korisnik.models.Korisnik;
import com.korisnik.models.KorisnikLogin;
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

		Adresa novaAdresa = adresaRepository.findByNazivNaseljenogMestaAndNazivUliceAndBroj(korisnik.getAdresaStanovanja().getNazivNaseljenogMesta(), korisnik.getAdresaStanovanja().getNazivUlice(), korisnik.getAdresaStanovanja().getBroj());
		if(novaAdresa != null){
			korisnik.setAdresaStanovanja(novaAdresa);
		}else{
			novaAdresa = adresaRepository.save(korisnik.getAdresaStanovanja());
			korisnik.setAdresaStanovanja(novaAdresa);
		}

		korisnikRepository.save(korisnik);
		
		return korisnik;
	}

	@Override
	public List<Korisnik> findAll() {
		List<Korisnik> korisnici = korisnikRepository.findAll();
		return korisnici;
	}

	@Override
	public Korisnik login(KorisnikLogin korisnikLogin) {
		Korisnik korisnikUbazi = korisnikRepository.findByKorisnickoImeAndLozinka(korisnikLogin.getKorisnickoIme(),
				korisnikLogin.getLozinka());
		korisnikUbazi.setUlogovan(true);
		korisnikRepository.save(korisnikUbazi);
		return korisnikUbazi;
	}

	@Override
	public Korisnik logout(KorisnikLogin korisnikLogin) {
		Korisnik korisnikUbazi = korisnikRepository.findByKorisnickoImeAndLozinka(korisnikLogin.getKorisnickoIme(),
				korisnikLogin.getLozinka());
		korisnikUbazi.setUlogovan(false);
		korisnikRepository.save(korisnikUbazi);
		return korisnikUbazi;
	}

	@Override
	public Korisnik aktivacijaKorisnika(Long id) {
		Korisnik korisnik = korisnikRepository.findOne(id);
		korisnik.setStatusKorisnika(TipStatusaKorisnika.AKTIVIRAN);
		Korisnik aktiviranKorisnik = korisnikRepository.save(korisnik);

		return aktiviranKorisnik;
	}

	@Override
	public Korisnik deaktivacijaKorisnika(Long id) {
		Korisnik korisnik = korisnikRepository.findOne(id);
		korisnik.setStatusKorisnika(TipStatusaKorisnika.DEAKTIVIRAN);

		Korisnik deaktiviraniKorisnik = korisnikRepository.save(korisnik);

		return deaktiviraniKorisnik;
	}

	@Override
	public List<Korisnik> aktivniKorisnici() {
		List<Korisnik> sviKorisnici = korisnikRepository.findAll();
		List<Korisnik> aktivniKorisnici = new ArrayList<Korisnik>();

		for (Korisnik korisnik : sviKorisnici) {
			if (korisnik.getStatusKorisnika().equals(TipStatusaKorisnika.AKTIVIRAN)) {
				aktivniKorisnici.add(korisnik);
			}
		}

		return aktivniKorisnici;
	}

	@Override
	public List<Korisnik> dektiviraniKorisnici() {
		List<Korisnik> sviKorisnici = korisnikRepository.findAll();
		List<Korisnik> deaktiviraniKorisnici = new ArrayList<Korisnik>();

		for (Korisnik korisnik : sviKorisnici) {
			if (korisnik.getStatusKorisnika().equals(TipStatusaKorisnika.DEAKTIVIRAN)) {
				deaktiviraniKorisnici.add(korisnik);
			}
		}

		return deaktiviraniKorisnici;
	}

	@Override
	public List<Korisnik> findByImeAndPrezime(String ime, String prezime) {
		List<Korisnik> sviKorisnici = korisnikRepository.findAll();
		List<Korisnik> korisnici = new ArrayList<Korisnik>();

		for (Korisnik k : sviKorisnici) {
			if (k.getIme().contains(ime) && k.getPrezime().contains(prezime)) {
				korisnici.add(k);
			}
		}

		return korisnici;
	}

	@Override
	public Korisnik findOne(Long id) {
		return korisnikRepository.findOne(id);
	}

	@Override
	public List<Korisnik> findByLocation(AdresaKoordinate adresaKoordinate) {
		float geoDuzinaCentar = adresaKoordinate.getGeoDuzinaCentar();
		float geoSirinaCentar = adresaKoordinate.getGeoSirinaCentar();
		float poluprecnik = adresaKoordinate.getPoluprecnik();

		List<Adresa> adrese = adresaRepository.findAll();
		List<Korisnik> korisnici = new ArrayList<Korisnik>();
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
				for (Korisnik k : adresa.getKorisnici()) {
					korisnici.add(k);
				}
			}
		}

		return korisnici;
	}

}
