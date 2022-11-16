package business;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;


import beans.Order;
import data.OrdersDataService;

@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {
	
	@EJB
	OrdersDataService service;
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;
		
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

	@Override
	public void addOrder(Order o) {
		service.create(o);		
	}

	@Override
	public void sendOrder(Order o) {
		try 
		{
			javax.jms.Connection connection = connectionFactory.createConnection();
			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage message1 = session.createTextMessage();
			message1.setText("This is test message");
			messageProducer.send(message1);
			ObjectMessage message2 = session.createObjectMessage();
			message2.setObject(o);
			messageProducer.send(message2);
			connection.close();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}
	}
	
}
