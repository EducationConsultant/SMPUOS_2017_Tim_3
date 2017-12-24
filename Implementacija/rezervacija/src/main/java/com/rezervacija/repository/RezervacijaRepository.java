package com.rezervacija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rezervacija.models.Rezervacija;

@Repository
public interface RezervacijaRepository  extends JpaRepository<Rezervacija, Long>{

}
