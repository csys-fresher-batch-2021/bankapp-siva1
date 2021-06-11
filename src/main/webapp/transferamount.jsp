<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<form action="TransferServlet" id="transferForm"
			novalidate="novalidate" method="get" onsubmit="transfer()">
			<h3>Transfer Funds</h3>
			<% Integer accountNumber = (Integer)session.getAttribute("ACCOUNTNUMBER"); %>
	
			<div class="mb-2 col-4">
				<label>Sender AccountNumber</label> <input type="number" name="accno1"
					id="accno1" value=<%=accountNumber %> readonly ><br />
			</div>
			<div class="mb-2 col-4">
				<label>Receiver AccountNumber</label> <input type="number" name="accno2"
					id="email2" placeholder="Enter AccountNumber" min="1" required autofocus><br />
				<div class="invalid-feedback">Please Enter Receiver Account Number</div>

			</div>
			<div class="mb-2 col-4">
				<label>Enter Amount</label> <input type="number" name="amount"
					id="amount" min = "10" max="100000"required placeholder="Enter amount here"><br />
				<div class="invalid-feedback">Please Enter Amount</div>

			</div>
			<div class="mb-2 col-4">
				<button class="btn btn-info">Transfer</button>
				<a href="display.jsp" class="btn btn-danger">Cancel</a>
			
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
