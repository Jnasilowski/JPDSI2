package com.jsfcourse.person;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.UserDAO;
import com.jsf.entities.Car;
import com.jsf.entities.User;

@Named
@ViewScoped
public class RejestracjaBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "personList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private User user = new User();
	private User loaded = null;
	UserDAO a ;
	@EJB
	UserDAO userDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public User getUser() {
		return user;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (User) flash.get("user");
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
	
		user.getLogin();
		try {
			
			if (userDAO.czyJestLogin(user.getLogin())==0) {
				// new record
				user.setRola("user");
				userDAO.create(user);
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Udało sie stworzyć konto!", null));
			} else {
				// existing record
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "login: " + user.getLogin() + " juz istnieje!" , null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		return PAGE_STAY_AT_THE_SAME;
		
	}
    public String doRejestr() {
    	 HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         session.invalidate();
        return "/rejestracja.xhtml/rejestracja?faces-redirect=true";
    }
}