
<!DOCTYPE html>
<html>
<head>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Bank APP</h3>

		<form action="DepositServlet" method="get">


			<label>Enter Amount To Deposit</label> <input type="number"
				name="amount" id="amount" min="100" placeholder="Enter Amount Here"
				required autofocus>
			<button>Enter</button>
			<br />
			<%
			String amount = request.getParameter("Balance");
			if (amount != null) {
				out.println("!!Deposit Success!!");
				out.println("Your current balance = Rs." + amount);
			}
			%>
		</form>




	</main>
</body>
</html>