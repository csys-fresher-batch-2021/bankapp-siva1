<%@page import="in.siva.service.UserManagement"%>
<!DOCTYPE html>
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
		String message ="Successfully logged in";
		response.sendRedirect("login.jsp?infoMessage=" + message);
	}
	else{
	
	String message="Invalid Login Credentials";
	response.sendRedirect("login.jsp?errorMessage=" + message);
	}
%>
	</main>
</body>
</html>
