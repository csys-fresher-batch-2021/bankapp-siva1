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

import in.siva.util.NumberValidator;

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
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			
			int accountNo = (Integer)session.getAttribute("ACCOUNTNUMBER");
			String accno = request.getParameter("accno");
			String price = request.getParameter("amount");
			float amount = 0;
			int accNo = 0;
			accNo = NumberValidator.parseInteger(accno, "Invalid Account Number");
			amount = NumberValidator.parseFloat(price, "Invalid amount");
			double balance = TransactionManagement.withdrawAmount(accountNo, amount);
					
			if (balance > 0 && accountNo ==accNo) {

				String message = "Withdraw Success ";
				request.setAttribute(price, message);
				response.sendRedirect("summary.jsp?Balance=" + balance + "&infoMessage=" + message);
				 		
			} else {
				String message = "Withdrawal failed";
				response.sendRedirect("withdraw.jsp?errorMessage=" + message);
			}
		} catch (ValidException e) {
			String message = "Withdrawal failed";
			response.sendRedirect("withdraw.jsp?errorMessage=" + message);
		}
	}

}
