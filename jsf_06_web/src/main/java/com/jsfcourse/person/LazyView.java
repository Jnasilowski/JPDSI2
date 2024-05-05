package com.jsfcourse.person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.jsf.entities.Car;
import com.jsf.entities.User;
import com.jsf.dao.CarDAO;

@Named
@RequestScoped

public class LazyView implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	CarDAO carDAO;
	private List <Car> cars;
	private String marka;
	
	private LazyDataModel<Car> lazycar;
	
	public LazyDataModel<Car> getlazycar() {
		return lazycar;
	}
	
	
	@PostConstruct
	public void init() {
        
		cars = getCarList();
        lazycar = new LazyDataModel<Car>() {

        	@Override
            public String getRowKey(Car customer) {
                return String.valueOf(customer.getIdSamochodu());
            }

		@Override
		public List<Car> load(int arg0, int arg1, Map<String, SortMeta> arg2, Map<String, FilterMeta> arg3) {
			
			Map<String,Object> searchParams = new HashMap<String, Object>();
			
			if (marka != null && marka.length() > 0){
				searchParams.put("marka", marka);
				cars = carDAO.getCars(arg0, arg1,searchParams);
			}else
			cars = carDAO.getCars(arg0, arg1);

             int rowCount = (int) carDAO.getCarTotalCount();
             setRowCount(rowCount);

             return cars;
		}
		@Override
		public int count(Map<String, FilterMeta> arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
        
        };
        
	}

	public List <Car> getCars() {
		return cars;
	}

	public void setCars(List <Car> cars) {
		this.cars = cars;
	}
	public List<Car> getCarList() {
		return carDAO.getFullList();
	}


	public String getMarka() {
		return marka;
	}


	public void setMarka(String name) {
		this.marka = name;
	}


	
}