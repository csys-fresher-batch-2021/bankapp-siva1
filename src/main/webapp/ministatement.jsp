<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<h3>Last Few Transactions</h3>
	<table class="table table-dark table-borderless">
		<caption>Your Last Transaction details</caption>
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Account Number</th>
				<th scope="col">Transaction Type</th>
				<th scope="col">Amount</th>
				<th scope="col">Comments</th>
				<th scope="col">TransactionDate</th>
				
			</tr>

		</thead>
		<tbody id="ministatement">
		</tbody>


	</table>
	<script>
	function getMiniStatement(){
	let url = "MiniStatementServlet?userId=<%=(Integer)session.getAttribute("ACCOUNTNUMBER")%>";
	fetch(url).then(res=>res.json()).then(res=>{
		
		let transaction = res;
		console.log(transaction);
		let statement = "";
		if(transaction==0){
			statement+="<tr><td colspan=14 class='text-center'>"+
						"No Transactions Yet"+"</td></tr>";
		}
    	
	    	for(let transfer of transaction){
			statement += "<tr><td>" +transfer.user.name+ "</td>"+
			"<td>" +transfer.user.accNo+ "</td>"+
			"<td>" + transfer.transactionType+"</td>"+
			"<td>"+ transfer.amount+"</td>"+
			"<td>"+transfer.comments+"</td>"+
			 "<td>"+transfer.transactionDateTime+"</td></tr>";
			
	    	}
			document.querySelector("#ministatement").innerHTML = statement;
			
	})
	}
getMiniStatement();
		
		</script>
</body>
</html>

