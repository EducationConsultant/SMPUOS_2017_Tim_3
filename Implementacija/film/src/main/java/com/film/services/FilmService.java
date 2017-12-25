package com.film.services;

import java.util.List;

import com.film.models.Film;

public interface FilmService {
	public Film save(Film film);

	public Film findOne(Long id);

	public List<Film> findAll();

	public Film update(Film film, Long id);

	public void delete(Film film);

	public List<Film> findAktuelniFilmovi();

	public List<Film> findByGodinaPremijere(int godinaPremijere);
}
