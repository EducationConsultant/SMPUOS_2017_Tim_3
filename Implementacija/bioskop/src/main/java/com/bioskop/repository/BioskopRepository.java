package com.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.models.Bioskop;

@Repository
public interface BioskopRepository extends JpaRepository<Bioskop, Long>{

}
