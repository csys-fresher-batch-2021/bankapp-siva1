package in.siva.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.siva.service.UserManagement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean valid = UserManagement.loginValidation(email, password);// Validating the UserName and Password
		// Condition for valid Login
		if (valid) {
			HttpSession session = request.getSession(); // Creating a Session
			session.setAttribute("LOGGED_IN_USER", email); // Setting username in session
			String message = "Successfully logged in";
			response.sendRedirect("display.jsp?infoMessage=" + message);

		} else {

			String message = "Invalid Login Credentials";
			response.sendRedirect("login.jsp?errorMessage=" + message);
		}

	}

}
