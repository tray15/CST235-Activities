package business;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import beans.Order;

@RequestScoped
@Path("/orders")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class OrdersRestService {
	@Inject
	OrdersBusinessInterface service;
	
	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrdersAsJson() {
		return service.getOrders();
	}
	
	@GET
	@Path("/getxml")
	@Produces(MediaType.APPLICATION_XML)
	public Order[] getOrdersAsXML() {
		List<Order> orders = service.getOrders();
		return orders.toArray(new Order[orders.size()]);
	}
	
	@POST
	@Path("/addorder")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addOrder(Order o) {
		service.addOrder(o);
	}
	/*
	//Example Client Code generation
	public void sendOrder(Order o) {
		HttpURLConnection conn = null;
		
		try {
			URL url = new URL("http://localhost:8080/assignment6/rest/orders/addorder");
			
			conn = (HttpURLConnection)url.openConnection();
			//We are sending a POST request because we are adding an order
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8");
			conn.setRequestProperty("Accept", "text/plain");
			//trigger POST
			conn.setDoOutput(true);
			
			try (OutputStream os = (OutputStream)conn.getOutputStream()) {
				byte[] input = o.getAsJson().getBytes("utf-8");
				//write bytes to the outputstream
				os.write(input, 0, input.length);
				os.close();
			}
			
		} catch (Exception e) {
			System.out.println("connection error: " + e);
		}
		//end connection after request
		conn.disconnect();
	}
	*/
}
