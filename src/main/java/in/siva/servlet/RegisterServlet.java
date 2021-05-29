package in.siva.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.siva.exception.ValidException;
import in.siva.model.User;
import in.siva.service.UserManagement;
import in.siva.util.NumberValidator;

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

		try {
			String name = request.getParameter("username");
			String password = request.getParameter("userpassword");
			String mobile = request.getParameter("number");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String price = request.getParameter("amount");
			long mobileNo = 0;
			mobileNo = NumberValidator.parseLong(mobile, "Invalid Mobile Number");
			float amount = 0;
			amount = NumberValidator.parseFloat(price, "Invalid amount");
			LocalDate date = LocalDate.now();
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setMobileNo(mobileNo);
			user.setAddress(address);
			user.setBalance(amount);
			user.setEmail(email);
			user.setCreated_date(Date.valueOf(date));
			
			boolean valid = UserManagement.registerDetails(user);// validating the details
			// condition for valid details
			if (valid) {

				String message = "Successfully Registered";
				response.sendRedirect("login.jsp?infoMessage=" + message);

			} else {
				String message = "!!Invalid Login Credentials!!";
				response.sendRedirect("registration.jsp?errorMessage=" + message);

			}
		} catch (ValidException e) {
			e.printStackTrace();
			String message = "!!Invalid Login Credentials!!";
			response.sendRedirect("registration.jsp?errorMessage=" + message);
		}

	}

}
