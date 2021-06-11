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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
			{

		try {
			String price = request.getParameter("amount");
			Transaction transaction = new Transaction();
			HttpSession session = request.getSession();
			int accountNo = (Integer) session.getAttribute("ACCOUNTNUMBER");
			float amount = 0;
			amount = NumberValidator.parseFloat(price, "Invalid amount");
			LocalDateTime depositTime = LocalDateTime.now();
			transaction.setAmount(amount);
			transaction.setTransactionDate(depositTime);
			transaction.setTransactiontype("CREDITED");
			transaction.setComments("TRANSACTION TRHOUGH UPI");
			double balance = TransactionService.depositAmount(accountNo, transaction);

			if (balance > 0) {

				String message = "Deposit Success ";
				response.sendRedirect("summary.jsp?Balance=" + balance + "&infoMessage=" + message);
			} else {
				String message = "Deposit failed";
				response.sendRedirect("deposit.jsp?errorMessage=" + message);
			}
		} catch (ValidException e) {
			e.printStackTrace();
			response.sendRedirect("deposit.jsp?errorMessage=" + e.getMessage());
			
		}

	}
}