<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Withdrawal Page</h3>
<% Integer accountNumber = (Integer)session.getAttribute("ACCOUNTNUMBER"); %>
	
		<form id="withdrawForm" action="WithdrawServlet" method="get"
			onsubmit="withdraw()" novalidate="novalidate">
			<div class="mb-2 col-4">
				<label> Account Number</label> <input type="number" name="accno"
					id="accno" value=<%=accountNumber %> readonly>
			</div>
			<div class="mb-2 col-4">
				<label>Enter Amount To Withdraw</label> <input type="number"
					name="amount" id="amount" min="10" max="100000" placeholder="Enter Amount Here"
					class="form-control" required autofocus><br />
				<div class="invalid-feedback">Please Enter an amount</div>
			</div>
			<div class="mb-2 col-4">
				<button class="btn btn-info">Withdraw</button>
				<a href="display.jsp" class="btn btn-danger">Cancel</a>
			
			</div>

		</form>



	</main>

	<script>
		function withdraw() {
			event.preventDefault();
			// Step 1: Get Form Values
			let withdraw = document.querySelector("#withdrawForm");
			let amount = document.querySelector("#amount");

			console.log(amount);
			// 2. Check form is valid
			if (withdraw.checkValidity()) {
				withdraw.classList.remove("was-validated");
				withdraw.submit(); //if valid, then submit the form( which will call the LoginServlet)
			} else {
				withdraw.classList.add("was-validated");
				//field errors
				if (amount.value.length == 0) {
					amount.classList.add("is-invalid");
				} else {
					amount.classList.add("is-valid");
				}
			}
		}
	</script>
</body>
</html>



