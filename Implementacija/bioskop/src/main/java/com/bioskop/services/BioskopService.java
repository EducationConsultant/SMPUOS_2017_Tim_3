package com.bioskop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bioskop.models.Adresa;
import com.bioskop.models.AdresaKoordinate;
import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;


public interface BioskopService {

	public Bioskop findOne(Long id);
	public List<Bioskop> find(); 
	public Bioskop save(Bioskop bioskop);
	public void delete(Bioskop bioskop);
	public void deleteById(Long id);
	public Bioskop update(Bioskop noviBioskop, Long id);
	public Bioskop findByNaziv(String naziv);
	public List<Bioskop> findByAdresaBioskopa(Adresa adresa);  // i bioskope i sale prikazi
	public Bioskop saveOcena(Bioskop bioskop, Long id);
	public List<Bioskop> rangiranje();
	public List<Bioskop> findByLocation(AdresaKoordinate adresaKoordinate);

	
}
