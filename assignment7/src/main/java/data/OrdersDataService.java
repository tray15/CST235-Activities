package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
@Alternative
public class OrdersDataService implements DataAccessInterface {
	private static final String DB_URL = "jdbc:mysql://localhost:3307/testapp?autoReconnect=true&useSSL=false";
	private static final String DB_USER = "root";
	private static final String PASSWORD = "root";
	
	private static final String FIND_ALL = "SELECT * FROM `orders`";
	private static final String FIND_BY_ID = "SELECT * FROM `orders` WHERE `ID`=?";
	private static final String CREATE_ONE = "INSERT INTO `orders` (`ORDER_NO`, `PRODUCT_NAME`, `PRICE`, `QUANTITY`) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE `orders` SET `ORDER_NO`=?, `PRODUCT_NAME`=?, `PRICE`=?, `QUANTITY`=? WHERE `ID`=?";
	private static final String DELETE_ONE = "DELETE FROM `orders` WHERE `ID`=?";

	@Override
	public List<Order> findAll() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(FIND_ALL);
			
			List<Order> orders = new ArrayList<Order>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String orderNumber = rs.getString("ORDER_NO");
				String name = rs.getString("PRODUCT_NAME");
				float price = rs.getFloat("PRICE");
				int quantity = rs.getInt("QUANTITY");
				
				orders.add(new Order(id, orderNumber, name , price, quantity));
			}
			rs.close();
			return orders;
			
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Order findById(int id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int orderId = rs.getInt("ID");
				String orderNo = rs.getString("ORDER_NO");
				String productName = rs.getString("PRODUCT_NAME");
				float price = rs.getFloat("PRICE");
				int quantity = rs.getInt("QUANTITY");
				Order found = new Order(orderId, orderNo, productName, price, quantity);
				
				System.out.println("ID: " + orderId + " OrderNumber: " + orderNo + " ProductName: " + productName + " Price: " + price + " Quantity: " + quantity);
				
				return found;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void create(Order order) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(CREATE_ONE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, order.getOrderNumber());
			stmt.setString(2, order.getProductName());
			stmt.setFloat(3, order.getPrice());
			stmt.setInt(4, order.getQuantity());
			
			stmt.executeUpdate();
			System.out.println("Executing query for create... ");
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Order order) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, order.getOrderNumber());
			stmt.setString(2, order.getProductName());
			stmt.setFloat(3, order.getPrice());
			stmt.setInt(4, order.getQuantity());
			stmt.setInt(5, order.getId());
			
			stmt.executeUpdate();
			System.out.println("Executing query for update... ");
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Order order) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(DELETE_ONE, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, order.getId());
			
			stmt.executeUpdate();
			System.out.println("Executing query for delete... ");
			
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
