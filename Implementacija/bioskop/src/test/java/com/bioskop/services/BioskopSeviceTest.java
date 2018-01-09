package com.bioskop.services;

import java.io.IOException;
import java.util.ArrayList;
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

import com.bioskop.BioskopApplication;
import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;
import com.bioskop.models.SalaTip;
import com.bioskop.repository.AdresaRepository;
import com.bioskop.repository.BioskopRepository;
import com.bioskop.repository.SalaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BioskopApplication.class)
public class BioskopSeviceTest {

	@Autowired
	private BioskopService bioskopService;

	@Autowired
	private SalaService salaService;
	
	@Autowired
	private BioskopRepository bioskopRepository;

	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private AdresaRepository adresaRepository;

	@Before
	public void setUp() {
		//bioskop 1
		String nazivBioskopa1 = "Zabavni bioskop";
		String nazivBioskopa2 = "Luna bioskop";
		
		Adresa adresa1 = new Adresa();
		adresa1.setId(null);
		adresa1.setNazivNaseljenogMesta("Novi Sad");
		adresa1.setNazivUlice("Brace Ribnikar");
		adresa1.setBroj(13);
		adresa1.setGeoDuzina(new Float(19.833549));
		adresa1.setGeoSirina(new Float(45.267136));
		adresaRepository.save(adresa1);
		
		Adresa adresa2 = new Adresa();
		adresa2.setId(null);
		adresa2.setNazivNaseljenogMesta("Novi Sad");
		adresa2.setNazivUlice("Danila Kisa");
		adresa2.setBroj(29);
		adresa2.setGeoDuzina(new Float(19.833549));
		adresa2.setGeoSirina(new Float(45.267136));
		adresaRepository.save(adresa2);
		
		
		Bioskop bioskop1 = new Bioskop();
		bioskop1.setAdresaBioskopa(adresa1);
		bioskop1.setId(null);
		bioskop1.setNaziv(nazivBioskopa1);
		bioskop1.setOcena(new Long(4));
		bioskopService.save(bioskop1);
		
		Bioskop bioskop2 = new Bioskop();
		bioskop2.setAdresaBioskopa(adresa2);
		bioskop2.setId(null);
		bioskop2.setNaziv(nazivBioskopa2);
		bioskop2.setOcena(new Long(9));
		bioskopService.save(bioskop2);
		
		String oznakaSale1 = "Sala 1";
		String oznakaSale2 = "Sala 2";
		int kapacitet1 = 100;
		int kapacitet2 = 200;
		int brojSedstaRedovi1 = 10;
		int brojSedistaRedovi2 = 20;
		int brojSedistaKolone1 = 10;
		int brojSedistaKolone2 = 10;
		SalaTip tip1 = SalaTip.OBICNA;
		SalaTip tip2 = SalaTip.ZA3D;
		
		Sala sala1 = new Sala();
		sala1.setOznakaSale(oznakaSale1);
		sala1.setKapacitet(kapacitet1);
		sala1.setBrojSedistaRedovi(brojSedstaRedovi1);
		sala1.setBrojSedistaKolone(brojSedistaKolone1);
		sala1.setId(null);
		sala1.setTip(tip1);
		salaService.saveSala(new Long(1), sala1);
		
		Sala sala2 = new Sala();
		sala2.setOznakaSale(oznakaSale2);
		sala2.setKapacitet(kapacitet2);
		sala2.setBrojSedistaRedovi(brojSedistaRedovi2);
		sala2.setBrojSedistaKolone(brojSedistaKolone2);
		sala2.setId(null);
		sala2.setTip(tip2);
		salaService.saveSala(new Long(2), sala2);
		
		List<Sala> sale = new ArrayList<Sala>();
		sale.add(sala1);
		sale.add(sala2);
		bioskop1.setSale(sale);
		bioskopService.save(bioskop1);
	}
	
	@Test
	public void test() throws IOException {
		List<Bioskop> bioskopiFindAll = bioskopService.find();
		Assert.assertEquals(2, bioskopiFindAll.size());
		
		Bioskop bioskop = bioskopService.findByNaziv("Zabavni bioskop");
		
		
		List<Sala> sale = salaService.findSveSalePoBioskopu(bioskop.getId());
		Assert.assertEquals(2, sale.size());
		
		Long ocena = new Long(2);
		bioskop.setOcena(ocena);
		Bioskop bioskopZaOceniti = bioskopService.saveOcena(bioskop, bioskop.getId(),"marko123");
		Assert.assertEquals(2, bioskopZaOceniti.getBrojac());
		
		Sala s = salaService.findSalaPoBioskopu(bioskop.getId(), new Long(1) );
		Assert.assertEquals("Sala 1", s.getOznakaSale());
		
		
	}
	
	@After
	public void cleanUp() {
		bioskopRepository.deleteAll();
		adresaRepository.deleteAll();
		salaRepository.deleteAll();
	}

	
}




















