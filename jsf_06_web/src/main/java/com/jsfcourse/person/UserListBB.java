package com.jsfcourse.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

@Named()
@RequestScoped
public class UserListBB {
	private static final String PAGE_PERSON_EDIT = "personEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String nazwisko ;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
		
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String surname) {
		this.nazwisko = surname;
	}
	public String printt() {
		return "123";
	}

	public List<User> getFullList(){
		return userDAO.getFullList();
	}

	public List<User> getList(){
		List<User> list = null;
		
		//1. Prepare search 
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (nazwisko != null && nazwisko.length() > 0){
			searchParams.put("nazwisko", nazwisko);
		}
		
		//2. Get list
		list = userDAO.getList(searchParams);
	
		return list;
	}

	public String newPerson(){
		User user = new User();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("user", user);
		
		return PAGE_PERSON_EDIT;
	}

	public String editPerson(User user){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("user", user);
		
		return PAGE_PERSON_EDIT;
	}

	public String deletePerson(User person){
		userDAO.remove(person);
		return PAGE_STAY_AT_THE_SAME;
	}
}
