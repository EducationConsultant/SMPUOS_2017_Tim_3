package com.korisnik.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String ime;

	@Column(nullable = false)
	private String prezime;

	@Column(nullable = false)
	private Date datumRodjenja;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipPola pol;

	@ManyToOne(fetch = FetchType.LAZY)
	private Adresa adresaStanovanja;

	@Column(nullable = false)
	private String mestoStanovanja;

	@Column(nullable = false, unique = true)
	private String korisnickoIme;

	@Column(nullable = false)
	private String lozinka;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipKorisnika tipKorisnika;

	@Column(nullable = false)
	private Date registrationDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipStatusaKorisnika statusKorisnika;

	public Boolean ulogovan;

	public Korisnik() {
	}

	@Override
	public String toString() {
		return String.format("Korisnik[id=%d, ime='%s', prezime='%s', korisnickoIme='%s']", id, ime, prezime,
				korisnickoIme);
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

	public String getMestoStanovanja() {
		return mestoStanovanja;
	}

	public void setMestoStanovanja(String mestoStanovanja) {
		this.mestoStanovanja = mestoStanovanja;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public TipStatusaKorisnika getUserStatus() {
		return statusKorisnika;
	}

	public void setUserStatus(TipStatusaKorisnika statusKorisnika) {
		this.statusKorisnika = statusKorisnika;
	}

}
