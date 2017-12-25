package com.film.services;

import java.util.List;

import com.film.models.Film;

public interface FilmService {
	public Film save(Film film);

	public List<Film> findAll();
}
