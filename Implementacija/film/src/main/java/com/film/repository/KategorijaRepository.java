package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.film.models.Kategorija;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

}
