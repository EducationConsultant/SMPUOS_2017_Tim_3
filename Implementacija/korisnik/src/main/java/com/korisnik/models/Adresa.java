package com.korisnik.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Adresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nazivNaseljenogMesta;

	@Column(nullable = false)
	private String nazivUlice;

	@Column(nullable = false)
	private int broj;

	public Adresa() {
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

}
