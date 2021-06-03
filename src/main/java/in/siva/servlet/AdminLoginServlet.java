package in.siva.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.siva.service.AdminService;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		try {
			boolean valid = AdminService.checkAdminLogin(userName, password);
			if (valid) {
				HttpSession session = request.getSession();
				session.setAttribute("LOGGED_IN_USER", userName);
				String message = "Successfully Logged In";
				response.sendRedirect("adminDisplay.jsp?message=" + message);
			}
			else {

				String message = "Invalid Login Credentials";
				response.sendRedirect("adminLogin.jsp?errorMessage=" + message);
			}
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			response.sendRedirect("adminLogin.jsp?errorMessage=" + errorMessage);
		}
	}
}
