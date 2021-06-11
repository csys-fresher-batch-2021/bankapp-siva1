<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<h3>Account Details</h3>
	<table class="table table-dark table-borderless">
		<caption>Users bank account details</caption>
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Account Number</th>
				<th scope="col">Email Id</th>
				<th scope="col">MobileNumber</th>
				<th scope="col">Address</th>
				<th scope="col">Balance</th>
				<th scope="col">CreatedDate</th>
				<th scope="col">AccountStatus</th>
				<th scope="col">Status Button</th>

			</tr>

		</thead>
		<tbody id="userlist">
		</tbody>


	</table>
	<script>
	
	 function updateAccountDetails(accNo, status){
		let url ="AccountStatusServlet?accno=" + accNo+"&status="+status;
		fetch(url).then(res=>res.json()).then(res=>{
			
			let result = res;
			
			if(result ==true){
				alert("Success");
			}
			else{
				alert("Failed");
			}window.location.href="display.jsp";
		})
	} 
	function getAllDetails(){
	
	let url = "DisplayServlet?userId=<%=(Integer) session.getAttribute("ACCOUNTNUMBER")%>";
	fetch(url).then(res=>res.json()).then(res=>{
		
		let user = res;		
		let details = "";

			details += "<tr><td>" +user.name+ "</td>"+
			"<td>" + user.accNo + "</td>"+
			"<td>" + user.email+"</td>"+
			"<td>"+user.mobileNo+"</td>"+
			"<td>"+user.address+"</td>"+
			"<td>"+user.balance+"</td>"+
			"<td>"+user.createdDate+"</td>";
			if(user.active){
			 details+="<td class='badge badge-pill badge-success'>"+" Active"+"</td>";
			 }
			else{
				details+="<td class='badge badge-pill badge-danger'>"+"Inactive"+"</td>";
			}
			if(user.active){
			details+="<td><button type ='button'class='btn btn-danger' onclick=updateAccountDetails(" + user.accNo + ",false) >DeActivate</button></td></tr>";
			
			details+="<tr><td><a href='deposit.jsp' class='btn btn-warning'>Deposit</a></td>"
			+ "<td><a href='withdraw.jsp' class='btn btn-warning'>Withdraw</a></td>"
			+"<td><a href='transferamount.jsp' class='btn btn-warning'>Transfer Amount</a></td>"
			+"<td><a href='transactionsummary.jsp' class='btn btn-warning'>Statement</a></td>"
			+"<td><a href='ministatement.jsp' class='btn btn-warning'>MiniStatement</a></td></tr>";
			}
			else{
				details+="<td><button type ='button' class = 'btn btn-success' disabled onclick=updateAccountDetails(" + user.accNo + ",true)>Activate</button></td></tr>";
				
				
			}
		document.querySelector("#userlist").innerHTML = details;
	})

	}
getAllDetails();
updateAccountDetails();	

	</script>
</body>
</html>
