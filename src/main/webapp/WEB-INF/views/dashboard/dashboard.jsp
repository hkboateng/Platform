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
<script src="<c:url value="/resources/js/visualization/d3.js" />" type="text/javascript"></script>
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
		
			<div class="col-sm-11 col-md-12 col-lg-12 center-block main">
			 	<h2>Welcome, ${employee.firstname}&nbsp;${employee.lastname}</h2>
			         
					  <hr>
			          <div class="row">
			          <c:if test="${not empty success }">
				          <div class="alert alert-success" role="alert">
				          	${success}
				          </div>
			          </c:if>
			          
			          <!-- Customer Search -->
			          </div>
			          <div class="row">
			          <div class="col-lg-12">
					      <c:if test="${not empty searchError }">
						    <div class="alert alert-danger" role="alert">
						        ${searchError }
						    </div>
					      </c:if>			          
			          </div>
				          <div class="col-xs-12 col-sm-7 col-md-7 col-lg-7">
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
				          	</c:if>						          	
				          </div>		
				          <div class="col-xs-12 col-sm-5 col-md-5 col-lg-5">
				          	<h3>Today's Transaction History</h3>	
				          	<table class="table">
				          		<tr>
				          			<th>Confirmation Number</th>
				          			<th>Amount</th>
				          			<th>Type Of Payment</th>
				          		</tr>				          		
			          			<c:if test="${not empty transactionListToday}">
		          					<c:forEach items="${transactionListToday }" var="history">
		          						<tr>
		          							<td>${history.getTransactionId()}</td>
		          							<td>${history.getAmountPaid()}</td>
		          							<td>${history.getPaymentType()}</td>
		          						</tr>
		          					</c:forEach>
		          					<tfoot>
		          						<tr>
		          							<td colspan="1"><span class="bold">Total Amount:</span></td>
		          							<td colspan="2">Total</td>
		          						</tr>
		          					</tfoot>
			          			</c:if>
			          			<c:if test="${ empty transactionListToday}">
			          				<td colspan="3" style="text-align:center;"> No Transaction History</td>
			          			</c:if>
			          			</table>
			          			
			          		<div>

					    		<sf:form action="/abankus/platform/searchDashboard" method="post">
									<label>Quick Search</label>
									<div class="spaceBelow_10">
										<label class="radio-inline">
										  <input type="radio" name="searchBill" id="customerId" value="customerIdDiv" checked> Customer Id:
										</label>
										<label class="radio-inline">
										  <input type="radio" name="searchBill" id="customerName" value="customerNameDiv"> First and Last Name
										</label>
										<label class="radio-inline">
										  <input type="radio" name="searchBill" id="customerOrder" value="customerOrderDiv"> Order Number:
										</label>
									</div>
										<div id="customerIdDiv" class="">
											<label>Customer Id:</label><input type="text" name="customerId" class="form-state"/>
											
										</div>
										<div id="customerNameDiv" class="hidden">
											<label>First Name:</label><input type="text" name="firstname" class="form-state">
											<label>Last Name:</label><input type="text" name="lastname" class="form-state">
										</div>
										<div class="hidden" id="customerOrderDiv">
											<label>Order Number:</label><input type="text" name="OrderNumber" class="form-state">
										</div>
										<input type="hidden" name="searchType" id="searchType" value="customerId">
										<button type="submit" class="btn btn-success btn-sm btn-block"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
								</sf:form>
			          		</div>
				          </div>					          	          
			          </div>	
			          <div class="row">
				          <div class="quick-stats">
				          	<div id="quick-stats-word" class="anw"> Quick Statistics</div>
				          </div>
								<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>
					          	<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>
					          	<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>
					          	<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>			          	
			          </div>
			</div>
		<script type="text/javascript">
		
		</script>
		</div>
	</div>
</body>
<script>
	$(document).ready(function(){
		loadEmployeeCustomers();
		
		$('#customerId').on('click',function(){
			$('#searchType').val("customerId");
			$("#customerIdDiv").removeClass('hidden');
			$('#customerNameDiv').addClass('hidden');
			$('#customerOrderDiv').addClass('hidden');
		});
		$('#customerName').on('click',function(){
			$('#searchType').val("customerName");
			$('#customerNameDiv').removeClass('hidden');
			$("#customerIdDiv").addClass('hidden');
			$('#customerOrderDiv').addClass('hidden');
		});
		$('#customerOrder').on('click',function(){
			$('#searchType').val("customerId");
			$('#customerOrderDiv').removeClass('hidden');
			$("#customerIdDiv").addClass('hidden');
			$('#customerNameDiv').addClass('hidden')
			
		});
		<%--- 
		$('input[type=radio]').on('click',function(){
			var id = $(this).val();
			$("#"+id).removeClass('hidden');
		});
		$('input[type=radio]').off('click',function(){
			var id = $(this).val();
			$("#"+id).addClass('hidden');
		});	---%>	
	});
	
	function loadEmployeeCustomers(){
		
	}
	
	
</script>
</html>
