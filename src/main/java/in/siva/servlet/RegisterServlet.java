package in.siva.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.siva.model.User;
import in.siva.service.UserManagement;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("userpassword");
		Long mobile = Long.parseLong(request.getParameter("number"));
		String email = request.getParameter("email");
		Long accno = Long.parseLong(request.getParameter("accno"));
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setMobileNo(mobile);
		user.setAccNo(accno);
		user.setEmail(email);
		boolean valid = UserManagement.registerDetails(user);// validating the details
		// condition for valid details
		if (valid) {
			String message = "Successfully Registered";
			response.sendRedirect("login.jsp?infoMessage=" + message);

		} else {
			String message = "!!Invalid Login Credentials!!";
			response.sendRedirect("registration.jsp?errorMessage=" + message);
		}
	}
}
