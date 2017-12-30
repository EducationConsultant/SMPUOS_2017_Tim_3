package com.korisnik.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "korisnik_id", nullable = false, unique = true)
	private Long id;

	@Column(nullable = false)
	@NotNull
	@Size(min = 2, max = 30)
	private String ime;

	@Column(nullable = false)
	@NotNull
	@Size(min = 2, max = 30)
	private String prezime;

	@Column(nullable = false)
	@NotNull
	private Date datumRodjenja;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private TipPola pol;

	@ManyToOne
	@JoinColumn(name = "adresaStanovanja_id", referencedColumnName = "adresa_id")
	private Adresa adresaStanovanja;

	@Column(nullable = false, unique = true)
	@NotNull
	@Size(min = 5, max = 30)
	private String korisnickoIme;

	@Column(nullable = false)
	@NotNull
	@Size(min = 5, max = 30)
	private String lozinka;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipKorisnika tipKorisnika;

	@Column(nullable = false)
	private Date datumRegistracije;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipStatusaKorisnika statusKorisnika;

	public Boolean ulogovan;

	public Korisnik() {
	}

	@Override
	public String toString() {
		return String.format("Korisnik[ime='%s', prezime='%s', korisnickoIme='%s']", this.ime, this.prezime,
				this.korisnickoIme);
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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public TipPola getPol() {
		return pol;
	}

	public void setPol(TipPola pol) {
		this.pol = pol;
	}

	public Adresa getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public void setAdresaStanovanja(Adresa adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public Date getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(Date registrationDate) {
		this.datumRegistracije = registrationDate;
	}

	public TipStatusaKorisnika getStatusKorisnika() {
		return statusKorisnika;
	}

	public void setStatusKorisnika(TipStatusaKorisnika statusKorisnika) {
		this.statusKorisnika = statusKorisnika;
	}

	public Boolean getUlogovan() {
		return ulogovan;
	}

	public void setUlogovan(Boolean ulogovan) {
		this.ulogovan = ulogovan;
	}

}
