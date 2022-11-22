package data;

import java.util.List;

import javax.ejb.Local;

import beans.Order;

@Local
public interface DataAccessInterface {
	public List<Order> findAll();
	
	public Order findById(int id);
	
	public void create(Order order);
	
	public void update(Order order);
	
	public void delete(Order order);
}
