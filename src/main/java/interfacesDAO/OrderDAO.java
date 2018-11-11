package interfacesDAO;

import java.sql.Date;
import java.util.ArrayList;

import entities.Order;

public interface OrderDAO {
	
	final static String Id = "id";
	final static String UserId = "userId";
	final static String CarId = "carId";
	final static String Bill = "bill";
	final static String Status = "status";
	final static String Driver = "driver";
	final static String StartDate = "start_date";
	final static String FinishDate = "end_date";
			
	
	
	
	
	/**
	 * this method inserts a new order into a database using incoming such as user's
	 * ID calculated price e.t.c.
	 * 
	 * @param userId   user which made an order
	 * @param carID    id of ordered car
	 * @param driver   sets if car with driver required
	 * @param bill     sets estimated price for the order
	 * @param status   sets status of order
	 * @param startDt  sets starting date of the order
	 * @param finishDt sets end date of the order
	 */
	public void addOrder(int userId, int carID, String driver, int bill, String status, Date startDt,
			Date finishDt);

	/**
	 * this method deletes order with prescribed id from database
	 * 
	 * @param id order's id
	 */
	void delteOrder(int id);

	/**
	 * this method sets status of with prescribed id
	 * 
	 * @param id     order's id
	 * @param status new status that has to be set
	 */
	void setOrderStatus(String status, int id);

	/**
	 * searches and returns order with prescribed id
	 * 
	 * @param id id of the order
	 * @return instance of founded order
	 */
	Order findById(int id);

	/**
	 * returns list of all orders from database
	 * 
	 * @return list of orders from database
	 */
	ArrayList<Order> getOrders();

	/**
	 * returns orders list of prescribed customer
	 * 
	 * @param userId customer's id
	 * @return list of orders
	 */
	ArrayList<Order> findOrderByUserId(int userId);

}
