package com.rezervacija.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Projekcija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projekcija_id", nullable = false, unique = true)
	private Long id;

	
	@Column(nullable = false)
	@NotNull
	private Long idFilma;
	
	@Column(nullable = false)
	@NotNull
	private String nazivFilma;
	
	@Column(nullable = false)
	@NotNull
	private Long idBioskopa;
	
	@Column(nullable = false)
	@NotNull
	private String nazivBioskopa;
	
	@Column(nullable = false)
	@NotNull
	private Long idSale;
	
	@Column(nullable = false)
	@NotNull
	private String oznakaSale;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date datumProjekcije;
	
	
	@OneToMany(mappedBy = "projekcija", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Rezervacija> rezervacije;
	
	private int brojAktivnihRezervacija;
	
	public Projekcija() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFilma() {
		return idFilma;
	}

	public void setIdFilma(Long idFilma) {
		this.idFilma = idFilma;
	}

	public String getNazivFilma() {
		return nazivFilma;
	}

	public void setNazivFilma(String nazivFilma) {
		this.nazivFilma = nazivFilma;
	}

	public Long getIdBioskopa() {
		return idBioskopa;
	}

	public void setIdBioskopa(Long idBioskopa) {
		this.idBioskopa = idBioskopa;
	}

	public String getNazivBioskopa() {
		return nazivBioskopa;
	}

	public void setNazivBioskopa(String nazivBioskopa) {
		this.nazivBioskopa = nazivBioskopa;
	}

	public Long getIdSale() {
		return idSale;
	}

	public void setIdSale(Long idSale) {
		this.idSale = idSale;
	}

	public String getOznakaSale() {
		return oznakaSale;
	}

	public void setOznakaSale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}

	public Date getDatumProjekcije() {
		return datumProjekcije;
	}

	public void setDatumProjekcije(Date datumProjekcije) {
		this.datumProjekcije = datumProjekcije;
	}

	public int getBrojAktivnihRezervacija() {
		return brojAktivnihRezervacija;
	}

	public void setBrojAktivnihRezervacija(int brojAktivnihRezervacija) {
		this.brojAktivnihRezervacija = brojAktivnihRezervacija;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
	
}
