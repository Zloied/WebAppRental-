package interfacesDAO;

import java.util.ArrayList;

import entities.User;

public interface UserDAO {

	final static String Name = "name";
	final static String Id = "id";
	final static String Password = "password";
	final static String Role = "role";
	final static String Status = "status";
	final static String Email = "email";
	final static String StatusBlock = "blocked";
	final static String StatusUnconf = "unsigned";
	final static String StatusConfirm = "confirmed";
	final static String RoleAdmin = "admin";
	final static String RoleUser = "user";
	final static String RoleManager = "manager";
	

	/**
	 * this method adds new user into a database
	 * 
	 * @param name     user's login
	 * @param password user's password for signing in
	 * 
	 */
	void addUser(String name, String password, String email);

	/**
	 * add manager into a database with default status eq. active
	 * 
	 * @param name     manager's login
	 * @param password manager's password
	 * @param email    user's email
	 */
	void addManager(String name, String password, String email);

	/**
	 * adds admin into a database with default status eq. active
	 * 
	 * @param name     admin's name
	 * @param password admin's password
	 * @param email    user's email
	 * 
	 */
	void addAdmin(String name, String password, String email);

	/**
	 * changes users status
	 * 
	 * @param status new status that has to be set
	 * @param id     user's id
	 */
	void setStatus(String status, int id);

	/**
	 * deletes user from database
	 * 
	 * @param id user's id
	 */
	void deleteUser(int id);

	/**
	 * returns user from database with matched id
	 * 
	 * @param id user's id
	 * @return user from database
	 */
	User findUserById(int id);

	/**
	 * returns user from database with matched login
	 * 
	 * @param name     user's name for search
	 * @param password checks is password matches with database
	 * @return user from database
	 */
	User findByLogin(String name, String password);

	/**
	 * checks does users with prescribed name already exist or not in database
	 * 
	 * @param name login to checked
	 * @return boolean true if user already exist in database else returns false
	 */
	boolean findUser(String name);

	/**
	 * returns user from database with matched email
	 * 
	 * @param mail user's email
	 * @return user from database
	 */
	User findByEmail(String mail);

	/**
	 * returns list of all users from database
	 * 
	 * @return list of users
	 */
	ArrayList<User> getUsers();

	/**
	 * returns list of users sorted out by name
	 * 
	 * @return list of users
	 */
	ArrayList<User> getUsersBySortName();

	/**
	 * returns list of users with prescribed status
	 * 
	 * @param status user's status for filtering
	 * @return list of users
	 */
	ArrayList<User> getByStatus(String status);
}
