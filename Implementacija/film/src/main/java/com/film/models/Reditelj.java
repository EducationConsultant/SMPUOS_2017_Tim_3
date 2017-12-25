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

@Entity
@Table
public class Reditelj {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, name = "reditelj_id")
	private Long id;

	@Column(nullable = false)
	@NotNull
	@Size(min = 3, max = 40)
	private String ime;

	@Column(nullable = false)
	@NotNull
	@Size(min = 3, max = 40)
	private String prezime;

	@OneToMany(mappedBy = "reditelj")
	private Set<Film> filmovi;

	public Reditelj() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Set<Film> getFilmovi() {
		return filmovi;
	}

	public void setFilmovi(Set<Film> filmovi) {
		this.filmovi = filmovi;
	}

}
