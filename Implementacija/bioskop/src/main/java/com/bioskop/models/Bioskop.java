package com.bioskop.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table
public class Bioskop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bioskop_id", nullable = false, unique = true)
	private Long id;
	
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String naziv;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "adresaBioskopa_id", referencedColumnName = "adresa_id")
	private Adresa adresa;
	
	private Long ocena;
	
	// nema info o svojim salama
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bioskop", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Sala> sale = new ArrayList<Sala>();
	
	
	public Bioskop() {
		
	}

	
	
	public List<Sala> getSale() {
		return sale;
	}



	public void setSale(List<Sala> sale) {
		this.sale = sale;
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

	

	public Adresa getAdresa() {
		return adresa;
	}



	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}



	public Long getOcena() {
		return ocena;
	}

	public void setOcena(Long ocena) {
		this.ocena = ocena;
	}
	
	
	
	


}
