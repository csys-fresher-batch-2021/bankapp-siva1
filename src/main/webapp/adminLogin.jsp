<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Login</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Admin Login</h3>
		
		<form id="loginForm" action="AdminLoginServlet" onsubmit="adminLogin()"novalidate="novalidate" method="post">
			<div class="mb-2 col-4">
			<label>Admin</label>
			<input type="text" name="userName" id="userName"
				autocomplete="off" required placeholder="Enter User Name" class="form-control" autofocus><br />
				<div class="invalid-feedback">Please Enter Name.</div>
			</div>
			<div class="mb-2 col-4">
			<label>Admin Password</label>
			 <input type="password" name="password" id="password"
				autocomplete="off" required class="form-control" placeholder="Enter Password"><br />
			<div class="invalid-feedback">Please Enter Password.</div>
			</div>
			<div class="mb-2 col-4">
			<button class="btn btn-secondary" type="submit">Submit</button>
			<button class="btn btn-danger" type="reset">Reset</button>
			</div>
			<p>Note:
			Admin=admin
			password=Admin@123</p>
		</form>
	</main>
	<script>
		function adminLogin() {
			event.preventDefault();
			// Step 1: Get Form Values
			let loginForm = document.querySelector("#loginForm");
			let username = document.querySelector("#userName");
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