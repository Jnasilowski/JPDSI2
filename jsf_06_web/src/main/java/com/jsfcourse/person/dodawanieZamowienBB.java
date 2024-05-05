package com.jsfcourse.person;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.UserDAO;
import com.jsf.dao.ZamowieniaDAO;
import com.jsf.entities.Car;
import com.jsf.entities.Zamowienia;
import com.jsf.entities.User;

@Named
@ViewScoped
public class dodawanieZamowienBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "personList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private Date dokiedy;
	private Date odkiedy;
	private Zamowienia zamowienie = new Zamowienia();
	private Zamowienia za = null;
	private User user = new User();
	private Car loaded = null;
	private Car car = new Car();
	
	UserDAO a ;
	@EJB
	UserDAO userDAO;
	@EJB
	ZamowieniaDAO zamowienieDAO;
	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public User getUser() {
		return user;
	}
	
	
	public void onLoad(Car c) throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = c;
		if (loaded != null) {
			car = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

/*
		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			user = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}
*/
	}

	public String saveData() {
		car.setIdSamochodu(1);
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        RemoteClient<User> client = RemoteClient.load(session);
        zamowienie.setDoKiedy(dokiedy);
        zamowienie.setDoKiedy(dokiedy);
        zamowienie.setCar(car);
		zamowienieDAO.create(zamowienie);
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Udało sie stworzyć konto!" + dokiedy, null));
		return PAGE_STAY_AT_THE_SAME;
	}

	public Date getDokiedy() {
		return dokiedy;
	}

	public void setDokiedy(Date dokiedy) {
		this.dokiedy = dokiedy;
	}

	public Date getOdkiedy() {
		return odkiedy;
	}

	public void setOdkiedy(Date odkiedy) {
		this.odkiedy = odkiedy;
	}
}