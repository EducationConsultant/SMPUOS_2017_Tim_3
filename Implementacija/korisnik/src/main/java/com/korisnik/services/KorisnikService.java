package com.korisnik.services;

import com.korisnik.models.Korisnik;

public interface KorisnikService {

	public Korisnik findByKorisnickoIme(String korisnickoIme);

	public Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka);
}
