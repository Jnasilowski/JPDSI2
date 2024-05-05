package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_user;

	@Column
	private String hasło;

	@Column
	private String imie;

	@Column
	private String login;

	@Column
	private String nazwisko;
	
	@Column
	private int numer_telefonu;

	@Column
	private String rola;

	private int wiek;

	//bi-directional many-to-one association to Zamowienia
	@OneToMany(mappedBy="user")
	private List<Zamowienia> zamowienias;

	public User() {
	}

	public int getID_user() {
		return this.ID_user;
	}

	public void setID_user(int ID_user) {
		this.ID_user = ID_user;
	}

	public String getHasło() {
		return this.hasło;
	}

	public void setHasło(String hasło) {
		this.hasło = hasło;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public int getNumer_telefonu() {
		return this.numer_telefonu;
	}

	public void setNumer_telefonu(int numer_telefonu) {
		this.numer_telefonu = numer_telefonu;
	}

	public String getRola() {
		return this.rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public int getWiek() {
		return this.wiek;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}

	public List<Zamowienia> getZamowienias() {
		return this.zamowienias;
	}

	public void setZamowienias(List<Zamowienia> zamowienias) {
		this.zamowienias = zamowienias;
	}

	public Zamowienia addZamowienia(Zamowienia zamowienia) {
		getZamowienias().add(zamowienia);
		zamowienia.setUser(this);

		return zamowienia;
	}

	public Zamowienia removeZamowienia(Zamowienia zamowienia) {
		getZamowienias().remove(zamowienia);
		zamowienia.setUser(null);

		return zamowienia;
	}

}