package com.bioskop.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;
import com.bioskop.services.SalaService;

@Service
public class SalaServiceJpa implements SalaService {

	@Override
	public Sala findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sala> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sala save(Sala sala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Sala sala) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Sala novaSala, Long salaId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Sala> findByBioskop(Bioskop bioskop) {
		// TODO Auto-generated method stub
		return null;
	}

}
