package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;

@ManagedBean
public class FormController {
	
	@Inject
	OrdersBusinessInterface service;
	
	@EJB
	MyTimerService timer;
	
	public String onSubmit() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		service.test();
		
		timer.setTimer(10000);
		
		/*
		 * System.out.println("You clicked the submit button.");
		 * System.out.println("The first name is " + user.getFirstName());
		 * System.out.println("The last name is " + user.getLastName());
		 */		
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		return "TestResponse.xhtml";
	}
	
	public OrdersBusinessInterface getService() {
		return service;
	}
	
}
