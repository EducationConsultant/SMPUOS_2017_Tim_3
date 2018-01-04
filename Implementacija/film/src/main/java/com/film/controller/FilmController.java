package com.film.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.film.models.Film;
import com.film.services.FilmService;

@RestController
@RequestMapping("film")
public class FilmController {
	@Autowired
	private FilmService filmService;

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

	// ocenjivanje filma
	// id - id filma koji ocenjujemo
	@RequestMapping(value = "/ocenjivanje/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Film> saveOcena(@PathVariable Long id, @RequestBody Film film) {
		Film ocenjeniFilm = filmService.saveOcena(film, id);
		return new ResponseEntity<Film>(ocenjeniFilm, HttpStatus.OK);
	}

	// pregled svih filmova - paginirano
	// localhost:8090/film/filmovi?page=0&size=3
	// Ne prosledjujem RequestParams.
	// Kada pogodim /filmovi?page=0&size=3 Spring automatski preuzima parametre
	// i kreira Pageable instancu.
	@RequestMapping(value = "/filmovi", method = RequestMethod.GET)
	public ResponseEntity<Page<Film>> getFilmovi(Pageable pageable) {
		Page<Film> filmovi = filmService.findAllByPage(pageable);
		return new ResponseEntity<Page<Film>>(filmovi, HttpStatus.OK);
	}

	// pregled aktuelnih filmova
	@RequestMapping(value = "/aktuelni", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> findAktuelniFilmovi() {
		List<Film> filmovi = filmService.findAktuelniFilmovi();
		//filmovi=filmService.findByDatumPremijereBetween();
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}

	// pregled filmova prema godini premijere
	@RequestMapping(value = "/godinaPremijere/{godina}", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> findByGodinaPremijere(@PathVariable int godina) {
		List<Film> filmovi = filmService.findByGodinaPremijere(godina);
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}

	// pregled filmova prema kategoriji
	@RequestMapping(value = "/kategorija/{naziv}", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> findByKategorija(@PathVariable("naziv") String naziv) {
		List<Film> filmovi = filmService.findByKategorija(naziv);
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}

	// pregled filmova prema reditelju
	@RequestMapping(value = "/reditelj/{ime}?{prezime}", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> findByReditelj(@PathVariable("ime") String ime,
			@PathVariable("prezime") String prezime) {
		List<Film> filmovi = filmService.findByReditelj(ime, prezime);
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}

	// pregled filmova prema glumcima
	@RequestMapping(value = "/glumac/{ime}?{prezime}", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> findByGlumac(@PathVariable("ime") String ime,
			@PathVariable("prezime") String prezime) {
		List<Film> filmovi = filmService.findByGlumac(ime, prezime);
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}

	// pregled filmova prema oceni
	// localhost:8090/film/ocena?ocena=5
	@RequestMapping(value = "/ocena", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> findByOcena(@RequestParam("ocena") float prosecnaOcena) {
		List<Film> filmovi = filmService.findByProsecnaOcena(prosecnaOcena);
		return new ResponseEntity<List<Film>>(filmovi, HttpStatus.OK);
	}
	
	// za povezivanje sa projekcijom
	//localhost:8765/film-service/film/checkFilm?filmId=1
	@RequestMapping(value = "/checkFilm", method = RequestMethod.GET)
	public String checkFilm(@RequestParam(name="filmId") Long filmId){
		Film film = filmService.findOne(filmId);
		return film.getNaziv();
	}
}
