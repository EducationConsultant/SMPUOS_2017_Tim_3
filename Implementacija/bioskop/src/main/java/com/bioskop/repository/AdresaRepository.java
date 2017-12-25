package com.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.models.Adresa;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Long> {

	public List<Adresa> findByGeoDuzinaAndGeoSirina(float geoDuzina, float geoSirina);

}
