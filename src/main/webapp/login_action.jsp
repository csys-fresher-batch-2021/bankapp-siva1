<%@page import="in.siva.app.UserManagement"%>
<html>
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Welcome To Bank APP</h3>
		
		<% 
	String username  = request.getParameter("name");
	String password = request.getParameter("password");
	boolean valid = UserManagement.loginValidation(username, password);
	if(valid){
		out.println("Successfully LoggedIn");
	}
	else{
	
	String message="Invalid Login Credentials";
	response.sendRedirect("login.jsp?errorMessage=" + message);
	}
%>
	</main>
</body>
</html>
