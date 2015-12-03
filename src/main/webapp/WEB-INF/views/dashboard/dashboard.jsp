<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/fonts-awesome/font-awesome.css" />" rel="stylesheet"/>

<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>


<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>

</head>
<body>
<!-- Page Header -->
	<%-- Include page header --%>
	<jsp:include page="../header.jsp"/>
	<!-- Page Header ends -->
	<!-- Body Begins-->
	<div id="container" class="container">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12 center-block main">
			 	<h2>Welcome, ${employee.firstname}&nbsp;${employee.lastname}</h2>
					  <hr>
			          <c:if test="${not empty success }">
				          <div class="alert alert-success" role="alert">
				          	${success}
				          </div>
			          </c:if>
			          <div class="col-lg-12">
					      <c:if test="${not empty searchError }">
						    <div class="alert alert-danger" role="alert">
						        ${searchError }
						    </div>
					      </c:if>			          
			          </div>
			           <div class="clearfix"></div>
				          <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
				          	<%--
				          	<h3>Customer Assigned</h3>
				          	<c:if test="${not empty employeeCustomerList }">
				          	<table class="table">
				          		<tr>
				          			<th>Full Name</th>
				          			<th>Account Number</th>
				          			<th>Account Status</th>
				          		</tr>
				          	
				          		<c:forEach items="${employeeCustomerList }" var="customerList" >
				          		<tr>
				          			<td>${customerList.customer.getCustomerName() }</td>
				          			<td><a href="#">${customerList.getAccountNumber() }</a></td>
				          			<td>${customerList.getStatus() }</td>
				          		</tr>
				          		</c:forEach>
				          	</table>
				          	</c:if>
				          	<c:if test="${empty employeeCustomerList}">				          	
								No Customer
								<div class="center-block">
									<a class="btn btn-success" href="<c:url value="/customers/create"/>"> Add New Customer</a>
								</div>	          		
				          	</c:if>		 --%>				          	

				          <h3 class="underline-div"> Transaction History</h3>				          
							  <div class="btn-group btn-group-justified" role="group" >
								  <div class="btn-group" role="group">
								    <a aria-controls="todayDivContainer" role="tab" data-toggle="tab" id="todayDiv" href="#todayDivContainer"  class="btn btn-success">Today</a>
								  </div>
								  <div class="btn-group" role="group">
								    <a aria-controls="monthlyDivContainer" role="tab" data-toggle="tab" href="#monthlyDivContainer" class="btn btn-default">Monthly</a>
								  </div>
								  <div class="btn-group" role="group">
								    <a aria-controls="monthlyDivContainer" role="tab" data-toggle="tab" href="#YTDDivContainer"  class="btn btn-default">Year-To-Date</a>
								  </div>
								  <div class="btn-group" role="group">
								    <a aria-controls="monthlyDivContainer" role="tab" data-toggle="tab" href="#FilterDivContainer"  class="btn btn-default">
								    	Filter
								    </a>						    
								  </div>							  
							   </div>	
								  <div class="tab-content">
								  <div class="spaceBelow_10"></div>
								  	<div  role="tabpanel" class="tab-pane active" id="todayDivContainer">								  		
									        <table id="transactionTodayTbl" class="table table-striped transactionTable">
									        	<thead>
										          	<tr>
											          	<th>Date</th>
											          	<th>Amount</th>
											          	<th>Type Of Payment</th>
											          	<th>Action</th>
										          	</tr>		
									          	</thead>	
									          	<tbody id="transactionToday">
																          	
									          	</tbody>  
						          				<tfoot>
						          					<tr>
						          						<td colspan="1"><span class="bold">Total Amount:</span></td>
						          						<td colspan="2">Total</td>
						          						<td></td>
						          					</tr>
						          				</tfoot>
						          			</table>								  		
								  		</div>

								  	<div  role="tabpanel" class="tab-pane" id="monthlyDivContainer">
									        <table id="transactionMonthTbl" class="table table-striped transactionTable">
									        	<thead>
										          	<tr>
											          	<th>Date</th>
											          	<th>Amount</th>
											          	<th>Type Of Payment</th>
											          	<th>Action</th>
										          	</tr>		
									          	</thead>	
									          	<tbody id="transactionMonth">
																          	
									          	</tbody>  
						          			</table>								  		
								  	</div>		
								  	<div  role="tabpanel" class="tab-pane" id="YTDDivContainer">
									        <table id="transactionYTDTbl" class="table transactionTable">
									        	<thead>
										          	<tr>
											          	<th>Date</th>
											          	<th>Amount</th>
											          	<th>Type Of Payment</th>
											          	<th>Action</th>
										          	</tr>		
									          	</thead>	
									          	<tbody id="transactionYTD">
																          	
									          	</tbody>  
						          				<tfoot>
						          					<tr>
						          						<td colspan="1"><span class="bold">Total Amount:</span></td>
						          						<td colspan="2">Total</td>
						          						<td></td>
						          					</tr>
						          				</tfoot>
						          			</table>								  		
								  	</div>
								  	<div  role="tabpanel" class="tab-pane" id="FilterDivContainer">
								  		<h4>Filter Transaction</h4>
								  	</div>								  						  	
								  </div>				          
				         	</div>		
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 spaceBelow_20">
								<jsp:include page="../sidebar.jsp"/>
			          	 	</div>			          	
			</div>
		</div>
	</div>
</body>
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function(){
		loadEmployeeCustomers();
		loadYearPayments()
		loadTodayPayment();
		loadMonthAndYearPayments();
		$('table.transactionTable').DataTable();
		
	});
	
	function loadEmployeeCustomers(){
		
	}
	function loadYearPayments(){
		$.ajax({
			url: 'loadYearTransactionHistory',
			method: 'get',
			dataType: 'json',
			success: function(result){
				console.log(result);
			},
			error : function(err){
				console.log(err.responseText);
			}
		});				
	}
	function loadMonthAndYearPayments(){
		var thisMonth= new Date().getMonth()+1;
		var year = new Date().getFullYear();
		$.ajax({
			url: 'http://localhost:8080/paymenthub/rs/paymentservice/findPaymentTransactionByMonthAndYear',
			method: 'get',
			data: {
				month: thisMonth,
				year:year
			},
			dataType: 'json',
			success: function(result){
				if(result.length > 0){
					$('table.transactionTable').DataTable();
					$.each(result,function(key,value){
						table +="<tr>";
						table +="<td>"+value.paymentDate+"</td>";
						table +="<td>"+value.amountPaid+"</td>";
						table +="<td>"+toTitleCase(value.paymentType)+"</td>";
						table +="<td><a class='btn btn-primary' href='#' role='button' onclick=''>Action</a>";
						table +="</tr>";
					});
					$('#transactionMonth').html(table);					
				}else{
					$('#transactionMonth').html("No Data");		
				}

			},
			error : function(err){
				$('#transactionMonth').html("No Transaction");
			}
		});		
	}
	function loadTodayPayment(){
		var now = new Date();
		var day = now.getDate();
		if(day <10){
			day = "0"+day;
		}
		var month = now.getMonth()+1;
		if(month < 10){
			month = "0"+month;
		}
		var year = now.getFullYear();
		
		var date = month+"/"+day+"/"+year;
		var fromDate = new Date().toLocaleDateString("en-US");
		
		var toDate = new Date().toLocaleDateString("en-US");
		$.ajax({
			url: "loadPayments",
			method: 'get',
			data: {
				date: date
			},
			dataType: 'json',
			success: function(result){
				console.log(result);
				var table = "";
				$.each(result,function(key,value){
					console.log(value);
					table +="<tr>";
					table +="<td>"+value.paymentDate+"</td>";
					table +="<td>"+value.amountPaid+"</td>";
					table +="<td>"+toTitleCase(value.paymentType)+"</td>";
					table +="<td><a class='btn btn-primary' href='#' role='button' onclick=''>Action</a>";
					table +="</tr>";
				});
				$('#transactionToday').html(table);
			
			},
			error : function(err){
				console.log(err.responseText);
			}
		});
	}
	
</script>
</html>
