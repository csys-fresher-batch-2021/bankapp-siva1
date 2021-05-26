<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<title>Bank APP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<h3>Welcome To Bank APP</h3>
	<table class="table	table-bordered">
		<caption>Users bank account details</caption>
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Account Number</th>
				<th scope="col">Email Id</th>
				<th scope="col">MobileNumber</th>
				<th scope="col">Address</th>
				<th scope="col">Balance</th>
			</tr>
		</thead>
		<tbody id="userlist">
		</tbody>


	</table>

	<a href="deposit.jsp" class="btn btn-primary">Deposit</a>
	<a href="withdraw.jsp" class="btn btn-primary">Withdraw</a>
	<script>
	function getAllDetails(){
	
	let url = "DisplayServlet?userId=<%=(String) session.getAttribute("LOGGED_IN_USER")%>";
	fetch(url).then(res=>res.json()).then(res=>{
		
		let users = res;		
		let details = "";
		for(let user of users){
			
			details += "<tr><td>" +user.name+ "</td><td>" + user.accNo + "</td><td>" + user.email+"</td><td>"+user.mobileNo+"</td><td>"+user.address+"</td><td>"+user.balance+"</td></tr>";
			
		}
		
		document.querySelector("#userlist").innerHTML = details;
	})
	
	}
getAllDetails();
	
	</script>
</body>
</html>
