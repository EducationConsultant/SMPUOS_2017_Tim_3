package com.korisnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korisnik.models.Adresa;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Long> {

}
