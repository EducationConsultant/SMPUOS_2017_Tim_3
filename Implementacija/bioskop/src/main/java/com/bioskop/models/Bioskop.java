package com.bioskop.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;




@Entity
@Table(name = "bioskop")
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
	private Adresa adresaBioskopa;
	
	@Column(nullable = false)
	@NotNull
	private Long ocena;
	
	public Bioskop() {
		
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

	public Adresa getAdresaBioskopa() {
		return adresaBioskopa;
	}

	public void setAdresaBioskopa(Adresa adresaBioskopa) {
		this.adresaBioskopa = adresaBioskopa;
	}

	public Long getOcena() {
		return ocena;
	}

	public void setOcena(Long ocena) {
		this.ocena = ocena;
	}
	
	
	
	


}
