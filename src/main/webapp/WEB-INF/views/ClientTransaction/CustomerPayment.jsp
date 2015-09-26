<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<c:import var="platform" url="com.boateng.abankus.utils.PlatformUtils"/>

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
			<h1> Make A Payment</h1>
			<hr class="line1">
						<div class="paymentContainer">
						<div class="platform-alert" id="paymentMessage">
						<c:choose>
							<c:when test="${not empty order }">
								<div class="col-md-6">
						
									<label for="accountNumber">Order Number:</label> <input type="text"
										name="accountNumber" id="accountNumber" class="form-state  width-75"
										value="${order }" /> <label for="paymentType">Select Payment
										Type:</label> <select name="paymentType" id="paymentType" class="form-state"
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
									<label for="paymentSchedule">Payment Schedule:</label> <select
										name="paymentSchedule" id="paymentSchedule"
										class="form-state  width-75"
										onchange="javascript:showPaymentDetails(this);">
										<option value="fullPayment">Full Payment</option>
										<option value="partialPayment">Partial Payment</option>
										<option value="installmentPayment">Installment Payment</option>
						
									</select>
									<div id="paymentDetails" class="hidden">
										<p>Hello EveryOne</p>
									</div>
									<label for="paymentAmount">Payment Amount:</label>
									 <input type="text"	name="paymentAmount" class="form-state  width-75" id="paymentAmount" value="<fmt:formatNumber value="${amount }" type="currency"/>" />
								</div>
								<div class="col-md-6">
									<label>Order Details</label><hr>
									<p><label>Total Cost:&nbsp; </label>$ ${amount }</p>
							
									<p><label>Order Date:&nbsp;</label>${orderDate }</p>
									<p><label>Product Code:&nbsp;</label>${product.productName} - ${product.productCode}</p>
								</div>
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
							<p>
								<button name="paymentSubmitBtn" id="paymentSubmitBtn" onclick="javascript:customerPaymentConfirmation(document.paymentForm);return false;" class="btn btn-success">Continue with Payment</button>
							</p>					
						</div>						
			</div>
		</div>
	</div>
<!-- Payment Summary Modal -->
<div class="modal fade" id="paymentSummaryModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Payment Summary and Confirmation</h4>
      </div>
      <div class="modal-body">
			<div class="" id="paymentSummary">
				<p><label for="accountNumberSummary">Order Number:</label><span id="accountNumberSummary"></span></p>
				<p><label for="paymentamountSummary">Payment Amount:</label><span id="paymentamountSummary"></span></p>
				<p><label for="paymentTypeSummary">Payment Type:</label><span id="paymentTypeSummary"></span></p>	
				<p><label for="paymentTypeSummary">Payment Type:</label><span id="paymentscheduleSummary"></span></p>	
				<p><label>Product Information:</label><span id="summaryProductInfo"></span></p>								
					<label for="customerPin">Customer PIN Number:</label>									
						<div id="customerPin-error" class="help-text-inline-error">
						</div>	
					<input type="password" name="customerPIN" id="customerPIN" class="form-state" />	
					<label for="customerPin">Customer PIN Number:</label>											
					<button name="btnSubmitPayment" id="btnSubmitPayment" onclick="" class="btn btn-success">Submit Payment</button><br>	
					<div class="help-text-inline">
						Click only once because clicking more than once will submit the payment more than once.
					</div>													
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
$(document).ready(function(){
	
	$('#paymentAmount').blur(function(){
		$(this).mask('000,000.00', {reverse: true});
	});
	$('#paymentAmount').focusout(function(){
		$(this).mask('000,000.00', {reverse: true});
	});	
	$("#customerPIN").blur(function(){
		var isAlpha = isAlphaNumeric(this);
		if(!isAlpha){
			$("#customerPin-error").text("Please! enter valid PIN Number.");
			$(this).focus();
		}else{
			$("#customerPin-error").text("");
			$(this).focusout();				
		}
	});
		
});
</script>
	</body>
	</html>