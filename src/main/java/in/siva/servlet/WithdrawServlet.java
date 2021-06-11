package in.siva.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.siva.exception.ValidException;
import in.siva.model.Transaction;
import in.siva.service.TransactionService;
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
			Transaction transaction = new Transaction();
			int accountNo = (Integer)session.getAttribute("ACCOUNTNUMBER");
			String price = request.getParameter("amount");
			float amount = 0;
			amount = NumberValidator.parseFloat(price, "Invalid amount");
			LocalDateTime withdrawTime = LocalDateTime.now();
			transaction.setAmount(amount);
			transaction.setTransactionDate(withdrawTime);
			transaction.setTransactiontype("DEBITED");
			transaction.setComments("TRANSACTION TRHOUGH ATM");
			double balance = TransactionService.withdrawAmount(accountNo,transaction);
					
			if (balance > 0) {

				String message = "Withdraw Success ";
				request.setAttribute(price, message);
				response.sendRedirect("summary.jsp?Balance=" + balance + "&infoMessage=" + message);
				 		
			} else {
				String message = "Withdrawal failed";
				response.sendRedirect("withdraw.jsp?errorMessage=" + message);
			}
		} catch (ValidException e) {
			e.printStackTrace();
			response.sendRedirect("withdraw.jsp?errorMessage=" +e.getMessage());
		}
	}

}
