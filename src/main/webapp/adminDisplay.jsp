<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<title>Bank APP</title>
</head>
<body onload="filterAccNo()">
	<jsp:include page="header.jsp"></jsp:include>

	<h3>Welcome To Bank APP</h3>
	<div class="mb-10 col-10">
		<label>Search</label> <input type="number" class="brandFilter"
			id="accno" oninput="filterAccNo()" min="1"
			placeholder="Account Number">
	</div>
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
				<th scope="col">StatusButton</th>
			</tr>

		</thead>
		<tbody id="details">
		</tbody>


	</table>
	<script>
	
	 function filterAccNo(){	
		 event.preventDefault();
		 let url = "AdminDisplayServlet";
			fetch(url).then(res=>res.json()).then(res=>{
				let accno = document.querySelector("#accno").value;
				let users = res;
				if(accno != null){
		 users = res.filter(res =>JSON.stringify(res.accNo).includes(accno));
				}	 	
		 getDetails(users);	
			} )
	 }
	function updateStatus(accNo, status){
		let url ="AccountStatusServlet?accno=" + accNo+"&status="+status;
		fetch(url).then(res=>res.json()).then(res=>{
			
			let result = res;
			
			if(result ==true){
				alert("Success");
			}
			else{
				alert("Failed");
			}window.location.href="adminDisplay.jsp";
		})
	} 
	function getDetails(user){
	
		let users = user;
		console.log(users);
		let details = "";
		if(users.length!=0){
			for(let user of users){
			details += "<tr><td>" +user.name+ "</td>"+
			"<td>" + user.accNo + "</td>"+
			"<td>" + user.email+"</td>"+
			"<td>"+user.mobileNo+"</td>"+
			"<td>"+user.address+"</td>"+
			"<td>"+user.balance+"</td>"+
			"<td>"+user.createdDate+"</td>";
			if(user.active){
				details+="<td class='badge badge-pill badge-success'>"+"Active"+"</td>";
			}
			else{
				details+="<td class='badge badge-pill badge-danger'>"+"Inactive"+"</td>";
			}
			if(user.active){
			details+="<td><button type ='button'class='btn btn-danger' onclick=updateStatus(" + user.accNo + ",false) >DeActivate</button></td></tr>";
			
			}
			else{
				details+="<td><button type ='button' class = 'btn btn-success'onclick=updateStatus(" + user.accNo + ",true)>Activate</button></td></tr>";
			}

			}}else{
				details+="<tr><td colspan=14 class='text-center'>" + "No Records Found" + "</td></tr>";
			}
		
		
		document.querySelector("#details").innerHTML = details;
	}

updateStatus();	
	</script>
</body>
</html>

