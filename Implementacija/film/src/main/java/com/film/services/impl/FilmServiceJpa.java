package com.film.services.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.film.models.Film;
import com.film.models.Glumac;
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
	public Film saveOcena(Film film, Long id) {
		Film filmZaOcenu = filmRepository.findOne(id);

		int suma = filmZaOcenu.getSumaOcena() + film.getOcena();
		filmZaOcenu.setSumaOcena(suma);

		int brojac = filmZaOcenu.getBrojac() + 1;
		filmZaOcenu.setBrojac(brojac);

		float prosek = (float) suma / (float) brojac;
		filmZaOcenu.setProsecnaOcena(prosek);

		filmRepository.save(filmZaOcenu);

		return filmZaOcenu;
	}

	@Override
	public Page<Film> findAllByPage(Pageable pageable) {
		return filmRepository.findAll(pageable);
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
	public List<Film> findByKategorija(String naziv) {
		Kategorija kat = kategorijaRepository.findByNaziv(naziv);
		return filmRepository.findByKategorija(kat);
	}

	@Override
	public List<Film> findByReditelj(String ime, String prezime) {
		List<Film> sviFilmovi = filmRepository.findAll();
		List<Film> filmovi = new ArrayList<Film>();

		for (Film f : sviFilmovi) {
			Reditelj reditelj = f.getReditelj();
			if (reditelj.getIme().contains(ime) && reditelj.getPrezime().contains(prezime)) {
				filmovi.add(f);
			}
		}
		return filmovi;
	}

	@Override
	public List<Film> findByGlumac(String ime, String prezime) {
		List<Film> sviFilmovi = filmRepository.findAll();
		List<Film> filmovi = new ArrayList<Film>();

		for (Film f : sviFilmovi) {
			Set<Glumac> glumci = f.getGlumci();

			for (Glumac g : glumci) {
				if (g.getIme().contains(ime) && g.getPrezime().contains(prezime)) {
					filmovi.add(f);
				}
			}
		}
		return filmovi;
	}

	@Override
	public List<Film> findByProsecnaOcena(float prosecnaOcena) {
		List<Film> filmovi = filmRepository.findByProsecnaOcena(prosecnaOcena);
		return filmovi;
	}

	@Override
	public List<Film> findByDatumPremijereBetween() {
		// TODO Auto-generated method stub
		Date currentDate=new Date();
		int currentYear=currentDate.getYear();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, currentYear-1);
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31);
		  
		Date start = cal.getTime();
		
		
		cal.set(Calendar.YEAR, currentYear+1);
		cal.set(Calendar.DAY_OF_YEAR, 1);   // 11 = december
		
		Date end=cal.getTime();
		
		List<Film> aktuelniFilmovi=filmRepository.findByDatumPremijereBetween(start, end);
		
		return  aktuelniFilmovi;
	}

	@Override
	public List<Film> findByProsecnaOcenaBetween(float minOcena, float maxOcena) {
		// TODO Auto-generated method stub
		List<Film> filmovi=filmRepository.findByProsecnaOcenaBetween(minOcena, maxOcena);
		
		return filmovi;
	}

}
