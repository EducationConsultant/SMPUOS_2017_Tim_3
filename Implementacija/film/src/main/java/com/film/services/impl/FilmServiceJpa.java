package com.film.services.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

	@Override
	public List<Film> findAktuelniFilmovi() {
		List<Film> pronadjeniFilmovi = new ArrayList<Film>();
		List<Film> sviFilmovi = filmRepository.findAll();

		Date dateNow = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateNow);
		int yearNow = cal.get(Calendar.YEAR);

		for (Film film : sviFilmovi) {
			Date datumPremijere = film.getDatumPremijere();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(datumPremijere);

			String[] parts = dateString.split("-");
			int godinaPremijere = Integer.parseInt(parts[0]);

			if (yearNow == godinaPremijere) {
				pronadjeniFilmovi.add(film);
			}
		}

		return pronadjeniFilmovi;
	}

	@Override
	public List<Film> findByGodinaPremijere(int godinaPremijere) {
		List<Film> pronadjeniFilmovi = new ArrayList<Film>();
		List<Film> sviFilmovi = filmRepository.findAll();

		for (Film film : sviFilmovi) {
			Date datumPremijere = film.getDatumPremijere();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(datumPremijere);

			String[] parts = dateString.split("-");
			int godina = Integer.parseInt(parts[0]);

			if (godina == godinaPremijere) {
				pronadjeniFilmovi.add(film);
			}
		}
		return pronadjeniFilmovi;
	}

	@Override
	public List<Film> findByKategorija(Kategorija kategorija) {
		Kategorija kat = kategorijaRepository.findByNaziv(kategorija.getNaziv());
		return filmRepository.findByKategorija(kat);
	}

}
