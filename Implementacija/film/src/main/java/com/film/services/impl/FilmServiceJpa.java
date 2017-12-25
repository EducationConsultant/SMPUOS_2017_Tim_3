package com.film.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.Film;
import com.film.models.Jezik;
import com.film.models.Kategorija;
import com.film.models.Reditelj;
import com.film.repository.FilmRepository;
import com.film.repository.JezikRepository;
import com.film.repository.KategorijaRepository;
import com.film.repository.RediteljRepository;
import com.film.services.FilmService;

@Service
public class FilmServiceJpa implements FilmService {

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private JezikRepository jezikRepository;

	@Autowired
	private RediteljRepository rediteljRepository;

	@Autowired
	private KategorijaRepository kategorijaRepository;

	@Override
	public Film save(Film film) {
		Jezik jezik = jezikRepository.findOne(film.getJezik().getId());
		film.setJezik(jezik);

		Reditelj reditelj = rediteljRepository.findOne(film.getReditelj().getId());
		film.setReditelj(reditelj);

		Kategorija kategorija = kategorijaRepository.findOne(film.getKategorija().getId());
		film.setKategorija(kategorija);

		filmRepository.save(film);
		return film;
	}

	@Override
	public Film findOne(Long id) {
		Film film = filmRepository.findOne(id);
		return film;
	}

	@Override
	public List<Film> findAll() {
		List<Film> filmovi = filmRepository.findAll();
		return filmovi;
	}

	@Override
	public Film update(Film film, Long id) {
		Film filmZaIzmenu = this.findOne(id);
		filmZaIzmenu.setNaziv(film.getNaziv());
		filmZaIzmenu.setOpis(film.getOpis());
		filmZaIzmenu.setTrajanje(film.getTrajanje());
		filmZaIzmenu.setDatumPremijere(film.getDatumPremijere());
		filmZaIzmenu.setJezik(film.getJezik());
		filmZaIzmenu.setReditelj(film.getReditelj());
		filmZaIzmenu.setGlumci(film.getGlumci());
		filmZaIzmenu.setKategorija(film.getKategorija());

		Film sacuvan = filmRepository.save(filmZaIzmenu);

		return sacuvan;
	}

	@Override
	public void delete(Film film) {
		filmRepository.delete(film);
	}

}
