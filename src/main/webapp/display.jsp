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
				<th scope="col">CreatedDate</th>
				<th scope="col">AccountStatus</th>
				<th scope="col">InactivateButton</th>
				
				</tr>
				
		</thead>
		<tbody id="userlist">
		</tbody>


	</table>
<script>
	
	 function updateAccountDetails(accNo, status){
		let url ="AccountStatusServlet?accno=" + accNo+"&status="+status;
		fetch(url).then(res=>res.json()).then(res=>{
			
			let result = res.data;
			console.log(res);
			if(result == false){
				alert("Failed");
			}
			else{
				alert("Success");
			}
		})
	} 
	function getAllDetails(){
	
	let url = "DisplayServlet?userId=<%=(String) session.getAttribute("LOGGED_IN_USER")%>";
	fetch(url).then(res=>res.json()).then(res=>{
		
		let users = res;		
		let details = "";
		for(let user of users){
			
			details += "<tr><td>" +user.name+ "</td>"+
			"<td>" + user.accNo + "</td>"+
			"<td>" + user.email+"</td>"+
			"<td>"+user.mobileNo+"</td>"+
			"<td>"+user.address+"</td>"+
			"<td>"+user.balance+"</td>"+
			"<td>"+user.created_date+"</td>"+
			"<td>"+user.active+"</td>";
			if(user.active){
			details+="<td><button type ='button'class='btn btn-danger' onclick=updateAccountDetails(" + user.accNo + ",false) >DeActivate</button></td></tr>";
			details+="<tr><a href='deposit.jsp' class='btn btn-primary'>Deposit</a></tr>"+ "<tr><a href='withdraw.jsp' class='btn btn-primary'>Withdraw</a></tr>"+"<tr><a href='transferamount.jsp' class='btn btn-primary'>Transfer Amount</a></tr>";
				
			 
			
			}
			else{
				details+="<td><button type ='button' class = 'btn btn-success'onclick=updateAccountDetails(" + user.accNo + ",true)>Activate</button></td></tr>";
			}

 
		}
		
		document.querySelector("#userlist").innerHTML = details;
	})
	
	}
getAllDetails();
updateAccountDetails();	
	</script>
</body>
</html>
