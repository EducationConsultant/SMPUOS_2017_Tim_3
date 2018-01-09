package com.bioskop.models;

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
uniqueConstraints = { @UniqueConstraint(columnNames = { "bioskop_id", "username" }) })
public class Ocjena {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bioskop_id", referencedColumnName = "bioskop_id")
	private Bioskop bioskop;

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
	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getOcjena() {
		return ocjena;
	}

	public void setOcjena(float ocjena) {
		this.ocjena = ocjena;
	}
	
	
	

}
