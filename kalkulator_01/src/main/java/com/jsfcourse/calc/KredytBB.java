package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;

@Named
@RequestScoped
public class KredytBB {
	private Double kwota;
	private Double okres;
	private Double oprocentowanie;
	private Double rata;

	@Inject
	FacesContext ctx;

	

	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Double getOkres() {
		return okres;
	}

	public void setOkres(Double okres) {
		this.okres = okres;
	}

	public Double getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(Double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public Double getRata() {
		return rata;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}
	
	public String calc() {
		try {
			double kwota = getKwota();
			double okres = getOkres();
			double oprocentowanie = getOprocentowanie();

			rata = (kwota + (kwota * (oprocentowanie / 100))) / okres;
			

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Poprawnie obliczono rate", null));
			return "showresult";
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd przetwarzania parametrów", null));
			return null;
		}
	}

	

}
