package in.siva.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.siva.model.Transaction;

import in.siva.service.TransactionService;

import in.siva.util.LocalDateTimeAdapter;
import in.siva.util.NumberValidator;

/**
 * Servlet implementation class TransactionSummaryServlet
 */
@WebServlet("/TransactionSummaryServlet")
public class TransactionSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String accNo = request.getParameter("userId");
		int accno = 0;
		accno = NumberValidator.parseInteger(accNo, "Invalid account number");
		List<Transaction> transaction= TransactionService.transactionSummary(accno);
		Gson gson = new GsonBuilder().setPrettyPrinting()
		        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
		        .create();
		// Step 2: Convert to Json string
		
		String json = gson.toJson(transaction);
		
		// Step 3: Write the json in response and flush it
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

	

}
