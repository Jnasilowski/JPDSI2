package com.jsfcourse.person;

import java.util.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsf.dao.CarDAO;
import com.jsf.dao.ZamowieniaDAO;
import com.jsf.entities.Car;
import com.jsf.entities.User;
import com.jsf.entities.Zamowienia;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Named()
@RequestScoped
public class ZamowieniaListBB {
	private static final String PAGE_PERSON_EDIT = "personEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private Date dokiedy;
	private Date odkiedy;
	private String ID_zamowienia;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	ZamowieniaDAO zamowieniaDAO;
		
	@EJB
	CarDAO carDAO;
	public String getID_zamowienia() {
		return ID_zamowienia;
	}

	public void setID_zamowienia(String surname) {
		this.ID_zamowienia = surname;
	}
	public String printt() {
		return "123";
	}
	
	public List<Zamowienia> getFullList(){
		return zamowieniaDAO.getFullList();
	}
	public List<Car> listB(){
		
		List<Zamowienia> list = null;
		
		List<Car> listC = null;
		Map<String,Object> searchParams = new HashMap<String, Object>();
		listC = carDAO.getFullList();
		list = zamowieniaDAO.getFullList();
	
		if(dokiedy != null || odkiedy!=null) {
		for(Zamowienia c : list){
			
			if(odkiedy.before(c.getOdKiedy())  && dokiedy.after(c.getDoKiedy()) ) {
				listC.remove(c.getCar().getIdSamochodu());
			}
			if(odkiedy.after(c.getOdKiedy())  && odkiedy.before(c.getDoKiedy()) ) {
				listC.remove(c.getCar().getIdSamochodu());
			}
			if(dokiedy.after(c.getOdKiedy()) && dokiedy.before(c.getDoKiedy()) ) {
				listC.remove(c.getCar().getIdSamochodu());
			}
			if(dokiedy.equals(c.getOdKiedy())==true || dokiedy.equals(c.getDoKiedy())==true || odkiedy.equals(c.getOdKiedy()) ==true|| odkiedy.equals(c.getDoKiedy())==true)  {
				listC.remove(c.getCar().getIdSamochodu());

				
			}
			
		}
		if(odkiedy == null) {
			listC.remove(1);
		}
		
		}
		
		return listC;
	}
	public List<Zamowienia> getUserList(String a){
		List<Zamowienia> list = null;
		
		list = zamowieniaDAO.UsersList(a);
	
		return list;
	}

	public List<Zamowienia> getList(){
		List<Zamowienia> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (ID_zamowienia != null && ID_zamowienia.length() > 0){
			searchParams.put("ID_zamowienia", ID_zamowienia);
		}
		
		//2. Get list
		list = zamowieniaDAO.getList(searchParams,ID_zamowienia);
	
		return list;
	}

	public String newPerson(){
		Zamowienia zamowienie = new Zamowienia();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("zamowienie", zamowienie);
		
		return PAGE_PERSON_EDIT;
	}

	public String editPerson(Zamowienia zamowienia){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("zamowienia", zamowienia);
		
		return PAGE_PERSON_EDIT;
	}

	public String deletePerson(Zamowienia zamowienia){
		zamowieniaDAO.remove(zamowienia);
		return PAGE_STAY_AT_THE_SAME;
	}

	public Date getOdkiedy() {
		return odkiedy;
	}

	public void setOdkiedy(Date odkiedy) {
		this.odkiedy = odkiedy;
	}

	public Date getDokiedy() {
		return dokiedy;
	}

	public void setDokiedy(Date dokiedy) {
		this.dokiedy = dokiedy;
	}
}
