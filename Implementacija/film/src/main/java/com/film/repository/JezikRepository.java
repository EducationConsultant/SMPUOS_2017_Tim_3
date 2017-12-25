package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.film.models.Jezik;

@Repository
public interface JezikRepository extends JpaRepository<Jezik, Long> {

}
