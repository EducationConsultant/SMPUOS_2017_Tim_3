package com.film.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ocjena", 
uniqueConstraints = { @UniqueConstraint(columnNames = { "film_id", "username" }) })
public class Ocjena {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "film_id", referencedColumnName = "film_id")
	private Film film;

	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private float ocjena;

	public Ocjena(){
		
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	@JsonIgnore
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public float getOcjena() {
		return ocjena;
	}

	public void setOcjena(float ocjena) {
		this.ocjena = ocjena;
	}
	
	
	

}
