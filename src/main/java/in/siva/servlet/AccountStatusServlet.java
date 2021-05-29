package in.siva.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.siva.service.UserManagement;
import in.siva.util.NumberValidator;

/**
 * Servlet implementation class AccountStatusServlet
 */
@WebServlet("/AccountStatusServlet")
public class AccountStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String accNo = request.getParameter("accno");
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		int accno = 0;
		accno = NumberValidator.parseInteger(accNo, "Invalid Account Number");
		boolean isValid = UserManagement.accountStatus(accno,status);
		PrintWriter out = response.getWriter();
		out.print(isValid);
		out.flush();
	}

	
}
