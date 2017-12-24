package com.korisnik.services;

import java.util.List;

import com.korisnik.models.AdresaKoordinate;
import com.korisnik.models.Korisnik;
import com.korisnik.models.KorisnikLogin;

public interface KorisnikService {

	public Korisnik findByKorisnickoIme(String korisnickoIme);

	public Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka);

	public Korisnik save(Korisnik korisnik);

	public List<Korisnik> findAll();

	public Korisnik login(KorisnikLogin korisnikLogin);

	public Korisnik logout(KorisnikLogin korisnikLogin);

	public Korisnik aktivacijaKorisnika(Long id);

	public Korisnik deaktivacijaKorisnika(Long id);

	public List<Korisnik> aktivniKorisnici();

	public List<Korisnik> dektiviraniKorisnici();

	public List<Korisnik> findByImeAndPrezime(String ime, String prezime);

	public Korisnik findOne(Long id);

	public List<Korisnik> findByLocation(AdresaKoordinate adresaKoordinate);
}
