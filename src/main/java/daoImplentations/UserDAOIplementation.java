package daoImplentations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import entities.User;
import interfacesDAO.UserDAO;

/**
 * 
 * this is realization of DAO which consist from method for interaction with
 * database like update , delete etc. Realization was made with entities which
 * prescribes essential rows from table Users.
 *
 */
public class UserDAOIplementation implements UserDAO {

	private static final Logger LOG = Logger.getLogger(UserDAOIplementation.class.getName());
	private DataSource dataSource;
	private static final String StUncofirmed = "unconfirmed";
	private static final String StConfirmed = "confirmed";
	private static final String StBlocked = "blocked";
	private static final String RoleUser = "user";
	private static final String RoleAdmin = "admin";
	private static final String RoleManager = "manager";

	public UserDAOIplementation(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public void addUser(String name, String password, String email) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO car_rental.users VALUES (default,?,?,?,?,?)";
			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, name);
			prepStat.setString(2, password);
			prepStat.setString(3, RoleUser);
			prepStat.setString(4, StUncofirmed);
			prepStat.setString(5, email);

			prepStat.executeUpdate();

		} catch (SQLException ex) {
			LOG.error("can't add user into a database " + ex);
		} finally {
			close(conn, prepStat);
		}

	}

	public void addManager(String name, String password, String email) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO car_rental.users VALUES (default,?,?,?,?,?)";
			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, name);
			prepStat.setString(2, password);
			prepStat.setString(3, RoleManager);
			prepStat.setString(4, StConfirmed);
			prepStat.setString(5, email);

			prepStat.executeUpdate();
			LOG.info("manager was added" + name);

		} catch (SQLException ex) {
			LOG.error("can't add manager into a database" + ex);
		} finally {
			close(conn, prepStat);
		}

	}

	public void addAdmin(String name, String password, String email) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO car_rental.users VALUES (default,?,?,?,?,?)";
			prepStat = conn.prepareStatement(sql);

			prepStat.setString(1, name);
			prepStat.setString(2, password);
			prepStat.setString(3, RoleAdmin);
			prepStat.setString(4, StConfirmed);
			prepStat.setString(5, email);

			prepStat.executeUpdate();
			LOG.info("admin was added" + name);

		} catch (SQLException ex) {
			LOG.error("can't add manager into a database" + ex);
		} finally {
			close(conn, prepStat);
		}

	}

	public void setStatus(String status, int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE car_rental.users SET status=? WHERE id=?";
			prepStat = conn.prepareStatement(sql);
			if (status.equalsIgnoreCase(StBlocked)) {
				prepStat.setString(1, StBlocked);
				prepStat.setInt(2, id);
				prepStat.executeUpdate();
			} else if (status.equalsIgnoreCase(StConfirmed)) {
				prepStat.setString(1, StConfirmed);
				prepStat.setInt(2, id);
				prepStat.executeUpdate();
			} else if (status.equalsIgnoreCase(StUncofirmed)) {
				prepStat.setString(1, StUncofirmed);
				prepStat.setInt(2, id);
				prepStat.executeUpdate();
			}

		} catch (SQLException ex) {
			LOG.error("can't change status" + ex);
		} finally {
			close(conn, prepStat);
		}

	}

	public void deleteUser(int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;

		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM car_rental.users WHERE id=?";
			prepStat = conn.prepareStatement(sql);

			prepStat.setInt(1, id);
			prepStat.executeUpdate();

		} catch (SQLException e) {
			LOG.error("can't delete user" + e);
		} finally {
			close(conn, prepStat);
		}

	}

	public boolean findUser(String name) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;
		boolean existing = false;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.users WHERE name=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, name);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				existing = true;
			}

		} catch (SQLException e) {
			LOG.error("user search failed " + e);
		} finally {
			close(conn, prepStat, resSt);
		}
		return existing;
	}

	public User findUserById(int id) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;
		User user = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.users WHERE id=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setInt(1, id);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				user = new User();
				user.setId(resSt.getInt(Id));
				user.setName(resSt.getString(Name));
				user.setPassword(resSt.getString(Password));
				user.setRole(resSt.getString(Role));
				user.setStatus(resSt.getString(Status));
				user.setEmail(resSt.getString(Email));

			}

		} catch (SQLException e) {
			LOG.error("can't get user from database " + e);
		} finally {
			close(conn, prepStat, resSt);
		}

		return user;
	}

	public User findByLogin(String name, String password) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;
		User user = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.users WHERE name=? AND password =?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, name);
			prepStat.setString(2, password);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				user = new User();
				user.setId(resSt.getInt(Id));
				user.setName(resSt.getString(Name));
				user.setPassword(resSt.getString(Password));
				user.setRole(resSt.getString(Role));
				user.setStatus(resSt.getString(Status));
				user.setEmail(resSt.getString(Email));

			}

		} catch (SQLException e) {
			LOG.error("can't get user from database " + e);
		} finally {
			close(conn, prepStat, resSt);
		}

		return user;
	}

	public User findByEmail(String mail) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;
		User user = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.users WHERE email=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, mail);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				user = new User();
				user.setId(resSt.getInt(Id));
				user.setName(resSt.getString(Name));
				user.setPassword(resSt.getString(Password));
				user.setRole(resSt.getString(Role));
				user.setStatus(resSt.getString(Status));
				user.setEmail(resSt.getString(Email));

			}

		} catch (SQLException e) {
			LOG.error("can't get user from database " + e);
		} finally {
			close(conn, prepStat, resSt);
		}

		return user;

	}

	public ArrayList<User> getUsers() {
		Connection conn = null;
		Statement stat = null;
		ResultSet resSt = null;
		ArrayList<User> usersList = new ArrayList<User>();
		User user = null;

		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			String sql = "SELECT * FROM car_rental.users";
			resSt = stat.executeQuery(sql);

			while (resSt.next()) {
				user = new User();
				user.setId(resSt.getInt(Id));
				user.setName(resSt.getString(Name));
				user.setPassword(resSt.getString(Password));
				user.setRole(resSt.getString(Role));
				user.setStatus(resSt.getString(Status));
				user.setEmail(resSt.getString(Email));

				usersList.add(user);
			}

		} catch (SQLException e) {
			LOG.error("can't get users list from database " + e);
		} finally {
			close(conn, stat, resSt);
		}

		return usersList;

	}

	public ArrayList<User> getUsersBySortName() {
		Connection conn = null;
		Statement stat = null;
		ResultSet resSt = null;
		ArrayList<User> usersList = new ArrayList<User>();
		User user = null;

		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			String sql = "SELECT * FROM car_rental.users GROUP BY name";
			resSt = stat.executeQuery(sql);

			while (resSt.next()) {
				user = new User();
				user.setId(resSt.getInt(Id));
				user.setName(resSt.getString(Name));
				user.setPassword(resSt.getString(Password));
				user.setRole(resSt.getString(Role));
				user.setStatus(resSt.getString(Status));
				user.setEmail(resSt.getString(Email));

				usersList.add(user);
			}

		} catch (SQLException e) {
			LOG.error("can't get users list from database " + e);
		} finally {
			close(conn, stat, resSt);
		}

		return usersList;

	}

	public ArrayList<User> getByStatus(String status) {
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet resSt = null;
		ArrayList<User> usersList = new ArrayList<User>();
		User user = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM car_rental.users WHERE status=?;";
			prepStat = conn.prepareStatement(sql);
			prepStat.setString(1, status);
			resSt = prepStat.executeQuery();

			while (resSt.next()) {
				user = new User();
				user.setId(resSt.getInt(Id));
				user.setName(resSt.getString(Name));
				user.setPassword(resSt.getString(Password));
				user.setRole(resSt.getString(Role));
				user.setStatus(resSt.getString(Status));
				user.setEmail(resSt.getString(Email));

				usersList.add(user);
			}

		} catch (SQLException e) {
			LOG.error("can't get users list from database " + e);
		} finally {
			close(conn, prepStat, resSt);
		}

		return usersList;

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
		} catch (SQLException e) {
			LOG.error("can't clsoe a conncetion" + e);
		}

	}

	/**
	 * closes connection to the database
	 * 
	 * @param conn     connection
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
			LOG.error("can't close a connection " + ex);
		}

	}

}
