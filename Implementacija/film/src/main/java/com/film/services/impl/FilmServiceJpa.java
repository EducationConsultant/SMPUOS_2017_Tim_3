package com.film.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.Film;
import com.film.models.Jezik;
import com.film.models.Reditelj;
import com.film.repository.FilmRepository;
import com.film.repository.JezikRepository;
import com.film.repository.RediteljRepository;
import com.film.services.FilmService;
import java.util.List;

@Service
public class FilmServiceJpa implements FilmService {

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private JezikRepository jezikRepository;

	@Autowired
	private RediteljRepository rediteljRepository;

	@Override
	public Film save(Film film) {
		Jezik jezik = jezikRepository.findOne(film.getJezik().getId());
		film.setJezik(jezik);

		Reditelj reditelj = rediteljRepository.findOne(film.getReditelj().getId());
		film.setReditelj(reditelj);

		filmRepository.save(film);
		return film;
	}

	@Override
	public List<Film> findAll() {
		List<Film> filmovi = filmRepository.findAll();
		return filmovi;
	}

}
