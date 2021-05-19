package in.siva.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.siva.exception.ValidException;
import in.siva.service.TransactionManagement;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		float amount = Float.parseFloat(request.getParameter("amount"));
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("LOGGED_IN_USER");
		
		try {
		double balance = TransactionManagement.withdrawAmount(email, amount);

		if (balance != 0) {

			String message = "Withdraw Success ";
			try {
				response.sendRedirect("summary.jsp?Balance=" + balance + "&infomessage=" + message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String message = "Withdrawal failed";
			try {
				response.sendRedirect("withdraw.jsp?errormessage=" + message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} catch (ValidException e) {
		String message = "Withdrawal failed";
		try {
			response.sendRedirect("withdraw.jsp?errormessage=" + message);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}}
