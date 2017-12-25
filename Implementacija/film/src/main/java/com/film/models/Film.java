package com.film.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, name = "film_id")
	private Long id;

	@Column(nullable = false)
	@NotNull
	@Size(min = 3, max = 40)
	private String naziv;

	@Column(nullable = false)
	@NotNull
	private String opis;

	// trajanje u minutima
	@Column(nullable = false)
	@NotNull
	private int trajanje;

	@Column(nullable = false)
	@NotNull
	private Date datumPremijere;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "jezik_id", referencedColumnName = "jezik_id")
	private Jezik jezik;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "reditelj_id", referencedColumnName = "reditelj_id")
	@JsonBackReference
	private Reditelj reditelj;

	@ManyToMany
	@JoinTable(name = "film_glumci", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"), inverseJoinColumns = @JoinColumn(name = "glumac_id", referencedColumnName = "glumac_id"))
	private Set<Glumac> glumci;

	// private float prosjecnaOcjena;
	// public java.util.Collection<Glumac> glumac;
	// public Kategorija kategorija;

	public Film() {
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public Date getDatumPremijere() {
		return datumPremijere;
	}

	public void setDatumPremijere(Date datumPremijere) {
		this.datumPremijere = datumPremijere;
	}

	public Jezik getJezik() {
		return jezik;
	}

	public void setJezik(Jezik jezik) {
		this.jezik = jezik;
	}

	public Reditelj getReditelj() {
		return reditelj;
	}

	public void setReditelj(Reditelj reditelj) {
		this.reditelj = reditelj;
	}

	public Set<Glumac> getGlumci() {
		return glumci;
	}

	public void setGlumci(Set<Glumac> glumci) {
		this.glumci = glumci;
	}

}
