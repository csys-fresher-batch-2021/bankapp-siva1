<%@page import="in.siva.model.User"%>
<%@page import="java.util.List"%>
<%@page import="in.siva.service.UserManagement"%>
<html>
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<form action="LoginSeverlet" method="get">
		<h3>UserDetails</h3>
		<table class="table	table-bordered">
		<caption>Users bank account details</caption>
		<thead><tr>
		<!-- <th scope="col">S.no</th> -->
		<th scope="col">Type</th>
		<th scope="col">Details</th>
		
		<%
		String userName = (String)session.getAttribute("LOGGED_IN_USER");
		List<User> userList = UserManagement.getAllUser(userName);
		//User user = userList.get(0);
		 
		for(User user: userList){
		if (userName.equals(user.getName())){  
		%>
		
		<tbody>
		<tr><td>Name</td>
		<td><%=user.getName() %>
		</tr>
		<tr><td>AccountNumber</td>
		<td><%=user.getAccNo() %>
		
		</tr>
		<tr><td>Email</td>
		<td><%=user.getEmail() %></td>
		</tr>
		<tr>
		<td>Address</td>
		<td><%=user.getAddress() %>
		</td></tr>
		<tr><td>Mobile</td>
		<td><%=user.getMobileNo() %></td>
		</tr>
		<tr><td>Balance</td>
		<td><%=user.getBalance() %></td>
		</tr>
		<!-- <tr><td>Balance</td><td>50000</td></tr>
	 -->
		
	 	  <% }} %>  
		</tbody>
		
		
		
		
		
		</table>
		<a href="#" class="btn btn-primary">Deposit</a>
		<a href="#" class="btn btn-primary">Withdraw</a> 
		<a href="#" class="btn btn-primary">Transfer</a>    
		</form>
		
		
	</main>
</body>
</html>
