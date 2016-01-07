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
	<div id="container" class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-3 col-lg-3">
				<jsp:include page="../sidebar.jsp" />
			</div>
			<div class="col-sm-9 col-md-9 col-lg-9 main">
			<h1 id="paymentHeading"> Make A Payment</h1>
			<hr class="line1">
						<div class="paymentContainer" id="paymentContainer">
						<div class="platform-alert" id="paymentMessage">
						${validation }
						</div>
						<div class="platform-alert" id="paymentForm">
						<div id="loading">
						</div>
						<c:choose>
							<c:when test="${not empty customerOrder }">
								
									<sf:form name="customerPaymentForm" action="submitOrderPayment" method="post">
									<div class="col-md-6" id="paymentEntry">
										<label for="orderNumber">Order Number:</label>
										<input type="text"	name="orderNumber" id="orderNumber" class="form-state  width-75" value="${billing.getClientOrderId().getOrderNumber() }" /> 
										<label for="paymentMethod">Select Payment	Type:</label> 
										<select name="paymentMethod" id="paymentMethod" class="form-state"	onchange="javascript:showBankCardInfoDiv(this);">
											<option value="cash">Cash</option>
											<option value="check">Bank Draft (Check)</option>
											<option value="card">Credit/Debit Card</option>
										</select>
										<div id="bankInfoDiv" class="hidden moveL_30">
											<h4 class="underline">Cheque Details</h4>

											<label for="bankName">Name of Bank:</label> 
											<input type="text"	name="bankName" class="form-state width-100" id="bankName" value="" />
											<label for="bankAccountNumber">Account Number:</label>
											<input type="text" id="bankAccountNumber" name="bankAccountNumber"	class="form-state  width-100" />
											<div class="help-text bold">Confirm Account Number</div>
											<input type="text" id="confirmAccountNumber" name="confirmAccountNumber" class="form-state  width-100" />
											<label for="bankRoutingNumber">Routing Number:</label>
											<input type="text"	id="bankRoutingNumber" name="bankRoutingNumber"	class="form-state  width-100" />
											<label for="bankCustName">Name on Bank Account:</label>
											<input type="text" name="bankCustName"	class="form-state width-100" id="bankCustName" value="" />
											<label for="checkNumber">Check/Cheque Number:</label>
											<input type="text" name="checkNumber"	class="form-state width-100" id="checkNumber" value="" />											
										</div>
										<div id="cardInfoDiv" class="hidden moveL_30">
											<h4 class="underline">Credit Card Payment</h4>
											<label>Name on Card:</label>
											<input type="text" name="nameOnCard" class="form-state">
											<label>Card Number:</label>
											<input type="text" name="nameOnCard" class="form-state">
											<label>Expiration Date:</label>
											  <div class="col-xs-1">
											    <input type="text" class="form-state " placeholder=".col-xs-2">
											  </div>
											  <div class="col-xs-1">
											    <input type="text" class="form-state " placeholder=".col-xs-3">
											  </div>																				
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
										</div><%--<fmt:formatNumber value="${customerOrder.getTotalAmount()} --%>
										<c:set var="amountLeft" value="${billing.totalAmountRemaining() }"/>
										<label for="paymentAmount">Payment Amount:</label>
										 <input type="text"	name="paymentAmount" class="form-state  width-75" id="paymentAmount" value="${amountLeft}" onblur="javascript:checkAmountPaid(this.value);formatAmount('paymentAmount',this)" placeholder="Amount"/>
											<p>
												<button name="paymentSubmitBtn" id="paymentSubmitBtn" onclick="javascript:customerPaymentConfirmation(document.paymentForm);return false;" class="btn btn-success">Continue with Payment</button>
												<a href="#" class="moveL_20">Cancel</a>
											</p>	
																			
											<input type="hidden" name="pId" value="${orderNumber }" id="pId"/>	
											<input type="hidden" name="orderTotalAmount" id="orderTotalAmount" value="${customerOrder.getTotalAmount()}">	
											<input type="hidden" name="unicode" id="cust" value="${customerOrder.getCustomer().customerId}">
											<input type="hidden" name="amountleft" id="amountleft" value="${amountLeft}"/>
											</div>	
												<!-- Review and Submit Customer Payment -->
												<div class="hidden col-md-10" id="paymentSummary">
													<p class="lead">By entering your Pin  Code, you are agreeing to pay $<span id="paymentamountSummary"></span> today <fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" /> with payment in the form of <span id="paymentFormSummary"></span> for the items ${product.getProductName()}.
														
													<div>
														<label for="customerPin">Customer PIN Number:</label>									
														<div id="customerPin-error" class="help-text-inline-error">
															
														</div>	
														<input type="password" name="customerPIN" id="customerPIN" onchange="" class="form-state width-50"  />													
													</div>
														<div class="help-text-inline inlineBlock">
															Click only once because clicking more than once will submit the payment more than once.
														</div>	
														<hr>
														<input type="button" class="btn btn-success" id="submitBtn" value="Submit Buttom" />		
														<a href="#" id="cancelPayment" class="btn btn-default moveL_20">Cancel Payment</a>										
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

	<%--- Confirmation Page --%>

<script>
$(document).ready(function(){
	$('#paymentSubmitBtn').attr('disabled','disabled');
	
	var messageDiv = $('#paymentMessage');
	$("#loading").hide();
	
	$("#paymentHeading").html("Make A Payment");


	$("#cancelPayment").on('click',function(){
		$('#paymentSummary').hide();
		$('#paymentEntry').show();
		$("#paymentHeading").html("Make Payment");
	});
		
	$('#submitBtn').click(function(e){
		e.preventDefault();
		var custId = $("#customerPIN").val();
		var customerId = $("#cust").val();
		var form = $('form').serialize();
		var data = {
				customerId:customerId ,
				customerpin:custId,
				orderTotalAmount : $("#orderTotalAmount").val(),
				orderNumber : $("#orderNumber").val(),
				paymentSchedule :$("#paymentSchedule").val(),
				paymentAmount: $("#paymentAmount").val(),
				paymentType: $("#paymentMethod").val()
			}
		if(!validatePin(custId)){
			$('#customerPin-error').text("You must enter a valid Pin Number!!!");
			showMessage("You must enter a valid Pin Number!!!","alert");
			return;
		}else{
			$('#customerPin-error').text(" ");
			hideMessageDiv(messageDiv);
			$(document).ajaxStart(function() {
				  $("#loading").show();
			});

			$(document).ajaxComplete(function() {
				 $("#loading").hide();
			});
			$.ajax({
				url: 'validateCustomerAuthenticate',
				data : form,
				dataType: 'json',
				beforeSend: function(){
					 $("#loading").text("Validating your Pin Number");
				},
				success : function(result){					
					if(result == "false"){
						showMessage("You must enter a valid Pin Number!!!","alert",messageDiv);
						
					}else{
						$('#paymentContainer').html('<h3>Your payment have being submitted.</h3><p>Your confirmation number is: <b>'+result+'</b></p>');
					}
					
				},
				error : function(err){
					console.log(err+" error");
				}
			});
		}		
	});
});
function hideMessageDiv(message){
	message.removeClass('platform-alert-caution');
	message.removeClass('platform-info-caution ');	
	message.html(" ");
	message.addClass('hidden');
}

function showMessage(message,level,messageDiv){
	if(level === "alert"){
		messageDiv.addClass('platform-alert-caution');
	}else if(level === "info"){
		messageDiv.addClass('platform-info-caution ');	
	}
	messageDiv.html(message);
	messageDiv.removeClass('hidden');	
}
<%--
function submitPayment(results,messageId){
	//document.customerPaymentForm.submit();
	var form = $('#customerPaymentForm').serialize();
	var data = {
		orderTotalAmount : $("#orderTotalAmount").val(),
		orderNumber : $("#orderNumber").val(),
		paymentSchedule :$("#paymentSchedule").val(),
		paymentAmount: $("#paymentAmount").val(),
		paymentType: $("#paymentType").val()
	}
	$.ajax({
		url: 'submitOrderPayment',

		data : data,
		type: 'get',
		dataType: 'json',
		beforeSend: function(){
			$('#lodaing').text(" ")
			$('#lodaing').text("Everthing looks good, sending data.");
		},
		success : function(result){
			if(!result.status){
				displayMessage(result,messageId);			
			}
			
		},
		error : function(err){
			console.log(err);
		}
	});
}
--%>
function validatePin(custId){
	var valid = false;
	if(!isEmpty(custId) || isAlphaNumeric(custId)){
		valid = true;
	}
	
	return valid;
}
function displayMessage(result,messageDiv){
	$(messageDiv).addClass('platform-info-caution');
	$.each(result,function(key,value){
		messageDiv.text(result);
	});
}

function checkAmountPaid(amount){
	var amountleft = $('#amountleft').val();
	amount = amount.replace(',','');
	if(compare(parseFloat(amount),parseFloat(amountleft))){
		$('#paymentSubmitBtn').attr('disabled','disabled');
		$("#paymentMessage").addClass('platform-alert-caution').text("You cannot pay more than what you owe. You must up to $"+amountleft)
		$('#paymentAmount').val("");
		$('#paymentAmount').focus();
		
	}else{
		$("#paymentMessage").removeClass('platform-alert-caution').text("").addClass('hidden');
		$('#paymentSubmitBtn').removeAttr('disabled');
	}
}

function compare(a,b){
	return (a > b);
}
/**
 * Shows the Summary of Order Payment and pops up the Modal window.
 */
function customerPaymentConfirmation(form){
	
	var accountnumber = $("#orderNumber").val() ;
	var paymentamount = $("#paymentAmount").val();
	var paymenttype = $("#paymentMethod").val();
	var paymentschedule = $("#paymentSchedule").val();
	
	if(isEmpty(paymentamount)){
		$("#paymentMessage").addClass('platform-alert-caution');
		getElementById("paymentMessage").innerHTML = "Payment Amount cannot by empty.";
	}else{
		getElementById("paymentMessage").innerHTML = "";
		$("#paymentMessage").removeClass('platform-alert-caution');
		$("#paymentHeading").html("Review and Confirm Payment");
		$("#accountNumberSummary").html(accountnumber);
		$("#paymentamountSummary").html(paymentamount);
		$("#paymentFormSummary").html(paymenttype.toUpperCase());
		$("#paymentscheduleSummary").html(paymentschedule.toUpperCase());
		// Openning Modal
		$('#paymentEntry').hide();
		$("#paymentSummary").removeClass("hidden");
		$("#paymentSummary").show();
	}

}
</script>
	</body>
	</html>