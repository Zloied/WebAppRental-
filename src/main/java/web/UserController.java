package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import daoImplentations.UserDAOIplementation;
import entities.User;
import interfacesDAO.UserDAO;
import validation.Validation;

/**
 * Servlet implementation class UserController . This servlet is responsible for
 * action upon users such as add, delete, update, signIn, change user status.
 * Servlet read command from request and chose appropriate action.
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOG = Logger.getLogger(UserController.class.getName());
	private UserDAOIplementation userDaoImp;

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
			userDaoImp = new UserDAOIplementation(dataSource);
		} catch (Exception e) {
			LOG.error("problem during initialazing servlet coused by SQL " + e);
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
			listUsers(request);
			break;
		case "Delete":
			delete(request, response);
			response.sendRedirect("adminUsers.jsp");
			break;
		case "ChangeStatus":
			changeStatus(request, response);
			response.sendRedirect("adminHome.jsp");
			break;
		case "LogOut":
			request.getSession(false).invalidate();
			response.sendRedirect("home.jsp");
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String theCommand = request.getParameter("command");

		switch (theCommand) {
		case "Add":
			addUser(request, response);
			response.sendRedirect("adminAdd.jsp");
			break;
		case "Registration":
			register(request, response);
			break;
		case "Authentification":
			authentificaton(request, response);
			break;
		}

	}

	/**
	 * Checks inputed in request login and password. If parameters matches user in
	 * table users then it puts user's properties in session. After authorization
	 * redirect user to defined page.
	 * 
	 * @param request  is incoming HttpServletRequest from method doPost
	 * @param response is HttpServletResponse from method doPost
	 * @throws IOException
	 */
	private void authentificaton(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		if (Validation.getValid(request.getParameter("login")) && Validation.getValid(request.getParameter("pasw"))) {

			String login = request.getParameter("login");
			String password = request.getParameter("pasw");
			User user;
			user = userDaoImp.findByLogin(login, password);
			if (user != null) {
				session.setAttribute("userId", user.getId());
				session.setAttribute("role", user.getRole());
				session.setAttribute("status", user.getStatus());
				if (!UserDAO.StatusBlock.equals(user.getStatus())) {
					String role = user.getRole();
					switch (role) {
					case UserDAO.RoleUser:
						response.sendRedirect("userHome.jsp");
						break;

					case UserDAO.RoleAdmin:
						response.sendRedirect("adminHome.jsp");
						break;

					case UserDAO.RoleManager:
						response.sendRedirect("managerHome.jsp");
						break;

					}
				} else {
					request.getSession().setAttribute("msg", "your account has been blocked conntact the support");
					response.sendRedirect("loginPage.jsp");
				}

			} else {
				request.getSession().setAttribute("msg", "wrong login or password");
				response.sendRedirect("loginPage.jsp");
			}
		} else {
			request.getSession().setAttribute("msg", "login or password doesn't match requirements");
			response.sendRedirect("loginPage.jsp");

		}

	}

	/**
	 * This method gets parameters from request. If parameters pass validation and
	 * such users doesn't exists yet then adds new user into table users.At the end
	 * redirects users to defined page.
	 * 
	 * @param request  is incoming HttpServletRequest from method doPost
	 * @param response is HttpServletResponse from method doPost
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);

		if (Validation.getRegValid(request.getParameter("login"), request.getParameter("password1"),
				request.getParameter("password1")) && Validation.getValidEmail(request.getParameter("email"))) {
			String login = request.getParameter("login");
			String password1 = request.getParameter("password1");
			String email = request.getParameter("email");
			if (!userDaoImp.findUser(login)) {
				userDaoImp.addUser(login, password1, email);

				User user = userDaoImp.findByLogin(login, password1);
				session.setAttribute("userId", user.getId());
				session.setAttribute("role", user.getRole());
				session.setAttribute("status", user.getStatus());

				response.sendRedirect("userHome.jsp");

			} else {
				String msg = "Such user already exists";
				session.setAttribute("msg", msg);
				response.sendRedirect("registration.jsp");
			}
		} else {
			String msg = "Inputed data are inncorrect";
			session.setAttribute("msg", msg);
			response.sendRedirect("registration.jsp");
		}

	}

	/**
	 * Gets user's id and new status from request. Based on that information changes
	 * user's in table users.
	 * 
	 * @param request  is incoming HttpServletRequest from method doGet
	 * @param response is HttpServletResponse from method doGet
	 */
	private void changeStatus(HttpServletRequest request, HttpServletResponse response) {
		int usersId = Integer.parseInt(request.getParameter("usersId"));
		String status = request.getParameter("setStatus");
		if (status.equals("unconfirmed") || status.equalsIgnoreCase("confirmed")
				|| status.equalsIgnoreCase("blocked")) {
			userDaoImp.setStatus(status, usersId);
		} else {
			LOG.error("no match " + status);
		}

	}

	/**
	 * Gets user's id from request. Based on that information deletes user in table
	 * users.
	 * 
	 * @param request  is incoming HttpServletRequest from method doGet
	 * @param response is HttpServletResponse from method doGet
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int usersId = Integer.parseInt(request.getParameter("usersId"));
		userDaoImp.deleteUser(usersId);

	}

	/**
	 * This method gets parameters from request. If parameters pass validation and
	 * such users doesn't exists yet then adds new admin or manager into table
	 * users. At the end redirects users to defined page.
	 * 
	 * @param request  is incoming HttpServletRequest from method doPost
	 * @param response is HttpServletResponse from method doPost
	 */
	private void addUser(HttpServletRequest request, HttpServletResponse response) {

		if (Validation.getRegValid(request.getParameter("login"), request.getParameter("password1"),
				request.getParameter("password1")) && Validation.getValidEmail(request.getParameter("email"))) {

			String login = request.getParameter("login");
			String password1 = request.getParameter("password1");
			String email = request.getParameter("email");
			if (!userDaoImp.findUser(login)) {
				String role = request.getParameter("setRole");
				if (role != null) {
					switch (role) {
					case "admin":
						userDaoImp.addAdmin(login, password1, email);
						break;
					case "manager":
						userDaoImp.addManager(login, password1, email);
						break;
					}
				}
			} else {
				request.getSession().setAttribute("msg", "user with such loger already existst");
			}
		}
	}

	/**
	 * Returns list of all users from table users.
	 * 
	 * @param request is incoming HttpServletRequest from method doGet
	 */
	private void listUsers(HttpServletRequest request) {
		ArrayList<User> listUser;
		listUser = userDaoImp.getUsers();
		request.getSession().setAttribute("listUsers", listUser);

	}

}
