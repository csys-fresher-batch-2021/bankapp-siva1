<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<form action="TransferServlet" id="transferForm"
			novalidate="novalidate" method="get" onsubmit="transfer()">
			<h3>Transfer Funds</h3>
			<div class="mb-2 col-4">
				<label>Sender AccountNumber</label> <input type="number" name="accno1"
					id="email1" placeholder="Enter AccountNumber" required autofocus><br />
				<div class="invalid-feedback">Please Enter your Account Number</div>

			</div>
			<div class="mb-2 col-4">
				<label>Receiver Email</label> <input type="number" name="accno2"
					id="email2" placeholder="Enter AccountNumber" required><br />
				<div class="invalid-feedback">Please Enter Receiver Email</div>

			</div>
			<div class="mb-2 col-4">
				<label>Enter Amount</label> <input type="number" name="amount"
					id="amount" required placeholder="Enter amount here"><br />
				<div class="invalid-feedback">Please Enter Amount</div>

			</div>
			<div class="mb-2 col-4">
				<button class="btn btn-primary">Transfer Fund</button>
			</div>
		</form>
	</main>
	<script>
		function transfer() {
			event.preventDefault();
			// Step 1: Get Form Values
			let transfer = document.querySelector("#transferForm");
			let amount = document.querySelector("#amount");

			console.log(amount);
			// 2. Check form is valid
			if (transfer.checkValidity()) {
				transfer.classList.remove("was-validated");
				transfer.submit(); //if valid, then submit the form( which will call the LoginServlet)
			} else {
				transfer.classList.add("was-validated");
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
