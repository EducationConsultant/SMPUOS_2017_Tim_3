package com.film.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.film.FilmApplication;
import com.film.models.Film;
import com.film.models.Glumac;
import com.film.models.Jezik;
import com.film.models.Kategorija;
import com.film.models.Reditelj;
import com.film.repository.FilmRepository;
import com.film.repository.GlumacRepository;
import com.film.repository.JezikRepository;
import com.film.repository.KategorijaRepository;
import com.film.repository.RediteljRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmApplication.class)
public class FilmServiceTest {

	@Autowired
	private FilmService filmService;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private JezikRepository jezikRepository;

	@Autowired
	private RediteljRepository rediteljRepository;

	@Autowired
	private GlumacRepository glumacRepository;

	@Autowired
	private KategorijaRepository kategorijaRepository;

	@Before
	public void setUp() {
		// film 1
		Jezik jezik1 = new Jezik();
		jezik1.setId(null);
		jezik1.setNaziv("srpski");
		jezikRepository.save(jezik1);

		Reditelj reditelj1 = new Reditelj();
		reditelj1.setId(null);
		reditelj1.setIme("Nikola");
		reditelj1.setPrezime("Kojo");
		rediteljRepository.save(reditelj1);

		Glumac glumac1 = new Glumac();
		glumac1.setId(null);
		glumac1.setIme("Jelisaveta");
		glumac1.setPrezime("Sablic");
		glumacRepository.save(glumac1);

		Glumac glumac2 = new Glumac();
		glumac2.setId(null);
		glumac2.setIme("Vesna");
		glumac2.setPrezime("Trivalic");
		glumacRepository.save(glumac2);

		Set<Glumac> glumci1 = new HashSet<>();
		glumci1.add(glumac1);
		glumci1.add(glumac2);

		Kategorija kategorija1 = new Kategorija();
		kategorija1.setId(null);
		kategorija1.setNaziv("komedija");
		kategorijaRepository.save(kategorija1);

		Film film1 = new Film();
		film1.setId(null);
		film1.setNaziv("Film 1");
		film1.setOpis("Opis filma");
		film1.setTrajanje(98);
		film1.setDatumPremijere(new Date());
		film1.setProsecnaOcena(5.0f);
		film1.setJezik(jezik1);
		film1.setReditelj(reditelj1);
		film1.setGlumci(glumci1);
		film1.setKategorija(kategorija1);

		// film 2
		Jezik jezik2 = new Jezik();
		jezik2.setId(null);
		jezik2.setNaziv("engleski");
		jezikRepository.save(jezik2);

		Reditelj reditelj2 = new Reditelj();
		reditelj2.setId(null);
		reditelj2.setIme("Kris");
		reditelj2.setPrezime("Kolumbo");
		rediteljRepository.save(reditelj2);

		Glumac glumac3 = new Glumac();
		glumac3.setId(null);
		glumac3.setIme("Patrik");
		glumac3.setPrezime("Dempsi");
		glumacRepository.save(glumac3);

		Glumac glumac4 = new Glumac();
		glumac4.setId(null);
		glumac4.setIme("Dzastin");
		glumac4.setPrezime("Cejmbers");
		glumacRepository.save(glumac4);

		Set<Glumac> glumci2 = new HashSet<>();
		glumci2.add(glumac3);
		glumci2.add(glumac4);

		Kategorija kategorija2 = new Kategorija();
		kategorija2.setId(null);
		kategorija2.setNaziv("drama");
		kategorijaRepository.save(kategorija2);

		Film film2 = new Film();
		film2.setId(null);
		film2.setNaziv("Film 2");
		film2.setOpis("Opis filma");
		film2.setTrajanje(150);
		film2.setDatumPremijere(new Date());
		film2.setProsecnaOcena(3.5f);
		film2.setJezik(jezik2);
		film2.setReditelj(reditelj2);
		film2.setGlumci(glumci2);
		film2.setKategorija(kategorija2);

		// film 3
		Reditelj reditelj3 = new Reditelj();
		reditelj3.setId(null);
		reditelj3.setIme("Zoran");
		reditelj3.setPrezime("Calic");
		rediteljRepository.save(reditelj3);

		Glumac glumac5 = new Glumac();
		glumac5.setId(null);
		glumac5.setIme("Dragomir");
		glumac5.setPrezime("Bojanic");
		glumacRepository.save(glumac5);

		Glumac glumac6 = new Glumac();
		glumac6.setId(null);
		glumac6.setIme("Marko");
		glumac6.setPrezime("Todorovic");
		glumacRepository.save(glumac6);

		Glumac glumac7 = new Glumac();
		glumac7.setId(null);
		glumac7.setIme("Lidija");
		glumac7.setPrezime("Vukicevic");
		glumacRepository.save(glumac7);

		Set<Glumac> glumci3 = new HashSet<>();
		glumci3.add(glumac5);
		glumci3.add(glumac6);
		glumci3.add(glumac7);

		Film film3 = new Film();
		film3.setId(null);
		film3.setNaziv("Film 3");
		film3.setOpis("Opis filma");
		film3.setTrajanje(103);
		film3.setDatumPremijere(new Date());
		film3.setProsecnaOcena(5.0f);
		film3.setJezik(jezik1);
		film3.setReditelj(reditelj3);
		film3.setGlumci(glumci3);
		film3.setKategorija(kategorija1);

		filmService.save(film1);
		filmService.save(film2);
		filmService.save(film3);
	}

	@Test
	public void test() {

		List<Film> filmovi = filmService.findAll();
		Assert.assertEquals(3, filmovi.size());

		Page<Film> filmoviByPage = filmService.findAllByPage(new PageRequest(0, 2));
		Assert.assertEquals(2, filmoviByPage.getNumberOfElements());
		Assert.assertEquals(3, filmoviByPage.getTotalElements());

		List<Film> aktuelni = filmService.findAktuelniFilmovi();
		Assert.assertEquals(3, aktuelni.size());

		List<Film> godinaPremijere = filmService.findByGodinaPremijere(2017);
		Assert.assertEquals(3, godinaPremijere.size());

		List<Film> kategorija = filmService.findByKategorija("komedija");
		Assert.assertEquals(2, kategorija.size());

		List<Film> reditelj = filmService.findByReditelj("Nikola", "Kojo");
		Assert.assertEquals(1, reditelj.size());

		List<Film> glumac = filmService.findByGlumac("Dragomir", "Bojanic");
		Assert.assertEquals(1, glumac.size());

		List<Film> ocena = filmService.findByProsecnaOcena(5.0f);
		Assert.assertEquals(2, ocena.size());

	}

	@After
	public void cleanUp() {
		filmRepository.deleteAll();
		jezikRepository.deleteAll();
		glumacRepository.deleteAll();
		rediteljRepository.deleteAll();
		kategorijaRepository.deleteAll();

	}

}
