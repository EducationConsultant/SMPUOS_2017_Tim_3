package com.rezervacija.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Column(nullable = false)
	@NotNull
	private Long idKorisnika;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date datumProjekcije;
	
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
	@NotNull
	private Boolean aktivna;
	
	@Column(nullable = false)
	@NotNull
	private int brojSedista;
	
	@Column(nullable = false)
	@NotNull
	private int brojRedaSedista;
	
	public Rezervacija() {
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

	public Long getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(Long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public Date getDatumProjekcije() {
		return datumProjekcije;
	}

	public void setDatumProjekcije(Date datumProjekcije) {
		this.datumProjekcije = datumProjekcije;
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

	public Boolean getAktivna() {
		return aktivna;
	}

	public void setAktivna(Boolean aktivna) {
		this.aktivna = aktivna;
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
	
	
	
	
}
