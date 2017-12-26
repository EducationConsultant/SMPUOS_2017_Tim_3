package com.korisnik.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.korisnik.KorisnikApplication;
import com.korisnik.models.Adresa;
import com.korisnik.models.Korisnik;
import com.korisnik.models.TipKorisnika;
import com.korisnik.models.TipPola;
import com.korisnik.models.TipStatusaKorisnika;
import com.korisnik.repository.AdresaRepository;
import com.korisnik.repository.KorisnikRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KorisnikApplication.class)
public class KorisnikServiceTest {

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private AdresaRepository adresaRepository;

	@Before
	public void setUp() {

		// korisnik 1
		Calendar datumRodjenja1 = Calendar.getInstance();
		datumRodjenja1.set(1985, 1, 8);

		Calendar datumRegistracije1 = Calendar.getInstance();
		datumRegistracije1.set(2008, 5, 18);

		Adresa adresa1 = new Adresa();
		adresa1.setId(null);
		adresa1.setNazivNaseljenogMesta("Novi Sad");
		adresa1.setNazivUlice("Brace Ribnikar");
		adresa1.setBroj(13);
		adresa1.setGeoDuzina(new Float(19.833549));
		adresa1.setGeoSirina(new Float(45.267136));
		adresaRepository.save(adresa1);

		Korisnik korisnik1 = new Korisnik();
		korisnik1.setId(null);
		korisnik1.setIme("Petar");
		korisnik1.setPrezime("Petricevic");
		korisnik1.setDatumRodjenja(datumRodjenja1.getTime());
		korisnik1.setPol(TipPola.M);
		korisnik1.setAdresaStanovanja(adresa1);
		korisnik1.setKorisnickoIme("petronije");
		korisnik1.setLozinka("sifra123");
		korisnik1.setTipKorisnika(TipKorisnika.ADMIN);
		korisnik1.setDatumRegistracije(datumRegistracije1.getTime());
		korisnik1.setStatusKorisnika(TipStatusaKorisnika.AKTIVIRAN);
		korisnik1.setUlogovan(false);

		korisnikService.save(korisnik1);

		// korisnik 2
		Calendar datumRodjenja2 = Calendar.getInstance();
		datumRodjenja2.set(1992, 7, 19);

		Korisnik korisnik2 = new Korisnik();
		korisnik2.setId(null);
		korisnik2.setIme("Mika");
		korisnik2.setPrezime("Milovanovic");
		korisnik2.setDatumRodjenja(datumRodjenja2.getTime());
		korisnik2.setPol(TipPola.Ž);
		korisnik2.setAdresaStanovanja(adresa1);
		korisnik2.setKorisnickoIme("mikica");
		korisnik2.setLozinka("sifra123");
		korisnik2.setTipKorisnika(TipKorisnika.REGKORISNIK);
		korisnik2.setDatumRegistracije(new Date());
		korisnik2.setStatusKorisnika(TipStatusaKorisnika.AKTIVIRAN);
		korisnik2.setUlogovan(false);

		korisnikService.save(korisnik2);

		// korisnik 3

		Calendar datumRodjenja3 = Calendar.getInstance();
		datumRodjenja1.set(1965, 2, 2);

		Adresa adresa2 = new Adresa();
		adresa2.setId(null);
		adresa2.setNazivNaseljenogMesta("Beograd");
		adresa2.setNazivUlice("Nede Spasojevic");
		adresa2.setBroj(7);
		adresa2.setGeoDuzina(new Float(20.457273));
		adresa2.setGeoSirina(new Float(44.787197));
		adresaRepository.save(adresa2);

		Korisnik korisnik3 = new Korisnik();
		korisnik3.setId(null);
		korisnik3.setIme("Slobodan");
		korisnik3.setPrezime("Slobodanovic");
		korisnik3.setDatumRodjenja(datumRodjenja3.getTime());
		korisnik3.setPol(TipPola.M);
		korisnik3.setAdresaStanovanja(adresa2);
		korisnik3.setKorisnickoIme("slobica");
		korisnik3.setLozinka("sifra123");
		korisnik3.setTipKorisnika(TipKorisnika.REGKORISNIK);
		korisnik3.setDatumRegistracije(new Date());
		korisnik3.setStatusKorisnika(TipStatusaKorisnika.AKTIVIRAN);
		korisnik3.setUlogovan(true);

		korisnikService.save(korisnik3);

	}

	@Test
	public void test() throws IOException {
		List<Korisnik> korisnikFindAll = korisnikService.findAll();
		Assert.assertEquals(3, korisnikFindAll.size());

		List<Korisnik> aktivirani = korisnikService.aktivniKorisnici();
		Assert.assertEquals(3, aktivirani.size());

		List<Korisnik> dekativirani = korisnikService.dektiviraniKorisnici();
		Assert.assertEquals(0, dekativirani.size());

		List<Korisnik> findByImePrezime = korisnikService.findByImeAndPrezime("Petar", "");
		Assert.assertEquals(1, findByImePrezime.size());

	}

	@After
	public void cleanUp() {
		korisnikRepository.deleteAll();
		adresaRepository.deleteAll();
	}

}
