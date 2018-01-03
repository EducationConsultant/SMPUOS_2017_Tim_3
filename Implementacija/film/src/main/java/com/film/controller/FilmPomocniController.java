package com.film.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.Film;
import com.film.models.Glumac;
import com.film.models.Jezik;
import com.film.models.Kategorija;
import com.film.models.Reditelj;
import com.film.repository.GlumacRepository;
import com.film.repository.KategorijaRepository;
import com.film.services.JezikService;
import com.film.services.RediteljService;

@RestController
@RequestMapping("filmPomocni")
public class FilmPomocniController {
	@Autowired
	private JezikService jezikService;
	@Autowired
	private RediteljService rediteljService;
	@Autowired
	private KategorijaRepository kategorijaRepository;
	@Autowired
	private GlumacRepository glumacRepository;
	
	@RequestMapping(value = "/jezici", method = RequestMethod.GET)
	public ResponseEntity<List<Jezik>> getJezike() {
		List<Jezik> jezici=jezikService.findAll();
		
		return new ResponseEntity<List<Jezik>>(jezici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reditelji", method = RequestMethod.GET)
	public ResponseEntity<List<Reditelj>>  getReditelji(){
	
		List<Reditelj> reditelji=rediteljService.findAll();
		
		return new ResponseEntity<List<Reditelj>>(reditelji, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/kategorije", method = RequestMethod.GET)
	public ResponseEntity<List<Kategorija>> getKategorije(){
		List<Kategorija> kategorije=kategorijaRepository.findAll();
		
		return new ResponseEntity<List<Kategorija>>(kategorije, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/glumci", method = RequestMethod.GET)
	public ResponseEntity<List<Glumac>> getGlumci(){
	
		List<Glumac> glumci=glumacRepository.findAll();
		
		return new ResponseEntity<List<Glumac>>(glumci, HttpStatus.OK);
	}
}
