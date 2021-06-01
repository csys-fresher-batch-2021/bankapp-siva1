
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Deposit Page</h3>
		<%
		Integer accountNumber = (Integer) session.getAttribute("ACCOUNTNUMBER");
		%>
		<form id="depositForm" action="DepositServlet" method="get"
			novalidate="novalidate" onsubmit="deposit()">
			<div class="mb-2 col-4">
				<label> Account Number</label> <input type="number" name="accno"
					id="accno" value=<%=accountNumber%> readonly>

			</div>
			<div class="mb-2 col-4">
				<label>Enter Amount To Deposit</label> <input type="number"
					name="amount" id="amount" min="100" max="100000" class="form-control"
					placeholder="Enter Amount Here" required autofocus><br />
				<div class="invalid-feedback">Please Enter an amount</div>

			</div>
			<div class="mb-3 col-4">
				<button class="btn btn-primary">Deposit</button>
			</div>
			<br />

		</form>
	</main>
	<script>
		function deposit() {
			event.preventDefault();
			// Step 1: Get Form Values
			let deposit = document.querySelector("#depositForm");
			let amount = document.querySelector("#amount");

			console.log(amount);
			// 2. Check form is valid
			if (deposit.checkValidity()) {
				deposit.classList.remove("was-validated");
				deposit.submit(); //if valid, then submit the form( which will call the LoginServlet)
			} else {
				deposit.classList.add("was-validated");
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