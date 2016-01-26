<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<c:import var="platform" url="com.boateng.abankus.utils.PlatformUtils"/>
<c:set var="date" value="<%=new java.util.Date()%>" /> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Corporation - Make a Payment</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/platform.css" />"	rel="stylesheet" />
		<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
		<script	src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/bootstrap.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
	</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div id="container" class="container">
		<div class="row">
		  	<div class="col-md-6">
		  	  <div class="page-header">
		  	  	<span class="lead">Submit Payment</span>
		  	  </div>
		      <label for="accountNumber">Account Number:</label>
		      <input type="text" name="accountNumber" id="accountNumber" class="form-state width-100">
		      <label for="paymentAmount">Amount:</label>
		      <input type="text" name="paymentAmount" id="paymentAmount" class="form-state width-100">
		      <label for="paymentMethod">Payment Method:</label>
			  <select name="paymentMethod" id="paymentMethod" class="form-state"	onchange="javascript:showBankCardInfoDiv(this);">
				<option value="cash">Cash</option>
				<option value="check">Bank Draft (Check)</option>
				<option value="card">Credit/Debit Card</option>
			  </select>
										<div id="bankInfoDiv" class="hidden moveL_30 col-sm-12 col-xs-12 col-md-12 row-non">
											<h4 class="underline">Cheque Details</h4>
											<div class="col-sm-12 col-xs-12 col-md-7 row-non">
												<label for="bankCustName">Name on Bank Account:</label>
												<input type="text" name="bankCustName"	class="form-state width-100" id="bankCustName" value="" />											

											</div>
											<div class="col-sm-12 col-xs-12 col-md-7 row-non">
												<label for="bankAccountNumber">Account Number:</label>
												<input type="text" id="bankAccountNumber" name="bankAccountNumber"	class="form-state  width-100" />
											</div>
											<div class="col-sm-12 col-xs-12 col-md-7 row-non">
												<div class="help-text bold">Confirm Account Number</div>
												<input type="text" id="confirmAccountNumber" name="confirmAccountNumber" class="form-state  width-100" />
											</div>
											<div class="col-sm-12 col-xs-12 col-md-7 row-non">
												<label for="bankRoutingNumber">Routing Number:</label>
												<input type="text"	id="bankRoutingNumber" name="bankRoutingNumber"	class="form-state  width-100" />
											</div>
											<div class="col-sm-12 col-xs-12 col-md-7 row-non">
												<label for="bankName">Name of Bank:</label> 
												<input type="text"	name="bankName" class="form-state width-100" id="bankName" value="" />
											</div>
											<div class="col-sm-12 col-xs-12 col-md-7 row-non">
												<label for="checkNumber">Check/Cheque Number:</label>
												<input type="text" name="checkNumber"	class="form-state width-100" id="checkNumber" value="" />
											</div>											
										</div>
										<div id="cardInfoDiv" class="hidden col-sm-12 col-xs-12 col-md-12 row-non">
													<h4 class="page-header">Credit Card Payment</h4>
													<div class="col-sm-12 col-xs-12 col-md-7 row-non">
														<label>Name on Card:</label>
														<input type="text" name="nameOnCard" class="form-state width-100">	
													</div>
													<div class="col-sm-12 col-xs-12 col-md-7 row-non">												
														<label>Card Number:</label>
														<input type="text" name="cardNumber" class="form-state  width-100"><span><input name="cardType" type="hidden" value="Visa"/></span>
													</div>
													<div class="clearfix"></div>
													<div class="col-sm-12 col-xs-12 col-md-3 row-non">
														<label>Expiration Date:</label>
														<input type="text" class="form-state width-100" name="expirationDate" placeholder="MM/YY">
													</div>
													<div class="col-sm-12 col-xs-12 col-md-4 row-non">
														<label>Security Number:</label>
														<input type="text" class="form-state width-100" name="securityNumber">												
													</div>																													
										</div>
				<div>
					<button class="btn btn-success" onclick="javascript:submitPayment(); return false;">Submit Payment</button>
				
				</div>		      
		    </div>		
		</div>	
	</div>
		<%-- Include page header --%>
	<jsp:include page="../footer.jsp" />
</body>
<script>
$(document).ready(function(){
	
});
</script>
</html>