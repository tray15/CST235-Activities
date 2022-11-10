package business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;
import data.OrdersDataService;

@Stateless
@Local(OrdersBusinessInterface.class)
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {
	
	@EJB
	OrdersDataService service;
		
	public OrdersBusinessService() {
	}
	
	@Override
	public void test() {
		System.out.println("Test method from OrdersBusinessService");
	}

	@Override
	public List<Order> getOrders() {
		return service.findAll();
	}

	@Override
	public void setOrders(List<Order> orders) {
		//this.orders = orders;
	}
	
}
