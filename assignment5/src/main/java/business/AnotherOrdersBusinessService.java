package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

@Stateless
@Local(OrdersBusinessInterface.class)
@Alternative
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {
	
	private List<Order> orders = new ArrayList<Order>();
	
	public AnotherOrdersBusinessService() {
//		orders.add(new Order("00001", "Product1 from AnotherOrdersBusinessService", (float)1.00, 1));
//		orders.add(new Order("00002", "This is a product 2", (float)2.00, 2));
//		orders.add(new Order("00003", "This is a product 3", (float)3.00, 3));
//		orders.add(new Order("00004", "This is a product 4", (float)4.00, 4));
	}
	
	@Override
	public void test() {
		System.out.println("Test method from AnotherOrdersBusinessService");
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
