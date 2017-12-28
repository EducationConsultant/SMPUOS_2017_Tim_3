package com.rezervacija.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rezervacija.RezervacijaApplication;
import com.rezervacija.models.Projekcija;
import com.rezervacija.models.Rezervacija;
import com.rezervacija.models.RezervacijaTip;
import com.rezervacija.repository.ProjekcijaRepository;
import com.rezervacija.repository.RezervacijaRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RezervacijaApplication.class)
public class RezervacijaServiceTest {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	private ProjekcijaRepository projekcijaRepository;

	@Before
	public void setUp() {
		
		Long idFilma1 = new Long(1);
		String nazivFilma1 = "Hugo";
		Long idBioskopa1 = new Long(1);
		String nazivBioskopa1 = "Decji";
		Long idSale1 = new Long(1);
		String oznakaSale1 = "Sala 1";
		Calendar datumProjekcije1 = Calendar.getInstance();
		datumProjekcije1.set(2017, 2, 8);
		
		Projekcija p1 = new Projekcija();
		p1.setId(null);
		p1.setDatumProjekcije(datumProjekcije1.getTime());
		p1.setNazivFilma(nazivFilma1);
		p1.setNazivBioskopa(nazivBioskopa1);
		p1.setOznakaSale(oznakaSale1);
		p1.setIdFilma(idFilma1);
		p1.setIdBioskopa(idBioskopa1);
		p1.setIdSale(idSale1);
		projekcijaRepository.save(p1);
		
		
		Long idKorisnika1 = new Long(1);
		int brojSedista1 = 10;
		int brojRedaSedista1 = 8;
		Calendar datumRezervacije1 = Calendar.getInstance();
		datumRezervacije1.set(2017, 1, 8);
		Calendar datumIstekaRezervacije1 = Calendar.getInstance();
		datumIstekaRezervacije1.set(2017, 1, 8);
		RezervacijaTip tip = RezervacijaTip.AKTIVNA;

		Rezervacija r = new Rezervacija();
		r.setId(null);
		r.setIdKorisnika(idKorisnika1);
		r.setBrojRedaSedista(brojRedaSedista1);
		r.setBrojSedista(brojSedista1);
		r.setDatumIstekaRezervacije(datumIstekaRezervacije1.getTime());
		r.setDatumRezervacije(datumRezervacije1.getTime());
		r.setTip(tip);
		rezervacijaRepository.save(r);
	}


	@Test
	public void test() {
		List<Rezervacija> rezervacije = rezervacijaService.find();
		Assert.assertEquals(1, rezervacije.size());
		
		List<Rezervacija> rezAktivneKorisnika = rezervacijaService.pregledAktRezKor(new Long(1));
		Assert.assertEquals(1, rezAktivneKorisnika.size());
		
		List<Rezervacija> sveRez = rezervacijaService.pregledSvihRezKor(new Long(1));
		Assert.assertEquals(1, sveRez.size());
		
		List<Rezervacija> otkazaneRez = rezervacijaService.pregledOtkazanihPoProjekcijama();
		Assert.assertEquals(0, otkazaneRez.size());
		
		List<Projekcija> projekcije = projekcijaService.find();
		Assert.assertEquals(1, projekcije.size());
	}


	@After
	public void cleanUp() {
		rezervacijaRepository.deleteAll();
		projekcijaRepository.deleteAll();
	}






}























