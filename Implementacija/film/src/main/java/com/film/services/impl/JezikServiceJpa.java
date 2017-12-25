package com.film.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.Jezik;
import com.film.repository.JezikRepository;
import com.film.services.JezikService;

@Service
public class JezikServiceJpa implements JezikService {

	@Autowired
	private JezikRepository jezikRespitory;

	@Override
	public List<Jezik> findAll() {
		List<Jezik> jezici = jezikRespitory.findAll();
		return jezici;
	}

}
