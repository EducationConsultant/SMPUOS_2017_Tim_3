package com.film.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.film.models.Reditelj;

@Repository
public interface RediteljRepository extends JpaRepository<Reditelj, Long> {

	public List<Reditelj> findByImeAndPrezime(String ime, String prezime);

}
