
<!DOCTYPE html> 
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
	
		<h3>Registration Page</h3>

		<form id="registerForm" action ="RegisterServlet" method="get" onsubmit = "register()" novalidate = "novalidate">
			<div class="mb-2 col-4">
			<label>Name</label>
		 	<input type="text" id="username" name="username" placeholder="Enter your name" class="form-control" required autofocus><br />
		 	<div class="invalid-feedback">Please Enter Name.</div>
		 	</div>
	<div class="mb-2 col-4">
		 	<label>Mobile</label>
			<input type="number" id="number" name="number" placeholder="Enter Mobile Number" class="form-control" required min="1000000000"max="9999999999"><br />
			<div class="invalid-feedback">Please Enter a Valid MobileNumber.</div>
			</div>
			<div class="mb-2 col-4">
			<label>Address</label> 
			<input type="text" id="address" name="address" placeholder="Enter your Address" class="form-control" required><br />
			<div class="invalid-feedback">Please give an address</div>
			</div>
			<div class="mb-2 col-4">
			<label>Email Id</label>
			<input type="email" id="email" name="email" placeholder="Enter Mail Id" class="form-control" required><br /> 
			<div class="invalid-feedback">Please Enter a Valid Email.</div>
			</div>
			<div class="mb-2 col-4">
			<label>Password</label>
			<input type="password" id="userpassword" name="userpassword" placeholder="Enter Password" class="form-control" required><br />
			<div class="invalid-feedback">Please Enter a Password.</div>
			</div>
			<label>Gender  </label>
			<input type="radio" name="gender" id="gender" value="M">Male
			<input type="radio" name="gender" id="gender" value="F">Female <br />

			<p><strong>Deposit an Initial Amount at least "Rs.1000" to Open Your new SB Account</strong></p>
			<div class="mb-2 col-4">
			<label>Initial Amount</label>
			<input type="number" name="amount" id="amount" min="1000" required class="form-control" placeholder="Enter Initial Amount"><br />
			<div class="invalid-feedback">Please Enter an Amount.</div>
			</div>
			<div class="mb-3 col-4">	
			<button class="btn btn-success">Register</button>
			
			<button type ="reset" class="btn btn-info">Reset</button>
			</div>


		</form>
		
	</main>
	
	
	<script>
		function register() {
			event.preventDefault();
			// Step 1: Get Form Values
			let regiterForm = document.querySelector("#registerForm");
			let username = document.querySelector("#uesrname");
			let password = document.querySelector("#password");
			let emailId = document.querySelector("#email");
			let mobileNo = document.querySelector("#number");
			console.log(username + "-" + password);
			// 2. Check form is valid
			if (registerForm.checkValidity()) {
				regiterForm.classList.remove("was-validated");
				regiterForm.submit(); //if valid, then submit the form( which will call the LoginServlet)
			} else {
				registerForm.classList.add("was-validated"); 
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

