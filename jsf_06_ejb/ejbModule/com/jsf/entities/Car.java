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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the cars database table.
 * 
 */
@Entity
@Table(name = "cars")
@NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_samochodu")
	private int idSamochodu;

	@Column
	private String color;

	@Column
	private String marka;

	@Column
	private String model;

	@Lob
	@Column(name="photo_name")
	private String photoName;

	@Lob
	private String rocznik;

	//bi-directional many-to-one association to Zamowienia
	@OneToMany(mappedBy="car")
	private List<Zamowienia> zamowienias;

	public Car() {
	}

	public int getIdSamochodu() {
		return this.idSamochodu;
	}

	public void setIdSamochodu(int idSamochodu) {
		this.idSamochodu = idSamochodu;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getRocznik() {
		return this.rocznik;
	}

	public void setRocznik(String rocznik) {
		this.rocznik = rocznik;
	}

	public List<Zamowienia> getZamowienias() {
		return this.zamowienias;
	}

	public void setZamowienias(List<Zamowienia> zamowienias) {
		this.zamowienias = zamowienias;
	}

	public Zamowienia addZamowienia(Zamowienia zamowienia) {
		getZamowienias().add(zamowienia);
		zamowienia.setCar(this);

		return zamowienia;
	}

	public Zamowienia removeZamowienia(Zamowienia zamowienia) {
		getZamowienias().remove(zamowienia);
		zamowienia.setCar(null);

		return zamowienia;
	}

}