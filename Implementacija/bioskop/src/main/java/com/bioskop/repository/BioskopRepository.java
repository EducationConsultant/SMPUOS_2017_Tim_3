package com.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.models.Adresa;
import com.bioskop.models.Bioskop;

@Repository
public interface BioskopRepository extends JpaRepository<Bioskop, Long>{

	public Bioskop findByNaziv(String ime);
	public List<Bioskop> findByAdresa(Adresa adresa);
	public List<Bioskop> findAllByOrderByOcenaDesc();

}
