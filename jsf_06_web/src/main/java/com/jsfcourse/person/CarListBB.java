package com.jsfcourse.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsf.dao.CarDAO;
import com.jsf.entities.Car;


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
public class CarListBB {
	private static final String PAGE_PERSON_EDIT = "CarEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String marka;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	CarDAO carDAO;
		
	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}
	
	public List<Car> getFullList(){
		return carDAO.getFullList();
	}

	public List<Car> getList(){
		List<Car> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (marka != null && marka.length() > 0){
			searchParams.put("marka", marka);
		}
		
		//2. Get list
		list = carDAO.getList(searchParams);
	
		return list;
	}

	public String newCar(){
		Car car = new Car();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("car", car);
		
		return PAGE_PERSON_EDIT;
	}

	public String editCar(Car car){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("car", car);
		
		return PAGE_PERSON_EDIT;
	}

	public String deletePerson(Car person){
		carDAO.remove(person);
		return PAGE_STAY_AT_THE_SAME;
	}
}
