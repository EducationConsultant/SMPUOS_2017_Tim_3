package com.korisnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korisnik.models.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	public Korisnik findByKorisnickoIme(String korisnickoIme);

	public Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka);

	public Korisnik findByImeAndPrezime(String ime, String prezime);
}
