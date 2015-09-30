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
		<script src="<c:url value="/resources/js/accounting/accounting.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/jquery-masked.js" />" type="text/javascript"></script>
	</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="row">
			<jsp:include page="../sidebar.jsp" />
			<div class="col-sm-9 col-md-10 col-md-offset-2 main">
			<h1 id="paymentHeading"> Make A Payment</h1>
			<hr class="line1">
						<div class="paymentContainer">
						<div class="platform-alert" id="paymentMessage">
						
						</div>
						<div class="platform-alert" id="paymentForm">
						<c:choose>
							<c:when test="${not empty customerOrder }">
								
									<sf:form name="customerPaymentForm" action="submitOrderPayment" method="post">
									<div class="col-md-6" id="paymentEntry">
										<label for="orderNumber">Order Number:</label> <input type="text"
											name="orderNumber" id="orderNumber" class="form-state  width-75"
											value="${order }" /> 
											<label for="paymentType">Select Payment
											Type:</label> <select name="paymentForm" id="paymentType" class="form-state"
											onchange="javascript:showBankInfoDiv(this);">
											<option value="cash">Cash</option>
											<option value="check">Bank Draft (Check)</option>
							
										</select>
										<div id="bankInfoDiv" class="hidden moveL_100">
											<label for="bankName">Name of Bank:</label> <input type="text"
												name="bankName" class="form-state width-100" id="bankName" value="" />
											<label for="bankAccountNumber">Account Number:</label> <input
												type="text" id="bankAccountNumber" name="bankAccountNumber"
												class="form-state  width-100" />
											<div class="help-text bold">Confirm Account Number</div>
											<input type="text" id="confirmAccountNumber"
												name="confirmAccountNumber" class="form-state  width-100" /> <label
												for="bankRoutingNumber">Routing Number:</label> <input type="text"
												id="bankRoutingNumber" name="bankRoutingNumber"
												class="form-state  width-100" /> <label for="bankCustName">Name
												on Bank Account:</label> <input type="text" name="bankCustName"
												class="form-state width-50" id="bankCustName" value="" />
										</div>
										<label for="paymentSchedule">Payment Schedule:</label>
										<select
											name="paymentSchedule" id="paymentSchedule"
											class="form-state  width-75"
											onchange="javascript:showPaymentDetails(this);">
											<option value="full Payment">Full Payment</option>
											<option value="partial Payment">Partial Payment</option>
											<option value="installment Payment">Installment Payment</option>
										</select>
										<div id="paymentDetails" class="hidden">
											<p>Hello EveryOne</p>
										</div>
										<label for="paymentAmount">Payment Amount:</label>
										 <input type="text"	name="paymentAmount" class="form-state  width-75" id="paymentAmount" value="<fmt:formatNumber value="${customerOrder.getTotalAmount()}" type="currency"/>" />
											<p>
												<button name="paymentSubmitBtn" id="paymentSubmitBtn" onclick="javascript:customerPaymentConfirmation(document.paymentForm);return false;" class="btn btn-success">Continue with Payment</button>
											</p>	
																			
											<input type="hidden" name="pId" value="${orderNumber }" id="pId"/>	
											<input type="hidden" name="orderTotalAmount" id="orderTotalAmount" value="${amount }">	
											<input type="hidden" name="cust" id="cust" value="${cust}">
											</div>		
												<!-- Review and Submit Customer Payment -->
												<div class="hidden col-md-6" id="paymentSummary">
													<p><label for="paymentDateSummary">Payment Date:&nbsp;</label>
													<span id="paymentDateSummary"><fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" />  </span></p>
													<p><label for="paymentamountSummary">Payment Amount:&nbsp;</label><span id="paymentamountSummary"></span></p>
													<p><label for="paymentFormSummary">Payment Type:&nbsp;</label><span id="paymentFormSummary"></span></p>	
													<p><label for="paymentscheduleSummary">Payment Schedule:&nbsp;</label><span id="paymentscheduleSummary"></span></p>	
													<p><label>Product Information:</label><span id="summaryProductInfo">${product.getProductName()}</span></p>								
														<label for="customerPin">Customer PIN Number:</label>									
															<div id="customerPin-error" class="help-text-inline-error">
															</div>	
														<input type="text" name="customerPIN" id="customerPIN" class="form-state width-50"  />
														<div class="help-text-inline inlineBlock">
															Click only once because clicking more than once will submit the payment more than once.
														</div>	
														<hr>
														<input type="button" class="btn btn-success" id="submitBtn" value="Submit Buttom" />		
														<a href="cancel" class="btn btn-default moveL_20">Cancel Payment</a>										
												</div>		
												<!-- End Review and Submit Customer Payment -->																
									</sf:form>
							</c:when>							
							<c:otherwise>
							<div class="alert alert-success" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								  <span aria-hidden="true">&times;</span>
								</button>							
								No Order Information is available.
							</div>
							<div class='spaceBelow_20'>
								<label class="sr-only">Order Number:</label>
	    						<input type="text" name="searchOrderId" id="searchOrderId"  class="custom-text" placeholder="Order Number"/>
	    						<button class="btn btn-primary" value="Search"><i class="fa fa-search" onclick="javascript:findCustomerOrder(document.searchOrderId);"></i></button>
							</div>								
							</c:otherwise>
						</c:choose>

						</div>
					
						</div>						
			</div>
		</div>
	</div>


<script>
$(document).ready(function(){
	$("#paymentHeading").html("Make A Payment");
	$('#paymentAmount').blur(function(){
		$(this).mask('000,000.00', {reverse: true});
	});
	$('#paymentAmount').focusout(function(){
		$(this).mask('000,000.00', {reverse: true});
	});	


	
});

function validatePin(custId){
	console.log(custId)
	var valid = false;
	if(isEmpty(custId) || !isAlphaNumeric(custId)){
		valid = false
	}else{
		valid = true;
	}
	
	return valid;
}

function submitPayment(custId){

	if(!validatePin(custId.value)){
		$('#customerPin-error').text("You must enter a valid Pin Number!!!");
		return;
	}else{
		$('#customerPin-error').text(" ");
		$('#pId').val($("#customerPIN").val());
		document.customerPaymentForm.submit();
	}
	
}
</script>
	</body>
	</html>