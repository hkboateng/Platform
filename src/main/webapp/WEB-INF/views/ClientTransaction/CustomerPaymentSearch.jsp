<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Corporation - Search for Order</title>
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
			<div class="col-sm-10 col-md-10 col-lg-10 main">
			
			<h1> Make A Payment</h1>
			<hr class="line1">
						<div class="paymentContainer">
						<div class="platform-alert" id="paymentMessage">
						
						</div>
						<div class='spaceBelow_20'>
						<sf:form name="searchOrder" method="post" action="/abankus/Payments/searchPaymentDetail">
							<p>Search for Customer's Order:</p>
							<label class="sr-only">Order Number:</label>
    						<input type="text" name="searchField" id="searchField"  class="custom-text" placeholder="Customer Id, Customer Number, Order Number"/>
    						<button class="btn btn-success" value="Search" id="btnSearchPayment"><i class="fa fa-search"></i></button>
    						<hr>
						</sf:form>
						</div>	
						<div id="paymentContainer">
							<div class="col-md-12 col-sm-12  col-lg-8">
							<c:choose>
								<c:when test="${not empty customerOrderList }">
									<sf:form name="submitPayment" id="submitPayment" method="post" action="/abankus/Payments/submitBillPayment">
									<div id="paymentSegment">
										
										  <!-- Table -->
										<table class="table" id="paymentTable">
										  	<tr>
										  		<th>#</th>
										  		<th>Date</th>
										  		<th>Order Number</th>
										  		<th>Product Name</th>
										  		<th>Amount Remaining</th>
										  	</tr>
												<c:forEach items="${customerOrderList }" var="orderList">
													<c:if test="${orderList.amountRemaining() gt 0.00}">
														<tr class="clickable-row" data-object="${orderList.amountRemaining() }">
														<td><input type="radio" name="orderNumber" id="orderNumber" value="${orderList.clientOrderId }" title="${orderList.getProductCode() }" /></td>
														<td>${orderList.convertOrderDate() }</td>
														<td>${orderList.getOrderNumber() }</td>
														<td id="productCode">${orderList.getProductCode() }</td>
														<td>${orderList.amountRemaining() }</td>
														</tr>
														
													</c:if>
												</c:forEach>									
										  </table>
										  <label id="orderNumber-errorLabel" class="error"></label>
										<div class="col-lg-12">
										</div>
											<label>Payment Method:</label>
											<span id="paymentMethodError" class="error"></span>
											<select name="paymentMethod" id="paymentMethod" class="form-state" onchange="javascript:showBankCardInfoDiv(this);">
												<option value=""></option>
												<option value="cash">Cash</option>
												<option value="check">Check (Cheque)</option>
												<option value="card">Back Issued Debit Card</option>
											</select>
												<div id="bankInfoDiv" class="hidden">
													<h4 class="page-header">Cheque Details</h4>
		
													<label for="bankName">Name of Bank:</label> <input type="text"
														name="bankName" class="form-state width-100" id="bankName" value="" />
													<label for="bankAccountNumber" class="platform_form-block">Account Number:</label>
													<input type="text" id="bankAccountNumber" name="bankAccountNumber"	class="form-state  width-50" />
													<label for="confirmAccountNumber" class="platform_form-block">Confirm Account Number</label>
													<input type="text" id="confirmAccountNumber" name="confirmAccountNumber" class="form-state  width-50" />
													<label for="bankRoutingNumber" class="platform_form-block">Routing Number:</label>
													<input type="text"	id="bankRoutingNumber" name="bankRoutingNumber"	class="form-state  width-50" />
													<label for="bankCustName" class="platform_form-block">Name on Bank Account:</label>
													<input type="text" name="bankCustName"	class="form-state width-100" id="bankCustName" value="" />
													<label for="checkNumber" class="platform_form-block">Check/Cheque Number:</label>
													<input type="text" name="checkNumber"	class="form-state width-50" id="checkNumber" value="" />											
												</div>	
												<div id="cardInfoDiv" class="hidden col-sm-12 col-xs-12 col-md-50 row-non">
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
											<div class="clearfix"></div>																		
											<label for="amountPaid">Payment Amount:	</label>
											</div>	
											<input type="text" name="amountPaid" id="amountPaid" onblur="javascript:formatAmount('amountPaid',this)" value="" class="form-state" placeholder="Amount">
											<hr>
											
											<input type="hidden" name="maximumAmount" id="maximumAmount" value=""/>
											<input type="hidden" name="customer" value="${customer.getCustomerId() }"/>	
											<p>
												<input type="submit" onclick="javascript:validatePaymentForm(); return false;" value="Submit Payment" id="btnSubmitPayment" class="btn btn-success"/>		
												<a href="<c:url value="/platform/index" />" class="btn btn-danger moveL_20">Cancel</a>	
											</p>								
									</div>
	
											<c:set var="date" value="<%=new java.util.Date()%>" /> 
											<%-- Payment Summary Container --%>
											<div id="paymentSummary">
												<div class="lead">
												 	By Entering your Customer Identification Code and Submitting this Payment, you agree to
												 	the Terms and Conditions to this transaction and also agree to pay the amount below.
												</div>
												<div  id="summaryAmount" class="lead"></div>
												<div>
													<label class="labelLength_20">Payment Date:</label><span id="summaryDate"><fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" /></span>
												</div>
												<div>
													<label class="labelLength_20">Product Name:</label><span id="summaryProduct"></span>
												</div>
												<div>
													<label class="labelLength_20">Payment Method:</label><span id="summaryPaymentMethod"></span>
												</div>
												
												<div id="summaryCheckDetails">
													<div>
														<label class="labelLength_20">Check Number:</label><span id="summaryCheckNumber"></span>
													</div>
													<div>
														<label class="labelLength_20">Bank Name:</label><span id="summaryBankName"></span>
													</div>
													<div>
														<label class="labelLength_20">Account Number</label><span id="summaryBankAccount"></span>								
													</div>
												</div>
				
																
												<%-- Customer Pin Code or Identification --%>
												<label for="passcode">Pin Code</label>
												<div id="customerPin-error" class="help-text-inline-error"></div>
												<input type="password" name="passcode" id="passcode" class="form-state width-50" />
												
												<div class="help-text-inline inlineBlock">
													Click only once because clicking more than once will submit the payment more than once.
												</div>
												<div>
													<input type="button" class="btn btn-success" id="submitPaymentBtn" value="Submit Buttom" />		
													<a href="#" id="cancelPayment" class="btn btn-default moveL_20">Cancel Payment</a>												
												</div>						
											</div>	
											<%-- End Payment Container --%>										
										</sf:form>
																				
								</c:when>
								<c:otherwise>
								<c:if test="${not empty searchError }">
								<div class="alert alert-info alert-dismissible fade in"  role="alert">

									<span  id="paymentMessage">
										${searchError}
									</span>							
								</div>									
								</c:if>
								</c:otherwise>
							</c:choose>
							

							
							<div id="paymentConfirmation" class="modal" tabindex="-1" role="dialog">
								<div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								        <h4 class="modal-title">Payment Confirmation</h4>
								      </div>
								      <div class="modal-body">
											You have agreed to Payment the following amount <span id="summaryAmount"></span>
											<div id="confirmationDate"><fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" /></div>
											<div id="confirmationOrderNumber"></div>
											<div id="confirmationPaymentType"></div>
											<div id="confirmationCheckNumber"></div>
											<div id="confirmationBankName"></div>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								        <button type="button" id="" class="btn btn-primary">Save changes</button>
								      </div>
								    </div><!-- /.modal-content -->
								  </div><!-- /.modal-dialog -->							
								
							</div>
							</div>
						</div>		
						</div>
			</div>
		</div>
	</div>
	<%--
					$('#paymentConfirmation').modal({
					backdrop : 'false',
					show: 'true',
					escape: 'false'
				});
	 --%>
	<script>
	var messageDiv = $('#paymentMessage');
	$(document).ready(function(){
		//$('#paymentMessageContainer').hide();
		$('#paymentSummary').hide();
		
		$("#btnSearchOrder").click(function(){
			document.searchOrder.searchField.value = searchField;
			document.searchOrder.submit();

		});
		
		$('#amountPaid').blur(function(){
			var maxAmount = $('#maximumAmount').val();
			var amount = $(this).val();
			var paymentValid = isPaymentAmountValid(amount,maxAmount);	
			if(amount === "InValid"){
				showMessage('Enter a valid Payment Amount.','alert',messageDiv);
				return;
			}
			if(maxAmount){
				if(!paymentValid){
					showMessage('You cannot pay more than you owe.','alert',messageDiv);
				}else{
					hideMessageDiv(messageDiv);
				}			
			}else{
				showMessage('Select from the list, what bill you will be paying.','info',messageDiv);
			}

		});
		
		$('.clickable-row').click(function(){
			var amount = $(this).data("object");
			$('#maximumAmount').val(amount);
		});
		
		$('#cancelPayment').click(function(){
			$('#paymentSegment').show();
			$('#summaryCheckDetails').hide();
			$('#paymentSummary').hide();
		});
		$('#btnSubmitPayment').click(function(){
		
			if(validatePaymentForm() && validateOrderNumberRadioBtn()){
				$('#summaryCheckDetails').hide();
				$('#paymentSegment').hide();
				var amountPaid = $('#amountPaid').val();
				var paymentMethod = $('#paymentMethod').val();
				getRadioBtnValue("#paymentTable",'#summaryProduct');
				var bankName = $('#bankName').val();
				var accountNumber = $('#custAccountNumber').val();
				var check = $('#checkNumber').val();
				
				$('#summaryOrderDate').html(new Date());
				$('#summaryPaymentMethod').html(paymentMethod);
				$('#summaryAmount').html("$"+amountPaid);
				if(paymentMethod == 'check'){
					$('#summaryCheckDetails').show()
					$('#summaryBankName').html(bankName);
					$('#summaryBankAccount').html(accountNumber);
					$('#summaryBankCheck').html(check);					
				}else{
					$('#summaryCheckDetails').hide();
				}
				$('#paymentSummary').show();
				//document.submitPayment.submit();
			}
			
		});
		
		$('#submitPaymentBtn').click(function(){
			var form = $('form').serialize();
			$('#customerPin-error').text(" ");
			
			$(document).ajaxStart(function() {
				  $("#loading").show();
			});

			$(document).ajaxComplete(function() {
				 $("#loading").hide();
			});
			$.ajax({
				url: 'submitBillPayment',
				data : form,
				dataType: 'json',
				beforeSend: function(){
					 $("#loading").text("Validating your Pin Number");
				},
				success : function(result){	
					if(result == false){
						showMessage('You must enter a valid Pin Number!!!','alert',messageDiv);
						$("#paymentMessage").text("You must enter a valid Pin Number!!!");			
						$('#paymentSegment').show();
						$('#summaryCheckDetails').hide();
						$('#paymentSummary').hide();						
					}else{
						$('#paymentConfirmation').modal({
							
						});
					}
					
				},
				error : function(err){
					console.log(err.responseText+" error");
				}
			});			
		});
	});
	
	
	function validatePassCode(value){
		
		var codePattern = new RegExp('/^[A-z0-9]+');
		if(isEmpty(value) && isBlank(str)){
			$('#customerPin-error').html("Enter your Pin Number before clicking Submit.");
			return;
		}
	}
	function validateOrderNumberRadioBtn(){
		if($("input[name=orderNumber]:checked").length < 1) {
			  $('#orderNumber-errorLabel').html('Select an Order/Bill to continue...');
			  $('#orderNumber-errorLabel').removeAttr('style');
			  return false;
		}else{
			return true;
		}
	}

	function findPaymentDetails(){
		var searchField = $("#searchField").val();
		console.log(searchField);
		
		var data = {
				searchField: searchField	
		}
		//document.searchOrder.searchField.value = searchField;
		//document.searchOrder.submit();
		if(!isEmpty(searchField)){
			$.ajax({
				url: 'searchPaymentDetail',
				type: 'get',
				data: data,
				dataType: 'html',
				success: function(response){
					console.log(response);
					if(response == null){
						showMessage('No information was found. Try again with a valid Customer Information','alert',messageDiv);
					
					}
															
				},
				error: function(err){
					showMessage('Problem occured while processing your request, please try again.','alert',messageDiv);
					
				},
				complete: function(e){
					console.log(e.statusText+" complete");
				}
			});
		}else{
			showMessage('Order Number cannot be null.','alert',messageDiv);
			$("#btnSearchOrder").html('<i class="fa fa-search"></i>');
		}
	}

	function validatePaymentForm(){
		$("#submitPayment").validate({
			onsubmit:false,
			rules : {
				amountPaid: {
					required :true,
					number:true
				},
				paymentMethod : {
					required: true
				},
				checkNumber : {
					required: true,
					digits: true
				},
				bankCustName : {
					required: true
				},
				bankRoutingNumber : {
					required: true,
					digits: true
				},
				bankAccountNumber: {
					required: true,
					digits: true
				},
				confirmAccountNumber: {
					required: true,
					equalTo: '#bankAccountNumber'
				},
				bankName: {
					required: true
				}
			},
			messages : {
				amountPaid : {
					required: "Enter the amount that the Customer is paying",
					numbers: "Enter a valid Payment Amount"
				},
				paymentMethod : {
					required: 'Select a Payment Method to continue'
				},
				checkNumber : {
					required : 'Enter the Check Number',
					digits: 'Enter a valid check number'
				}
			}
		});
		if(!$('#submitPayment').valid())
			 return false;
		 return true;			
	}

	function isPaymentAmountValid(paymentAmount,maxAmount){
		
		var paymentValid = false;
		if((paymentAmount > 0.0) && parseFloat(paymentAmount) <= parseFloat(maxAmount)){
			paymentValid = true;
		}
		return paymentValid;
	}
	</script>
	</body>
	</html>
