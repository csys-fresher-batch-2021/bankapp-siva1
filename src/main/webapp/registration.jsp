
<!DOCTYPE html> 
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
	
		<h3>Registration Page</h3>

		<form action ="RegisterServlet" method="get">
			<label>Name</label>
		 	<input type="text" id="username" name="username" placeholder="Enter your name" required autofocus><br />

		 	<label>Mobile</label>
			<input type="number" id="number" name="number" placeholder="Enter Mobile Number" required min="1000000000"max="9999999999"><br />
			<label>Address</label> 
			<input type="text" id="address" name="address" placeholder="Enter your Address" required><br />
			<label>Email Id</label>
			<input type="email" id="email" name="email" placeholder="Enter Mail Id" required><br /> 
			<label>Password</label>
			<input type="password" id="userpassword" name="userpassword" placeholder="Enter Password" required><br />
			<label>Gender  </label>
			<input type="radio" name="gender" id="gender" value="M">Male
			<input type="radio" name="gender" id="gender" value="F">Female <br />

			<p>Deposit an Initial Amount at least 1000 to Open Your new SB Account</p>
			<label>Initial Amount</label>
			<input type="number" name="amount" id="amount" min="1000" required placeholder="Enter Initial AMount"><br />

			<button class="btn btn-primary">Register</button>
			<button type ="reset" class="btn btn-info">Reset</button>



		</form>
	</main>
</body>
</html>

