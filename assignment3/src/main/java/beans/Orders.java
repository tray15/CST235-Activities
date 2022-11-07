package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Orders {
	List<Order> orders = new ArrayList<Order>();
	
	public Orders() {
		orders.add(new Order("00001", "This is a product 1", (float)1.00, 1));
		orders.add(new Order("00002", "This is a product 2", (float)2.00, 2));
		orders.add(new Order("00003", "This is a product 3", (float)3.00, 3));
		orders.add(new Order("00004", "This is a product 4", (float)4.00, 4));
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
