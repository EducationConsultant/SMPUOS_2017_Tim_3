package com.film.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.Reditelj;
import com.film.repository.RediteljRepository;
import com.film.services.RediteljService;

@Service
public class RediteljServiceJpa implements RediteljService {

	@Autowired
	private RediteljRepository rediteljRepository;

	@Override
	public List<Reditelj> findAll() {
		List<Reditelj> reditelji = rediteljRepository.findAll();
		return reditelji;
	}

}
