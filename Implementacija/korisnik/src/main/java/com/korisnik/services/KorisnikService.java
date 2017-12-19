package com.korisnik.services;

import java.util.List;

import com.korisnik.models.Korisnik;

public interface KorisnikService {

	public Korisnik findByKorisnickoIme(String korisnickoIme);

	public Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka);

	public Korisnik save(Korisnik korisnik);
	
	public List<Korisnik> findAll();
}
