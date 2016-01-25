<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Customer Search</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"	rel="stylesheet" />
<link href="<c:url value="/resources/css/platform.css" />"	rel="stylesheet" />
<link href="<c:url value="/resources/css/datepicker.css" />"	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/customerValidation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"	type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />"	type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />"	type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"	type="text/javascript"></script>
<script src="<c:url value="/resources/js/moments.js" />"	type="text/javascript"></script>
</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div id="container" class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="spaceBelow_20"></div>
				<div id="searchContainer">
					<div class="spaceBelow_10 container">						
							<div class="spaceBelow_10">
								<label class="radio-inline">
									<input type="radio" name="searchBill" id="customer" value="customerIdDiv" checked>Search for Customer:
								</label>
								<label class="radio-inline">
									<input type="radio" name="searchBill" id="employee" value="customerOrderDiv">Payment(s) received by Employee:
								</label>
							</div>
							<div class="col-lg-12">
							<div id="customerIdDiv" class="vertical-divider">
								<div class="col-lg-5 spaceBelow_20">
									<sf:form name="searchForm" id="searchCustomerNumberForm" action="/abankus/platform/customerProfileSearch" method="post">
										<label>Customer Number or Email Address:</label>
										<input type="text" name="customerIdentity" id="customerIdentity" class="form-state width-100" placeholder="Customer Id, Number  or Email address">				
										<div>
											<button type="submit" class="btn btn-success btn-sm" id="searchCustomerNumberBtn" onclick="javascript:validateCustomerNumberSearch();return false;"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
										</div>
										<input type="hidden" name="searchType" id="searchType" value="customerIdentity">
									</sf:form>
									
								</div>
								<div class="col-lg-5 spaceBelow_20">	
									<sf:form name="searchForm" id="searchCustomerNameForm" action="/abankus/platform/customerProfileSearch" method="post">
										<div>			
											<label>First Name:</label><input type="text"  id="firstname" name="firstName" class="form-state width-100 customer_search" placeholder=" First Name">
										</div>
										<label>Last Name:</label><input type="text" id="lastname" name="lastName" class="form-state width-100 customer_search" placeholder=" Last Name">
										<div>
											<button type="submit" class="btn btn-success btn-sm" id="searchCustomerNameBtn" onclick="javascript:validateCustomerNameSearch();return false;"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
										</div>
										<input type="hidden" name="searchType" id="searchType" value="customerDetail">
									</sf:form>
								</div>			
							</div>
							
							<div class="hidden vertical-divider" id="customerOrderDiv">
								<div class="col-lg-5 spaceBelow_20">
									<sf:form name="searchForm" id="searchCustomerPaymentForm" action="/abankus/platform/customerPaymentSearch" method="post">
										<div>
											<label>Customer Number:</label><input type="text" name="customerNumber" id="customerNumber" class="form-state width-100 customer_search" placeholder="Customer Number">					
										</div>
										<div>			
											<label>From:</label><input type="text"  id="transactionFrom" name="transactionFrom" class="form-state width-100 customer_search" placeholder=" First Name">
											
										</div>
										<label>To:</label>
										<div class="input-group date  spaceBelow_20">
											<input type="text" id="transactionTo" name="transactionTo" class="form-control customer_search" placeholder=" Last Name">
											<div class="input-group-addon">
										        <span class="glyphicon glyphicon-th"></span>
										    </div>
									    </div>
										<div>
											<button type="submit" class="btn btn-success btn-sm" id="searchCustomerPaymentBtn" onclick="javascript:validateCustomerPaymentSearch();return false;"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
										</div>
										<input type="hidden" name="searchType" id="searchType" value="customerPayments">
									</sf:form>
								</div>
								<div class="col-lg-5 spaceBelow_20">	
									<sf:form name="searchForm" id="searchEmployeePaymentForm" action="/abankus/platform/datePaymentSearch" method="post">	
										<div>	
											<label>Transaction From:</label><input type="text" name="transactionFrom" id="empTransactionFrom" class="form-state width-100 employee_search" placeholder="Employee First Name">
										</div>	
										<label>Transaction To:</label><input type="text" name="transactionTo" id="empTransactionTo" class="form-state width-100 employee_search" placeholder="Employee Last Name">
										<div>
											<button type="submit" class="btn btn-success btn-sm" id="searchEmployeePaymentBtn" onclick="javascript:validateEmployeePaymentSearch();return false;"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
										</div>
										<input type="hidden" name="searchType" id="searchType" value="employeePayments">
									</sf:form>
								</div>
							</div>
							<div class="clearfix"></div>
							</div>
					</div>	  
				  </div>			
				 <c:if test="${not empty searchError}">
					<div class="page-header">
						<h1>Search Results</h1>
					</div>				 
					<div class="alert alert-danger col-lg-8" role="alert">					
						 ${searchError }
					</div>
				</c:if>	
				<div id="customerPaymentList">
				<c:if test="${not empty customerList}">
					<div class="page-header">
						<h1>Search Results</h1>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>Customer Number</th>
								<th>Customer Name</th>
								<th>Address</th>
								<th>Contact Information</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${customerList}" var="customer">
								<tr>
									<td>${customer.getCustomerNumber()}</td>
									<td>${customer.getCustomerName()}</td>
									<td>${customer.getAddressId()}</td>
									<td>
										<div>${customer.getEmailId()}</div>
										<div>${customer.getPhoneId()}</div>
									</td>
									<td><a href="<c:url value="/customers/viewProfile?searchType=customerNumber&customerNumber=${customer.getCustomerNumber() }" />" >View Profile</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>				
				</c:if>	
				<c:if test="${not empty customerSearchPayment}">
				<div class="page-header">
					<h1>Search Results</h1>
				</div>				
					<table class="table">
						<thead>
							<tr>
								<th>Transaction Number</th>
								<th>Amount Paid</th>
								<th>Payment Type</th>
								<th>Order Number</th>
								<th>Payment Date</th>
								<th>Accepted By</th>
							</tr>
						</thead>
						<tbody id="customerPaymentBody">
							<c:forEach items="${customerSearchPayment}" var="payment">
								<tr>
									<td>${payment.getConfirmationNumber() }</td>
									<td>${payment.getAmountPaid() }</td>
									<td>${payment.getPaymentType() }</td>
									<td>${payment.getOrderNumber()}</td>
									<td>${payment.getDatePaid() }</td>
									<td>${payment.getEmployeeId() }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	</div>
		<%-- Include page header --%>
	<jsp:include page="../footer.jsp"/>
	<!-- Page Header ends -->

</body>
<script type="text/javascript">
$(document).ready(function(e){
	$("#searchCustomerNumberBtn").click(function(){
		if(validateCustomerNumberSearch()){
			$("#searchCustomerNumberForm").submit();
		}
	});
	
	$("#searchEmployeePaymentBtn").click(function(){
		if(validateEmployeePaymentSearch()){
			$("#searchEmployeePaymentForm").submit();
		}
	});

	$("#searchCustomerPaymentBtn").click(function(){		
		if(validateCustomerPaymentSearch()){
			$("#searchCustomerPaymentForm").submit();
		}
	});
	
	$("#searchCustomerNameBtn").click(function(){
		if(validateCustomerNameSearch()){
			$("#searchCustomerNameForm").submit();
		}
	});
});
$(function() {
    $( "#transactionFrom" ).datepicker();
    $( "#transactionTo" ).datepicker({
    	useCurrent: false
    });
    $("#transactionFrom").on("dp.change", function (e) {
        $('#transactionTo').data("DateTimePicker").minDate(e.date);
    });
    $("#transactionTo").on("dp.change", function (e) {
        $('#transactionFrom').data("DateTimePicker").maxDate(e.date);
    });    
  });
</script>
</html>