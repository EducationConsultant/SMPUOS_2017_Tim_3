package com.rezervacija.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rezervacija.models.Projekcija;
import com.rezervacija.models.Rezervacija;
import com.rezervacija.models.RezervacijaTip;
import com.rezervacija.repository.ProjekcijaRepository;
import com.rezervacija.repository.RezervacijaRepository;
import com.rezervacija.services.RezervacijaService;

@Service
public class RezervacijaServiceJpa implements RezervacijaService {

	@Autowired
	private RezervacijaRepository repository;
	
	@Autowired
	private ProjekcijaRepository projekcijaRepository;

	@Override
	public Rezervacija findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Rezervacija> find() {
		return repository.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		Projekcija p = rezervacija.getProjekcija();
		int brojacP = p.getBrojAktivnihRezervacija() + 1;
		p.setBrojAktivnihRezervacija(brojacP);
		projekcijaRepository.save(p);
		
		return repository.save(rezervacija);
	}

	@Override
	public void delete(Rezervacija rezervacija) {
		repository.delete(rezervacija);
	}

	@Override
	public void deleteById(Long id) {
		repository.delete(id);
	}

	@Override
	public Rezervacija update(Rezervacija rezervacija, Long id) {
		Rezervacija rezervacijaZaIzmenu = repository.findOne(id);
		rezervacijaZaIzmenu.setTip(rezervacija.getTip());
		rezervacijaZaIzmenu.setBrojRedaSedista(rezervacija.getBrojRedaSedista());
		rezervacijaZaIzmenu.setBrojSedista(rezervacija.getBrojSedista());
		rezervacijaZaIzmenu.setDatumIstekaRezervacije(rezervacija.getDatumIstekaRezervacije());
		rezervacijaZaIzmenu.setDatumRezervacije(rezervacija.getDatumRezervacije());
		rezervacijaZaIzmenu.setIdKorisnika(rezervacija.getIdKorisnika());
		Rezervacija sacuvana = repository.save(rezervacijaZaIzmenu);
		return sacuvana;
	}

	@Override
	public Rezervacija deaktivacija(Long id) {
		Rezervacija rezervacija = repository.findOne(id);
		rezervacija.setTip(RezervacijaTip.OTKAZANA);
		int brojacAktivnih = rezervacija.getProjekcija().getBrojAktivnihRezervacija() - 1;
		rezervacija.getProjekcija().setBrojAktivnihRezervacija(brojacAktivnih);
		projekcijaRepository.save(rezervacija.getProjekcija());
		return repository.save(rezervacija);
	}

	@Override
	public List<Rezervacija> pregledAktRezKor(Long idKorisnika) {
		List<Rezervacija> rezervacije = repository.findByIdKorisnika(idKorisnika);
		List<Rezervacija> aktivne = new ArrayList<Rezervacija>();
		for (Rezervacija r : rezervacije) {
			if (r.getTip() == RezervacijaTip.AKTIVNA) {
				aktivne.add(r);
			}
		}
		return aktivne;
	}

	@Override
	public List<Rezervacija> pregledSvihRezKor(Long idKorisnika) {
		List<Rezervacija> rezervacije = repository.findByIdKorisnika(idKorisnika);
		return rezervacije;
	}
	

	@Override
	public List<Rezervacija> pregledAktivnihPoProjekcijama() {
		int brojacAktivnih = 0;
		List<Rezervacija> rezervacije = repository.findAll();
		List<Rezervacija> aktivne = new ArrayList<Rezervacija>();
		for (Rezervacija r : rezervacije) {
			if (r.getTip() == RezervacijaTip.AKTIVNA) {
				aktivne.add(r);
				brojacAktivnih++;
			}
		}
		for(Rezervacija r : aktivne) {
			r.getProjekcija().setBrojAktivnihRezervacija(brojacAktivnih);
		}
		
		return aktivne;
	}

	@Override
	public List<Rezervacija> pregledOtkazanihPoProjekcijama() {
		int brojacOtkazanih = 0;

		List<Rezervacija> rezervacije = repository.findAll();
		List<Rezervacija> otkazane = new ArrayList<Rezervacija>();
		for (Rezervacija r : rezervacije) {
			if (r.getTip() == RezervacijaTip.OTKAZANA) {
				otkazane.add(r);
				brojacOtkazanih++;
				
			}
		}
		for(Rezervacija r : otkazane) {
			r.getProjekcija().setBrojOtkazanihRezervacija(brojacOtkazanih);
		}
		return otkazane;
	}

	@Override
	public List<Rezervacija> getAktivneRezervacijeZaProjekciju(Long idProjekcije) {
		Projekcija projekcija = projekcijaRepository.findOne(idProjekcije);
		return repository.findByProjekcijaAndTip(projekcija, RezervacijaTip.AKTIVNA);	
	}

	@Override
	public List<Rezervacija> getOtkazaneRezervacijeZaProjekciju(Long idProjekcije) {
		Projekcija projekcija = projekcijaRepository.findOne(idProjekcije);
		return repository.findByProjekcijaAndTip(projekcija, RezervacijaTip.OTKAZANA);
	}
}
