package daoImplentations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import entities.Car;
import interfacesDAO.CarDAO;

/**
 * 
 * 
 * this is realization of DAO which consist method for interaction with database
 * like update , delete etc. Realization implement due to entities which
 * prescribes essential rows from table Cars.
 *
 */
public class CarDAOImplementation implements CarDAO {

	private static final Logger LOG = Logger.getLogger(CarDAOImplementation.class.getName());
	private DataSource dataSource;

	public CarDAOImplementation(DataSource theDataSource) {
		dataSource = theDataSource;
	} 

	public Car findCarById(int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;
		Car car = null;

		try {
			conn = dataSource.getConnection();			
			String sql = "SELECT * FROM car_rental.cars WHERE id=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setInt(1, id);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				car = new Car();
				car.setId(resSt.getInt(Id));
				car.setCost(resSt.getInt(Cost));
				car.setMark(resSt.getString(Mark));
				car.setModel(resSt.getString(Model));
				car.setCarClass(resSt.getString(CarClass));

			}

		} catch (SQLException e) {
			LOG.error("can't get user from database " + e);
		} finally {
			close(conn, prepStat, resSt);
		}

		return car;
	}

	public void addCar(String model, String mark, String carClass, int cost) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();

			String sql = "INSERT INTO  car_rental.cars VALUES (default, ?, ?, ? , ?)";
			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, model);
			prepStat.setString(2, mark);
			prepStat.setString(3, carClass);
			prepStat.setInt(4, cost);

			prepStat.executeUpdate();
			LOG.info("car " + model + "was added");

		} catch (SQLException ex) {
			LOG.error("can't add the car" + ex);
		} finally {
			close(conn, prepStat);
		}

	}

	public void changeModel(int id, String model) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();

			String sql = "UPDATE car_rental.cars SET model=? WHERE id=?";

			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, model);
			prepStat.setInt(2, id);

			prepStat.executeUpdate();
			LOG.info("model was changed in car" + id);

		} catch (SQLException ex) {
			LOG.error("can't change carId is " + ex);
		} finally {
			close(conn, prepStat);
		}

	}

	public void changeMark(int id, String mark) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();

			String sql = "UPDATE  car_rental.cars SET mark=? WHERE id=?";

			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, mark);
			prepStat.setInt(2, id);

			prepStat.executeUpdate();
			LOG.info("mark was changed carId is " + id);

		} catch (SQLException ex) {
			LOG.error("can't change the model " + ex);
		} finally {
			close(conn, prepStat);
		}
	}

	public void changeClass(int id, String carClass) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {

			conn = dataSource.getConnection();

			String sql = "UPDATE car_rental.cars SET class=? WHERE id=?";

			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, carClass);
			prepStat.setInt(2, id);

			prepStat.executeUpdate();
			LOG.info("car class was changed carID is " + id);

		} catch (SQLException ex) {
			LOG.error("can't change car calss " + ex);
		} finally {
			close(conn, prepStat);
		}

	}

	public void changeCar(int id, String model, String mark, String carClass, int cost) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {

			conn = dataSource.getConnection();

			String sql = "UPDATE car_rental.cars SET model=? , mark=? , class=?, cost=? WHERE id=?";

			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, model);
			prepStat.setString(2, mark);
			prepStat.setString(3, carClass);
			prepStat.setInt(4, cost);
			prepStat.setInt(5, id);

			prepStat.executeUpdate();

		} catch (SQLException e) {
			LOG.error("can't change car  " + e);
		} finally {
			close(conn, prepStat);
		}

	}

	public void deleteCar(int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {

			conn = dataSource.getConnection();

			String sql = "DELETE FROM car_rental.cars WHERE id=?";

			prepStat = conn.prepareStatement(sql);

			prepStat.setInt(1, id);
			prepStat.executeUpdate();
			LOG.info("car with id " + id + " was deleted");

		} catch (SQLException ex) {
			LOG.error("can't delete a car" + ex);
		}

	}

	public ArrayList<Car> selectCarsByMark(String mark) {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car = null;
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.cars WHERE mark=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, mark);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				car = new Car();
				car.setId(resSt.getInt(Id));
				car.setCost(resSt.getInt(Cost));
				car.setMark(resSt.getString(Mark));
				car.setModel(resSt.getString(Model));
				car.setCarClass(resSt.getString(CarClass));

				cars.add(car);
			}

		} catch (SQLException ex) {
			LOG.error("can't read cars from database " + ex);
		} finally {
			close(conn, prepStat, resSt);
		}

		return cars;
	}

	public ArrayList<Car> selecCarsByClass(String model) {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car = null;
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.cars WHERE model=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, model);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				car = new Car();
				car.setId(resSt.getInt(Id));
				car.setCost(resSt.getInt(Cost));
				car.setMark(resSt.getString(Mark));
				car.setModel(resSt.getString(Model));
				car.setCarClass(resSt.getString(CarClass));

				cars.add(car);
			}

		} catch (SQLException ex) {
			LOG.error("can't get list of cars " + ex);
		} finally {
			close(conn, prepStat, resSt);
		}

		return cars;
	}

	public ArrayList<Car> searchCars(String param) {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car = null;
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;

		try {
			conn = dataSource.getConnection();
			String searchPar = "%" + param.toLowerCase() + "%";
			String sql = "SELECT * FROM car_rental.cars WHERE lower(id) LIKE ? OR lower(model) LIKE ? OR lower(mark) LIKE ?;";
			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, searchPar);
			prepStat.setString(2, searchPar);
			prepStat.setString(3, searchPar);
			resSt = prepStat.executeQuery();
			while (resSt.next()) {
				car = new Car();
				car.setId(resSt.getInt(Id));
				car.setCost(resSt.getInt(Cost));
				car.setMark(resSt.getString(Mark));
				car.setModel(resSt.getString(Model));
				car.setCarClass(resSt.getString(CarClass));
				cars.add(car);
			}

		} catch (SQLException ex) {
			LOG.error("can't get list of cars " + ex);
		} finally {
			close(conn, prepStat, resSt);
		}

		return cars;
	}

	public ArrayList<Car> selectCarsGroupByCost() {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car = null;
		Connection conn = null;
		Statement stat = null;
		ResultSet resSt = null;

		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			String sql = "SELECT * FROM car_rental.cars GROUP BY cost";
			resSt = stat.executeQuery(sql);

			while (resSt.next()) {
				car = new Car();
				car.setId(resSt.getInt(Id));
				car.setCost(resSt.getInt(Cost));
				car.setMark(resSt.getString(Mark));
				car.setModel(resSt.getString(Model));
				car.setCarClass(resSt.getString(CarClass));

				cars.add(car);
			}
		} catch (SQLException ex) {
			LOG.error("can't get cars list from database " + ex);
		} finally {
			close(conn, stat, resSt);
		}

		return cars;
	}

	public ArrayList<Car> selectCarsGroupByName() {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car = null;
		Connection conn = null;
		Statement stat = null;
		ResultSet resSt = null;

		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			String sql = "SELECT * FROM car_rental.cars GROUP BY model";
			resSt = stat.executeQuery(sql);

			while (resSt.next()) {
				car = new Car();
				car.setId(resSt.getInt(Id));
				car.setCost(resSt.getInt(Cost));
				car.setModel(resSt.getString(Mark));
				car.setModel(resSt.getString(Model));
				car.setCarClass(resSt.getString(CarClass));

				cars.add(car);
			}
		} catch (SQLException ex) {
			LOG.error("can't get cars list from database" + ex);
		} finally {
			close(conn, stat, resSt);
		}

		return cars;
	}

	/**
	 * closes connection to the database
	 * 
	 * @param conn  connection
	 * @param stat  Statement
	 * @param resSt ResultSet
	 */
	private void close(Connection conn, Statement stat, ResultSet resSt) {
		try {
			if (resSt != null) {
				resSt.close();
			}
			if (stat != null) {
				stat.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (SQLException ex) {
			LOG.error("problem during closing a connection" + ex);
		}

	}

	/**
	 * closes connection to the database
	 * 
	 * @param conn     Connection
	 * @param prepStat PreparedStatement
	 */
	private void close(Connection conn, PreparedStatement prepStat) {
		try {
			if (prepStat != null) {
				prepStat.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException ex) {
			LOG.error("problem during closing a connection" + ex);
		}

	}

}
