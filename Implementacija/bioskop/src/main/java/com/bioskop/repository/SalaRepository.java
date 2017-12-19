package com.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.models.Sala;


@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{

}
