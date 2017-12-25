package com.film.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.Film;
import com.film.services.FilmService;

@RestController
@RequestMapping("film")
public class FilmController {
	@Autowired
	private FilmService filmService;

	// getAll
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Film>> getFilmovi() {
		List<Film> filmovi = filmService.findAll();
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}

	// getOne
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Film> getFilm(@PathVariable Long id) {
		Film film = filmService.findOne(id);
		return new ResponseEntity<Film>(film, HttpStatus.OK);
	}

	// insert
	@RequestMapping(value = "/dodaj", method = RequestMethod.POST)
	public ResponseEntity<Film> save(@Valid @RequestBody Film film) {
		Film savedFilm = filmService.save(film);
		return new ResponseEntity<Film>(savedFilm, HttpStatus.CREATED);
	}

	// update
	@RequestMapping(value = "/izmeni/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Film> update(@PathVariable Long id, @Valid @RequestBody Film film) {
		Film izmenjenFilm = filmService.update(film, id);
		return new ResponseEntity<Film>(izmenjenFilm, HttpStatus.OK);
	}

	// delete
	@RequestMapping(value = "/obrisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Film> delete(@PathVariable Long id) {
		Film film = filmService.findOne(id);
		filmService.delete(film);
		return new ResponseEntity<Film>(film, HttpStatus.OK);
	}

	@RequestMapping(value = "/pregledAktuelnih", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> findAktuelniFilmovi() {
		List<Film> filmovi = filmService.findAktuelniFilmovi();
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}
}
