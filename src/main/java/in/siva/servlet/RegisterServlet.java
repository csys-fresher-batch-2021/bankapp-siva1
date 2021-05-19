package in.siva.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.siva.exception.ValidException;
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
			 {

		String name = request.getParameter("username");
		String password = request.getParameter("userpassword");
		Long mobile = Long.parseLong(request.getParameter("number"));
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		float amount = Float.parseFloat(request.getParameter("amount"));

		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setMobileNo(mobile);
		user.setAddress(address);
		user.setBalance(amount);
		user.setEmail(email);
		try {

			boolean valid = UserManagement.registerDetails(user);// validating the details
			// condition for valid details
			if (valid) {

				String message = "Successfully Registered";
				try {
					response.sendRedirect("login.jsp?infoMessage=" + message);
				} catch (IOException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				String message = "!!Invalid Login Credentials!!";
				try {
					response.sendRedirect("registration.jsp?errorMessage=" + message);
				} catch (IOException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (ValidException e) {
			String message = "!!Invalid Login Credentials!!";
			try {
				response.sendRedirect("registration.jsp?errorMessage=" + message);
			} catch (IOException e1) {
				//  Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
