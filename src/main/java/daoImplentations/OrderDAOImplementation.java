package daoImplentations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import entities.Order;
import interfacesDAO.OrderDAO;

/**
 * 
 * this is realization of DAO which consist with method for interaction with
 * database like update , delete etc. Realization implemented due to entities
 * which prescribes essential rows from table Order.
 *
 */
public class OrderDAOImplementation implements OrderDAO {
	private static final Logger LOG = Logger.getLogger(OrderDAOImplementation.class.getName());
	private DataSource dataSource;

	public OrderDAOImplementation(DataSource theDataSuorce) {
		dataSource = theDataSuorce;
	}

	public void addOrder(int userId, int carID, String driver, int bill, String status, Date startDt, Date finishDt) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO car_rental.orders VALUES(default,?,?,?,?,?,?,?)";
			prepStat = conn.prepareStatement(sql);

			prepStat.setInt(1, userId);
			prepStat.setInt(2, carID);
			prepStat.setString(3, driver);
			prepStat.setInt(4, bill);
			prepStat.setString(5, status);
			prepStat.setDate(6, startDt);
			prepStat.setDate(7, finishDt);

			prepStat.executeUpdate();

		} catch (SQLException e) {
			LOG.error("can't insert an order " + e);
		} finally {
			close(conn, prepStat);
		}

	}

	public void delteOrder(int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM car_rental.orders WHERE id=?";
			prepStat = conn.prepareStatement(sql);

			prepStat.setInt(1, id);
			prepStat.executeUpdate();

		} catch (SQLException e) {
			LOG.error("can't delete an order from database " + e);
		} finally {
			close(conn, prepStat);
		}

	}

	public void setOrderStatus(String status, int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE car_rental.orders SET status=? WHERE id=?";
			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, status);
			prepStat.setInt(2, id);

			prepStat.executeUpdate();

		} catch (SQLException e) {
			LOG.error("can't set status at order" + e);
		} finally {
			close(conn, prepStat);
		}

	}

	public Order findById(int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSet = null;
		Order order = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.orders WHERE id=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setInt(1, id);
			resSet = prepStat.executeQuery();

			while (resSet.next()) {
				order = new Order();
				order.setId(resSet.getInt(Id));
				order.setCarId(resSet.getInt(CarId));
				order.setUserId(resSet.getInt(UserId));
				order.setBill(resSet.getInt(Bill));
				order.setStatus(resSet.getString(Status));
				order.setstart_date(resSet.getDate(StartDate));
				order.setFinish_date(resSet.getDate(FinishDate));
				order.setDriver(resSet.getString(Driver));
			}

		} catch (SQLException e) {
			LOG.error("can't get order from database " + e);
		} finally {
			close(conn, prepStat, resSet);
		}

		return order;
	}

	public ArrayList<Order> getOrders() {
		ArrayList<Order> listOrders = new ArrayList<Order>();
		Order order = null;
		Connection conn = null;
		Statement stat = null;
		ResultSet resSet = null;

		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			String sql = "SELECT * FROM car_rental.orders GROUP BY id";
			resSet = stat.executeQuery(sql);

			while (resSet.next()) {
				order = new Order();
				order.setId(resSet.getInt(Id));
				order.setCarId(resSet.getInt(CarId));
				order.setUserId(resSet.getInt(UserId));
				order.setBill(resSet.getInt(Bill));
				order.setStatus(resSet.getString(Status));
				order.setstart_date(resSet.getDate(StartDate));
				order.setFinish_date(resSet.getDate(FinishDate));
				order.setDriver(resSet.getString(Driver));

				listOrders.add(order);

			}

		} catch (SQLException e) {
			LOG.error("can't get orders from database " + e);
		} finally {
			close(conn, stat, resSet);
		}

		return listOrders;
	}

	public ArrayList<Order> findOrderByUserId(int userId) {
		ArrayList<Order> listOrders = new ArrayList<Order>();
		Order order = null;
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSet = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.order WHERE id=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setInt(1, userId);
			resSet = prepStat.executeQuery();

			while (resSet.next()) {
				order = new Order();
				order.setId(resSet.getInt(Id));
				order.setCarId(resSet.getInt(CarId));
				order.setUserId(resSet.getInt(UserId));
				order.setBill(resSet.getInt(Bill));
				order.setDriver(resSet.getString(Driver));
				order.setStatus(resSet.getString(Status));
				order.setstart_date(resSet.getDate(StartDate));
				order.setFinish_date(resSet.getDate(FinishDate));

				listOrders.add(order);

			}

		} catch (SQLException e) {
			LOG.error("can't get orders from database " + e);
		} finally {
			close(conn, prepStat, resSet);
		}

		return listOrders;
	}

	/**
	 * closes connection to the database
	 * 
	 * @param conn   connection
	 * @param stat   Statement
	 * @param resSet ResultSet
	 */
	private void close(Connection conn, Statement stat, ResultSet resSet) {
		try {
			if (resSet != null) {
				resSet.close();
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
		} catch (SQLException e) {
			LOG.error("can't close a connection" + e);
		}
	}

}
