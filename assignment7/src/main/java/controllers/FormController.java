package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;
import data.OrdersDataService;

@ManagedBean
public class FormController {
	
	@Inject
	OrdersBusinessInterface service;
	
	@EJB
	MyTimerService timer;
	
	public String onLogoff() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "TestResponse.xhtml?faces-redirect=true";
	}
	
	public String onSendOrder() {
		service.sendOrder(new Order("1234", "OnSendOrderTest", 50.00f, 90));
		return "OrderResponse.xhtml";
	}
	
	public OrdersBusinessInterface getService() {
		return service;
	}

}
