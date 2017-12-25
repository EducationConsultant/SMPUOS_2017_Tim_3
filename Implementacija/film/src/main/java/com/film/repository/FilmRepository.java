package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.film.models.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

}
