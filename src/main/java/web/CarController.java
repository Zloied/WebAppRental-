package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import daoImplentations.CarDAOImplementation;
import entities.Car;
import validation.Validation;

/**
 * Servlet implementation class CarController . This servlet responsible for
 * action with cars in database such as delete, update, adding new one. This
 * servlet reads commands from request and call sufficient method for incoming
 * command Without any command(by default doGet) loads list of cars from
 * database.
 */
public class CarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOG = Logger.getLogger(CarController.class.getName());
	private CarDAOImplementation carDaoImp;

	@Resource(name = "jdbc/car_rental")
	private DataSource dataSource;

	/*
	 * 
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		try {
			carDaoImp = new CarDAOImplementation(dataSource);
		} catch (Exception e) {
			LOG.error("problem during initialazing servlet coused by SQL" + e);
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "List";
			}

			switch (theCommand) {
			case "List":
				listCars(request, response);
				break;

			case "Load":
				loadCar(request, response);
				response.sendRedirect("managerCarChange.jsp");
				break;
			case "Search":
				searchCars(request, response);
				break;
			}

		} catch (ServletException | IOException e) {
			LOG.error(e);
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
			case "Add":
				addCar(request, response);
				response.sendRedirect("managerCars.jsp");
				break;

			case "Delete":
				deleteCar(request, response);
				response.sendRedirect("managerCars.jsp");
				break;
			case "Update":
				updateCar(request, response);
				response.sendRedirect("managerHome.jsp");
				break;
			}

		} catch (IOException e) {
			LOG.error(e);
		}
	}

	/**
	 * takes parameter from request . Makes search in Cars table based on the
	 * parameter. Sets search outcome in session and redirects on web page.
	 */
	private void searchCars(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String search = request.getParameter("searchParameter");
		if (Validation.getValid(search)) {
			LOG.error(search);
			ArrayList<Car> carsList = carDaoImp.searchCars(search);
			request.getSession().setAttribute("CARS_LIST", carsList);
			request.setAttribute("CARS_LIST", carsList);
			LOG.error(carsList.toString());
			response.sendRedirect("managerCars.jsp");

		} else {
			response.sendRedirect("managerCars.jsp");
		}

	}

	/**
	 * takes parameters from request. Based on that updates information about Car in
	 * cars table.
	 * 
	 * @param request  is incoming HttpServletRequest from method doPost 
	 * @param response is HttpServletResponse from method doPost
	 */
	private void updateCar(HttpServletRequest request, HttpServletResponse response) {
		int carId = Integer.parseInt(request.getParameter("carId"));
		String model = request.getParameter("model");
		String mark = request.getParameter("mark");
		String carClass = request.getParameter("carClass");
		int cost = Integer.parseInt(request.getParameter("carCost"));

		carDaoImp.changeCar(carId, model, mark, carClass, cost);
	}

	private void deleteCar(HttpServletRequest request, HttpServletResponse response) {
		int carId = Integer.parseInt(request.getParameter("carId"));
		carDaoImp.deleteCar(carId);

	}

	/**
	 * Search for certain car in table cars based on request parameter. Sets search
	 * outcome in session and redirects on web page.
	 * 
	 * @param request is incoming HttpServletRequest from method doGet 
	 * @param response is HttpServletResponse from method doGet
	 */
	private void loadCar(HttpServletRequest request, HttpServletResponse response) {
		Car car = null;
		int carId = Integer.parseInt(request.getParameter("carId"));
		LOG.error("carId from session " + carId);
		car = carDaoImp.findCarById(carId);
		LOG.error("car from DB" + car.getMark());
		request.getSession().setAttribute("theCar", car);

	}

	/**
	 * Adds a new car into table cars . Takes car properties from request.
	 * 
	 * @param request  is incoming HttpServletRequest from method doPost 
	 * @param response is HttpServletResponse from method doPost
	 */
	private void addCar(HttpServletRequest request, HttpServletResponse response) {
		String mark = request.getParameter("mark");
		String model = request.getParameter("model");
		int cost = Integer.parseInt(request.getParameter("carCost"));
		String carClass = request.getParameter("carClass");

		carDaoImp.addCar(model, mark, carClass, cost);
	}
	/**
	 * Pulls out all Cars from table cars. Sets list of pulled cars as session attribute.  
	 * @param request  is incoming HttpServletRequest from method doPost or doGet 
	 * @param response is HttpServletResponse from method doPost or doGet
	 */
	private void listCars(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Car> carsList = carDaoImp.selectCarsGroupByCost();
		request.getSession().setAttribute("CARS_LIST", carsList);

	}

}
