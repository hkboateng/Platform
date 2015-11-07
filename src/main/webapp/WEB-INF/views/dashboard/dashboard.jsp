<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
			          </div>
				          <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
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
				          <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
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
			          				<th> No Transaction History</th>
			          			</c:if>
			          			</table>
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
	});
	
	function loadEmployeeCustomers(){
		
	}
</script>
</html>
