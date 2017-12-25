package com.rezervacija.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rezervacija_id", nullable = false, unique = true)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private Long idKorisnika;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date datumRezervacije;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date datumIstekaRezervacije;
	

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private RezervacijaTip tip;
	
	
	@Column(nullable = false)
	@NotNull
	private int brojSedista;
	
	@Column(nullable = false)
	@NotNull
	private int brojRedaSedista;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "projekcija_id")
	@JsonIgnore
	private Projekcija projekcija;
	
	public Rezervacija() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(Long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public Date getDatumRezervacije() {
		return datumRezervacije;
	}

	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	public Date getDatumIstekaRezervacije() {
		return datumIstekaRezervacije;
	}

	public void setDatumIstekaRezervacije(Date datumIstekaRezervacije) {
		this.datumIstekaRezervacije = datumIstekaRezervacije;
	}

	public RezervacijaTip getTip() {
		return tip;
	}

	public void setTip(RezervacijaTip tip) {
		this.tip = tip;
	}

	public int getBrojSedista() {
		return brojSedista;
	}

	public void setBrojSedista(int brojSedista) {
		this.brojSedista = brojSedista;
	}

	public int getBrojRedaSedista() {
		return brojRedaSedista;
	}

	public void setBrojRedaSedista(int brojRedaSedista) {
		this.brojRedaSedista = brojRedaSedista;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	

}