package com.korisnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korisnik.models.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	public Korisnik findByKorisnickoIme(String korisnickoIme);

	public Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka);
}
