package in.siva.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("LoginServlet");
		
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		boolean valid = UserManagement.loginValidation(username, password);
		
		if (valid) {
			String message = "Successfully logged in";
			response.sendRedirect("login.jsp?infoMessage=" + message);

		} else {

			String message = "Invalid Login Credentials";
			response.sendRedirect("login.jsp?errorMessage=" + message);
		}
		

	}

}
