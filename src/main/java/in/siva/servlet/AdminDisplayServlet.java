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

import in.siva.model.User;
import in.siva.service.AdminService;
import in.siva.util.LocalDateTimeAdapter;

/**
 * Servlet implementation class AdminDisplayServlet
 */
@WebServlet("/AdminDisplayServlet")
public class AdminDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> list = AdminService.getAllUser();
		Gson gson = new GsonBuilder().setPrettyPrinting()
		        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
		        .create();
		// Step 2: Convert to Json string
		
		String json = gson.toJson(list);
		
		// Step 3: Write the json in response and flush it
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

	

}
