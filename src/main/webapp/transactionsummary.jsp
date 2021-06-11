<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<title>Bank APP</title>
</head>
<body onload="filter()">
	<jsp:include page="header.jsp"></jsp:include>

	<h3>Welcome To Bank APP</h3>
			<select id="type" name="type" onchange="filter()">
			<option  value="" selected>All</option>
			<option value="CREDITED">Credited</option>
			<option value="DEBITED">Debited</option>
			<option value="TRANSFER">Transfer</option>
			</select>
	
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
		<tbody id="transactionlist">
		</tbody>


	</table>
	<script>
	function filter(){
		event.preventDefault();
		let url = "TransactionSummaryServlet?userId=<%=(Integer)session.getAttribute("ACCOUNTNUMBER")%>";
		fetch(url).then(res=>res.json()).then(res=>{
			let transactionType = document.querySelector("#type").value;
			console.log(transactionType);
			let transaction = res;
			if(transactionType!=null){
				transaction = res.filter(res=>res.transactionType.toLowerCase().includes(transactionType.toLowerCase()));
			}
			getTransactionDetails(transaction);
		})
		
		
	}
	
	function getTransactionDetails(statement){	
 		let transfer = statement;
		console.log(transfer);
		let summary = "";
			if(transfer==0){
				summary+="<tr><td colspan=14 class='text-center'>"+
							"No Transactions Yet"+"</td></tr>";
			}
	    	for(let trans of transfer){
			summary+= "<tr><td>" +trans.user.name+ "</td>"+
			"<td>" +trans.user.accNo+ "</td>"+
			"<td>" + trans.transactionType+"</td>"+
			"<td>"+ trans.amount+"</td>"+
			"<td>"+trans.comments+"</td>"+
			 "<td>"+trans.transactionDateTime+"</td></tr>";
			
	    	}
			document.querySelector("#transactionlist").innerHTML = summary;
	}
		
		</script>
</body>
</html>

