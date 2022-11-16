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
	
	public String onSubmit() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		service.test();
		
		timer.setTimer(10000);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
//		OrdersDataService orderService = new OrdersDataService();
		
//		Order createOrder = new Order("12346585", "Create Order", 1.47f, 57);
//		orderService.create(createOrder);
//		orderService.update(new Order(5, "848584858", "Update Order", 5.55f, 55));
//		orderService.delete(new Order(3));
//		orderService.findById(7);
		
		return "TestResponse.xhtml";
	}
	public String onSendOrder() {
		service.sendOrder(new Order("1234", "OnSendOrderTest", 50.00f, 90));
		return "OrderResponse.xhtml";
	}
	
	public OrdersBusinessInterface getService() {
		return service;
	}

}
