<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
	<div id="container" class="container-fluid">
		<div class="row">
			<div class="col-sm-2 col-md-2 col-lg-2">
				<jsp:include page="../sidebar.jsp" />
			</div>
			<div class="col-sm-10 col-md-10 col-lg-10 main">
			
			</div>
			<div class="col-sm-10 col-md-10 col-lg-10 main">
			
			<h1> Make A Payment</h1>
			<hr class="line1">
			${PlatformFields.SEARCH_ERROR_SESSION }
						<div class="paymentContainer">
						
						<div class="alert alert-warning alert-dismissible fade in" id="paymentMessageContainer" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							<span  id="paymentMessage">
							
							</span>							
						</div>

						<div class='spaceBelow_20'>
						<sf:form name="searchOrder" method="post" action="/abankus/Payments/searchPaymentDetail">
							<p>Search for Customer's Order:</p>
							<label class="sr-only">Order Number:</label>
    						<input type="text" name="searchField" id="searchField"  class="custom-text width-50" placeholder="Customer Id, Customer Number, Order Number"/>
    						<button class="btn btn-success" value="Search" id="btnSearchPayment"><i class="fa fa-search"></i></button>
    						<hr>
						</sf:form>
						</div>	
						<div id="paymentContainer">
							<div class="col-md-12 col-sm-12  col-lg-6">
							<c:choose>
								<c:when test="${not empty customerOrderList }">
									<sf:form name="submitPayment" id="submitPayment" method="post" action="/abankus/Payments/submitBillPayment">
									
									<div class="panel panel-default">
										  <!-- Default panel contents -->
										<div class="panel-heading">${customer.getCustomerName() }</div>
										
										  <!-- Table -->
										<table class="table">
										  	<tr>
										  		<th></th>
										  		<th>Date</th>
										  		<th>Order Number</th>
										  		<th>Product Name</th>
										  		<th>Amount Remaining</th>
										  	</tr>
												<c:forEach items="${customerOrderList }" var="orderList">
													<c:if test="${orderList.amountRemaining() gt 0.00}">
														<tr>
														<td><input type="radio" name="orderNumber" id="orderNumber" value="${orderList.clientOrderId }"/></td>
														<td>${orderList.convertOrderDate() }</td>
														<td>${orderList.getOrderNumber() }</td>
														<td>${orderList.getProductCode() }</td>
														<td>${orderList.amountRemaining() }</td>
														</tr>
														
													</c:if>
												</c:forEach>									
										  </table>
										  <label id="orderNumber-errorLabel" class="error"></label>
										</div>		
										<div class="col-lg-12">
										</div>
											<label>Payment Method:</label>
											<span id="paymentMethodError" class="error"></span>
											<select name="paymentMethod" class="form-state" onchange="javascript:showBankCardInfoDiv(this);">
												<option value=""></option>
												<option value="cash">Cash</option>
												<option value="check">Check (Cheque)</option>
												<option value="card">Back Issued Debit Card</option>
											</select>
												<div id="bankInfoDiv" class="hidden moveL_30">
													<h4 class="page-header">Cheque Details</h4>
		
													<label for="bankName">Name of Bank:</label> <input type="text"
														name="bankName" class="form-state width-100" id="bankName" value="" />
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
													<h4 class="page-header">Credit Card Payment</h4>
													<label>Name on Card:</label>
													<input type="text" name="nameOnCard" class="form-state  width-100">
													<label>Card Number:</label>
													<input type="text" name="cardNumber" class="form-state  width-100">
													<label>Expiration Date:
													<input type="text" class="form-state width-40" placeholder="MM/YY">
													</label>
													
													<label>Security Number:
													<input type="text" class="form-state width-40">		
													</label>
																														
												</div>		
											<div>	
											<div></div>																		
											<label for="amountPaid">
												Payment Amount:
											</label>
											</div>	
											<input type="text" name="amountPaid" id="amountPaid" onblur="javascript:formatAmount('amountPaid',this)" class="form-state" placeholder="Amount">
											<hr>

											<input type="hidden" name="customerId" value="${customer.getCustomerId() }"/>
										</sf:form>
											<p>
												<input type="submit" onclick="javascript:validatePaymentForm(); return false;" value="Submit Payment" id="btnSubmitPayment" class="btn btn-success"/>		
												<a href="#" class="btn btn-danger moveL_20">Cancel</a>	
											</p>										
								</c:when>
								<c:otherwise>
								<c:if test="${not empty searchError }">
								<div class="alert alert-info alert-dismissible fade in"  role="alert">

									<span  id="paymentMessage">
										${searchError}
									</span>							
								</div>									
								</c:if>
									<a href="#" class="btn btn-primary" id="btnContinue">Continue</a>
								</c:otherwise>
							</c:choose>

							</div>
						</div>		
						</div>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		$('#paymentMessageContainer').hide();
		$("#btnSearchOrder").click(function(){
			document.searchOrder.searchField.value = searchField;
			document.searchOrder.submit();

		});
		
		$('#btnSubmitPayment').click(function(){
			validateOrderNumberRadioBtn();			
			if(validatePaymentForm()){
				document.submitPayment.submit();
			}
			
		});
	});
	function validateOrderNumberRadioBtn(){
		if($("input[name=orderNumber]:checked").length < 1) {
			  $('#orderNumber-errorLabel').html('Select an Order/Bill to continue...');
			  $('#orderNumber-errorLabel').removeAttr('style');
			  return;
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
					
					if(response == null){
						$('#paymentMessageContainer').show();
						$("#paymentMessage").text("NO information was found. Try again with a valid Customer Information");		
					}
					$('#paymentContainer').show();
										
				},
				error: function(err){
					$('#paymentMessageContainer').show();
					$("#paymentMessage").text("Problem occured while processing your request, please try again.");
				},
				complete: function(e){
					console.log(e.statusText+" complete");
				}
			});
		}else{
			$('#paymentMessageContainer').show();
			$("#paymentMessage").text("Order Number cannot be null.");
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
					required: true
				}
			},
			messages : {
				amountPaid : {
					required: "Enter the amount that the Customer is paying",
					numbers: "Quantity can only be a whole number!!."
				},
				paymentMethod : {
					required: 'Select the Payment Method'
				}
			}
		});
		if(!$('#submitPayment').valid())
			 return false;
		 return true;			
	}

	</script>
	</body>
	</html>
