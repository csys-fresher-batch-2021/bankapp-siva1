<html>
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
	
		<h3>Login Page</h3>
		
		<form action=login_action.jsp>
		<label>UserName</label>
		<input type="text" name="name" id="name" placeholder="Enter Your Registered Name" required autofocus><br/>
		<label>Password</label>
		<input type="password" name="password" id="password" placeholder="Enter Your Password" required><br/>
		<button class="btn btn-primary">Submit</button>
		</form>
		
		
		
	</main>
</body>
</html>
