package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.film.models.Reditelj;

@Repository
public interface RediteljRepository extends JpaRepository<Reditelj, Long> {

}
