package com.film.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.Film;
import com.film.models.Jezik;
import com.film.models.Reditelj;
import com.film.services.FilmService;
import com.film.services.JezikService;
import com.film.services.RediteljService;

@RestController
@RequestMapping("film")
public class FilmController {
	@Autowired
	private FilmService filmService;

	@Autowired
	private JezikService jezikService;

	@Autowired
	private RediteljService rediteljService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Film>> getAll() {
		List<Film> filmovi = filmService.findAll();
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Film> save(@RequestBody Film film) {
		Film savedFilm = filmService.save(film);
		return new ResponseEntity<Film>(savedFilm, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/jezici", method = RequestMethod.GET)
	public ResponseEntity<List<Jezik>> getAllJezik() {
		List<Jezik> jezici = jezikService.findAll();
		return new ResponseEntity<List<Jezik>>(jezici, HttpStatus.OK);
	}

	@RequestMapping(value = "/reditelji", method = RequestMethod.GET)
	public ResponseEntity<List<Reditelj>> getAllReditelj() {
		List<Reditelj> reditelji = rediteljService.findAll();
		return new ResponseEntity<List<Reditelj>>(reditelji, HttpStatus.OK);
	}
}
