<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<%
		String errorMessage = request.getParameter("errorMessage");
		if(errorMessage != null){
			out.println("<font color='red'>" + errorMessage + "</font>");
		}
		String infoMessage = request.getParameter("infoMessage");
		if(infoMessage != null){
			out.println("<font color='green'>" + infoMessage + "</font>");
		}
		%>
	
		
		
	</main>
</body>
</html>
