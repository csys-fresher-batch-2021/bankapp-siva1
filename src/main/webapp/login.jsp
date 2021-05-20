<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">

		<h3>Login Page</h3>

		<form id="loginForm" class="" action="LoginServlet" onsubmit="login()"
			novalidate="novalidate" method="post">
			<div class="mb-2 col-4">
			<label>Email Id</label>
			
			<input type="email" name="email" id="email"placeholder="Enter Your Registered EmailId" class="form-control" required autofocus><br />
			<div class="invalid-feedback">Please choose an Email.</div>
			</div>
			<div class="mb-2 col-4">
			<label>Password</label>
			 
			<input type="password" name="password"id="password" placeholder="Enter Your Password" class="form-control"required><br />
			<div class="invalid-feedback">Please choose a password.</div>
			</div>
			<div class="mb-2 col-4">
			<button class="btn btn-success">Submit</button> 
			</div>
		</form>



	</main>
	
	
	<script>
		function login() {
			event.preventDefault();
			// Step 1: Get Form Values
			let loginForm = document.querySelector("#loginForm");
			let username = document.querySelector("#email");
			let password = document.querySelector("#password");
			console.log(username + "-" + password);
			// 2. Check form is valid
			if (loginForm.checkValidity()) {
				loginForm.classList.remove("was-validated");
				loginForm.submit(); //if valid, then submit the form( which will call the LoginServlet)
			} else {
				loginForm.classList.add("was-validated"); 
				//field errors
				if (username.value.length == 0) {
					username.classList.add("is-invalid");
				}
				else{
					username.classList.add("is-valid");
				}
			}
		}
	</script>
</body>
</html>
