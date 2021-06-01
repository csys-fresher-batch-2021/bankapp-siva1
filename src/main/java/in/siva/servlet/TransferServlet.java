package in.siva.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.siva.exception.ValidException;
import in.siva.service.TransactionManagement;

import in.siva.util.NumberValidator;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String senderAccNo = request.getParameter("accno1");
			String receiverAccNo = request.getParameter("accno2");
			String price = request.getParameter("amount");
			float amount = 0;
			int fromAccNo = 0;
			int toAccNo = 0;
			amount = NumberValidator.parseFloat(price, "Invalid Price");
			fromAccNo = NumberValidator.parseInteger(senderAccNo, "Invalid Account Number");
			toAccNo = NumberValidator.parseInteger(receiverAccNo, "Invalid Account Number");
			TransactionManagement.transferAmount(fromAccNo, toAccNo, amount);
			double balance = TransactionManagement.withdrawAmount(fromAccNo, amount);

			if (balance > 0) {
				String message = "Transfer Fund Success";
				response.sendRedirect("summary.jsp?Balance=" + balance + "&infoMessage=" + message);
			} else {
				String message = "Transfer Fund Failure";
				response.sendRedirect("transferamount.jsp?errorMessage=" + message);
			}

		} catch (ValidException e) {

			e.printStackTrace();
			response.sendRedirect("transferamount.jsp?errorMessage=" + e.getMessage());
			
		}
	}

}
