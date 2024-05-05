package jpa_temp2;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the zamowienia database table.
 * 
 */
@Entity
@NamedQuery(name="Zamowienia.findAll", query="SELECT z FROM Zamowienia z")
public class Zamowienia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_zamowienia;

	@Temporal(TemporalType.DATE)
	@Column(name="do_kiedy")
	private Date doKiedy;

	@Temporal(TemporalType.DATE)
	@Column(name="od_kiedy")
	private Date odKiedy;

	//bi-directional many-to-one association to Car
	@ManyToOne
	@JoinColumn(name="Id_samochodu")
	private Car car;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Id_uzytkownika")
	private User user;

	public Zamowienia() {
	}

	public int getID_zamowienia() {
		return this.ID_zamowienia;
	}

	public void setID_zamowienia(int ID_zamowienia) {
		this.ID_zamowienia = ID_zamowienia;
	}

	public Date getDoKiedy() {
		return this.doKiedy;
	}

	public void setDoKiedy(Date doKiedy) {
		this.doKiedy = doKiedy;
	}

	public Date getOdKiedy() {
		return this.odKiedy;
	}

	public void setOdKiedy(Date odKiedy) {
		this.odKiedy = odKiedy;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}