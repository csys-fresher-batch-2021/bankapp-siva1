<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Transaction Summary</h3>
		<table class="table table-dark table-borderless">
			<caption>Transaction Summary</caption>
			<thead>
				<tr>

					<th scope="col">Summary</th>
					<th scope="col">Details</th>


					<%
					String amount = request.getParameter("Balance");
					String result = request.getParameter("infoMessage");
					LocalDate date = LocalDate.now();
					LocalTime time = LocalTime.now();
					 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					 String text = date.format(formatters);
					%>
				
			<tbody>
				<tr>
					<td>Transaction Status</td>
					<td><%=result%></td>
				</tr>
				<tr>
					<td>Current Balance</td>
					<td><%=amount%></td>
				</tr>
				<tr>
					<td>Transaction Date</td>
					<td><%=text%></td>
				</tr>
				<tr>
					<td>Transaction Time</td>
					<td><%=time%></td>
				</tr>

			</tbody>


		</table>
	</main>
</body>
</html>