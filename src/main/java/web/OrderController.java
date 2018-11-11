package web;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import daoImplentations.CarDAOImplementation;
import daoImplentations.OrderDAOImplementation;
import entities.Order;

/**
 * Servlet implementation class OrderController . This servlet responsibke for
 * action with orders in database such as deletem uprdate , adding a new one.
 * This servlet reads command from request and call
 */
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOG = Logger.getLogger(OrderController.class.getName());
	private OrderDAOImplementation orderDaoImp;

	@Resource(name = "jdbc/car_rental")
	DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			orderDaoImp = new OrderDAOImplementation(dataSource);
		} catch (Exception e) {
			LOG.error("problem during intializing servlet" + e);
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String theCommand = request.getParameter("command");
		if (theCommand == null) {
			theCommand = "List";
		}

		switch (theCommand) {
		case "List":
			listOrders(request, response);
			break;
		case "ChangeStatus":
			changeStat(request, response);
			response.sendRedirect("managerOrders.jsp");
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "List";
			}

			switch (theCommand) {
			case "List":
				listOrders(request, response);
				break;
			case "Add":
				addOrder(request, response);
				response.sendRedirect("userNewOrder.jsp");
				break;
			}

		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * This method changes status of certain order in database. Orders defines by
	 * orderId parameter from request.
	 * 
	 * @param request  is incoming HttpServletRequest from method doGet
	 * @param response is HttpServletResponse from method doGet
	 */
	private void changeStat(HttpServletRequest request, HttpServletResponse response) {
		String status = request.getParameter("setStatus");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		if (status.equals("requested") || status.equalsIgnoreCase("proceeding")
				|| status.equalsIgnoreCase("completed")) {
			orderDaoImp.setOrderStatus(status, orderId);
		} else {
			LOG.error("no match " + status);
		}

	}

	/**
	 * Add an order into the table order in DB. Order properties are extracted from
	 * request parameters.
	 * 
	 * @param request  is incoming HttpServletRequest from method doPost
	 * @param response is HttpServletResponse from method doPost
	 */
	private void addOrder(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		int carId = Integer.parseInt(request.getParameter("carId").toString());
		if (request.getSession(false).getAttribute("userId") != null) {
			CarDAOImplementation carDaoImp = new CarDAOImplementation(dataSource);
			int price = carDaoImp.findCarById(carId).getCost();
			String status = "requested";
			String driver = request.getParameter("driver");
			if (driver.equals("yes")) {
				driver = "yes";
			} else {
				driver = "no";
			}
			Date startDate = Date.valueOf(request.getParameter("startDate"));
			Date endDate = Date.valueOf(request.getParameter("endDate"));
			long days = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
			if (days >= 1) {
				int bill;
				if (driver.equals("yes")) {
					bill = (int) ((days * price) + (days * 35));
				} else {
					bill = (int) (days * price);
				}
				orderDaoImp.addOrder(userId, carId, driver, bill, status, startDate, endDate);
			}
		}
	}

	/**
	 * Checks role of user. Based on that role returns list of orders pulled out
	 * from database
	 * 
	 * @param request  is incoming HttpServletRequest from method doPost or doGet
	 * @param response is HttpServletResponse from method doPost or doGet
	 */
	private void listOrders(HttpServletRequest request, HttpServletResponse response) {
		String userRole = null;
		if (request.getSession().getAttribute("Role") != null) {
			userRole = request.getSession().getAttribute("Role").toString();
		}
		if (userRole == "customer") {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			ArrayList<Order> listOrders = orderDaoImp.findOrderByUserId(userId);

			request.getSession().setAttribute("listOrders", listOrders);
		} else {
			ArrayList<Order> listOrders = orderDaoImp.getOrders();
			request.getSession().setAttribute("listOrders", listOrders);
		}

	}

}
