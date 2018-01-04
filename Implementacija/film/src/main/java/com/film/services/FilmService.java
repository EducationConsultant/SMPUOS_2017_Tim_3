package com.film.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.film.models.Film;

public interface FilmService {
	public Film save(Film film);

	public Film findOne(Long id);

	public List<Film> findAll();

	public Film update(Film film, Long id);

	public void delete(Film film);

	public Film saveOcena(Film film, Long id);

	public Page<Film> findAllByPage(Pageable pageable);

	public List<Film> findAktuelniFilmovi();

	public List<Film> findByGodinaPremijere(int godinaPremijere);

	public List<Film> findByKategorija(String naziv);

	public List<Film> findByReditelj(String ime, String prezime);

	public List<Film> findByGlumac(String ime, String prezime);

	public List<Film> findByProsecnaOcena(float prosecnaOcena);
	
	public List<Film> findByDatumPremijereBetween();

}
