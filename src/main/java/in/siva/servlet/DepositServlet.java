package in.siva.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.siva.service.TransactionManagement;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// PrintWriter out = response. getWriter();
		float amount = Float.parseFloat(request.getParameter("amount"));
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("LOGGED_IN_USER");
		double balance = TransactionManagement.depositAmount(name, amount);

		if (balance != 0) {

			String message = "Deposit Success ";
			response.sendRedirect("deposit.jsp?Balance=" + balance + "&infomessage=" + message);
		} else {
			String message = "Deposit failed";
			response.sendRedirect("deposit.jsp?errormessage=" + message);
		}
	}
}