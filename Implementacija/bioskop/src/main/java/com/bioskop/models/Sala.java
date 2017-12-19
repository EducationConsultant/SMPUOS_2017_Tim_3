package com.bioskop.models;

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




@Entity
@Table
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sala_id", nullable = false, unique = true)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private String oznakaSale;
	
	@Column(nullable = false)
	@NotNull
	private int kapacitet;
	
	@Column(nullable = false)
	@NotNull
	private int brojSedistaRedovi;
	
	@Column(nullable = false)
	@NotNull
	private int brojSedistaKolone;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private SalaTip tip;
	
	//ima info o bioskopu
	@ManyToOne
	@JoinColumn(name="bioskop", referencedColumnName = "bioskop_id")
	private Bioskop bioskop;
	
	
	public Sala() {
	}

	
	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznakaSale() {
		return oznakaSale;
	}

	public void setOznakaSale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public int getBrojSedistaRedovi() {
		return brojSedistaRedovi;
	}

	public void setBrojSedistaRedovi(int brojSedistaRedovi) {
		this.brojSedistaRedovi = brojSedistaRedovi;
	}

	public int getBrojSedistaKolone() {
		return brojSedistaKolone;
	}

	public void setBrojSedistaKolone(int brojSedistaKolone) {
		this.brojSedistaKolone = brojSedistaKolone;
	}

	public SalaTip getTip() {
		return tip;
	}

	public void setTip(SalaTip tip) {
		this.tip = tip;
	}
	
	
	
	
}
