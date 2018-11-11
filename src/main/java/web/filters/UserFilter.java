package web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import web.UserController;

/**
 * Servlet Filter implementation class UserFilter . Checks role of user. If user
 * have different role then redirects user at main page.
 */
@WebFilter({ "/userHome.jsp", "/userNewOrder.jsp", "/userView.jsp" })
public class UserFilter implements Filter {
	private Logger LOG = Logger.getLogger(UserController.class.getName());

	/**
	 * Default constructor.
	 */
	public UserFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servreq = (HttpServletRequest) request;
		HttpServletResponse serresp = (HttpServletResponse) response;
		try {
			String role = (String) servreq.getSession().getAttribute("role");
			if (!"user".equals(role)) {
				serresp.sendRedirect("home.jsp");
			}
			chain.doFilter(request, response);
		} catch (IOException | ServletException e) {
			LOG.error(e);
			servreq.getRequestDispatcher("home.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
