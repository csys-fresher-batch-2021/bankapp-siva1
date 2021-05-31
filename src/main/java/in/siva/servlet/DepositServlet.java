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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String price = request.getParameter("amount");
			String accno = request.getParameter("accno");
			HttpSession session = request.getSession();
			
			int accountNo = (Integer)session.getAttribute("ACCOUNTNUMBER"); 
			float amount = 0;
			int accNo = 0;
			accNo = NumberValidator.parseInteger(accno, "Invalid Account Number");
			amount = NumberValidator.parseFloat(price, "Invalid amount");
			double balance = TransactionManagement.depositAmount(accountNo, amount);
			
			
			if (balance > 0 &&accountNo ==accNo) {

				String message = "Deposit Success ";
				response.sendRedirect("summary.jsp?Balance=" + balance + "&infoMessage=" + message);
			} 
			else
			{
				String message = "Deposit failed";
				response.sendRedirect("deposit.jsp?errorMessage=" + message);
			}
		} catch (ValidException  e) {
			String message = "Deposit failed";
			response.sendRedirect("deposit.jsp?errorMessage=" + message);

		}

	}
}