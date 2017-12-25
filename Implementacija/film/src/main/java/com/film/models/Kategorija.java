package com.film.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Kategorija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, name = "kategorija_id")
	private Long id;

	@Column(nullable = false)
	@NotNull
	@Size(min = 3, max = 30)
	private String naziv;

	@OneToMany(mappedBy = "kategorija")
	@JsonManagedReference(value = "kategorija_filmovi")
	private Set<Film> filmovi;

	public Kategorija() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Film> getFilmovi() {
		return filmovi;
	}

	public void setFilmovi(Set<Film> filmovi) {
		this.filmovi = filmovi;
	}

}
