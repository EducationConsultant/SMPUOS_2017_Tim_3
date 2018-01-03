package com.bioskop.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Adresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adresa_id", nullable = false, unique = true)
	private Long id;

	@Column(nullable = false)
	private String nazivNaseljenogMesta;

	@Column(nullable = false)
	private String nazivUlice;

	@Column(nullable = false)
	private int broj;

	@Column(nullable = false)
	private float geoDuzina;

	@Column(nullable = false)
	private float geoSirina;

	// mora zbog pregleda bioskopa na osnovu koordinata
	@OneToMany(mappedBy = "adresaBioskopa", fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Bioskop> bioskopi = new HashSet<>();

	public Adresa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivNaseljenogMesta() {
		return nazivNaseljenogMesta;
	}

	public void setNazivNaseljenogMesta(String nazivNaseljenogMesta) {
		this.nazivNaseljenogMesta = nazivNaseljenogMesta;
	}

	public String getNazivUlice() {
		return nazivUlice;
	}

	public void setNazivUlice(String nazivUlice) {
		this.nazivUlice = nazivUlice;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public float getGeoDuzina() {
		return geoDuzina;
	}

	public void setGeoDuzina(float geoDuzina) {
		this.geoDuzina = geoDuzina;
	}

	public float getGeoSirina() {
		return geoSirina;
	}

	public void setGeoSirina(float geoSirina) {
		this.geoSirina = geoSirina;
	}

	public Set<Bioskop> getBioskopi() {
		return bioskopi;
	}

	public void setBioskopi(Set<Bioskop> bioskopi) {
		this.bioskopi = bioskopi;
	}
	
	

}
