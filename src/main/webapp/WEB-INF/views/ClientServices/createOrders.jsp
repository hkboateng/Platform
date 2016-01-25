<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/accounting/accounting.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div  id="container" class="container">
<div class="row">
		<div class="col-xs-12 col-sm-10 col-md-8">

          <h1>Create Orders</h1>
			<hr>
				<c:if test="${not empty validation }">
					<div>
						<ul>
							<c:forEach items="${validation}" var="errors">
								<li>${errors }</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>			
			<%-- Description of what this page is for. --%>
			<div>
			The Create Orders page, is the Page that Sales Reps or Consultants will use to create an Order for Customer. The order can be a sale of a product or services.
			When an order is created, a Bill of Invoice will also be created for the Customer in which the customer can use the invoice to make payments
			</div>
			<hr>
				<div class="form-group">
					<span>Search:</span>
					<label for="customerAccount" class="sr-only">Customer Account Number:</label>
					<input type="text" name="accountNumber" id="accountNumber" class="custom-text" placeholder="Enter Account Number, Customer Number"/>
					<button  id="btnSearchCustomer" class="btn btn-success" title="Search for Customer using Customer AccountNumber" ><i class="fa fa-search"></i></button>								
				</div>
				
				<c:set var="formState" value="${not empty account ? '' : 'hidden' }"/>	
				  <div id="resultHeading" class="resultHeading ${formState}">
		          	<div id="resultHeading-word" class="anw">Search Results</div>
		          </div>	
	
				 <div id="pending">	
		          		
				 </div>			

			<sf:form class="forms ${formState}" method="post"  action="" name="customerOrderForm" id="customerOrderForm">
				  <div class="page-header">
			        <h3 class="bold" id="page-title">Select and Continue</h3>
			      </div>
			<div id="customerOrderDiv" class="">
			
			  <div>
					<div id="clientName">
						<label class="bold labelLength_20">Client Name:</label><span id="clientFullName">${account.customer.firstname}&nbsp;${account.customer.lastname}</span>
					</div>
					<div id="clinetAccountStatus">
						<label class="bold labelLength_20">Account Status:</label>
						<span id="accountStatus">${account.status }</span>
					</div>
						<c:if test="${not empty productList}" >
								<label for="product" class=" labelLength_20 bold">Product/Services:</label><span id="product-error" class="hidden"></span>
									<select name="product" id="product" class="form-state-inline"  onchange="javascript:getProductCode(this);" onblur="javascript:getProductCode(this);">
										<option value=" ">Select Product/Service</option>
										<c:forEach items="${productList}" var="products" varStatus="list">
											<option value="${products.productCode }" title="${products.description }">${products.productName }</option>
										</c:forEach>
									</select>	

							
							<p>
								<label class="bold labelLength_20">Product Description:</label><span id="productDescription"></span>
							</p>
								<div id="serviceContainer">
									<label class="bold labelLength_20">Payment Schedule:</label>
									<select class="form-state-inline" id="paymentSchedule" name="paymentSchedule">
										<option value="">Select a Payment Schedule</option>
										<option value="payment">One Time</option>
										<option value="daily">Daily (Everyday)</option>
										<option value="monthly">Monthly</option>
									</select>
									<div>
										<label class="bold labelLength_20">Amount:</label>
										<input type="text" class="form-state-inline" id="serviceAmount" name="serviceAmount"/>										
									</div>

								</div>
								<div id="productContainer">
									<p>
										<label class="bold labelLength_20">Product Cost (Unit Cost):</label>&nbsp;<span id="unitCost"></span>
									</p>
									<p>
										<label class="bold labelLength_20">Quantity:</label><input type="number" name="quantity" id="productQuantity" maxLength="3" min="1" max="100" value="1"/>
									</p>								
								</div>
				
						</c:if>
			
			</div>
						<hr />
							<input type="hidden" name="xUnitCost" value="" id="xUnitCost"/>		
							<input type="hidden" name="customerId" value="${account.customer.customerId }" id="customerId"/>
							<input type="hidden" name="productCategory" value="" id="productCategory"/>
							<a href="<c:url value="/platform/index"/>" class="btn btn-primary moveR_20">Cancel Transaction</a>	
							<button type="button"  class="btn btn-success" id="showOrderSummary" >Continue to payment</button>
				
			
			</div>

			</sf:form>
<!-- Customer Order Summary Modal -->

<div id="customerSummaryModal">

      <div class="">
      	<p class="lead">You agree to make or pay <span id="summaryCustomerPaymentSchedule" class="bold"> </span> payment(s) of <span id="summaryCustomerTotalAmount" class="bold"></span>.</p> 
        <p><label class="bold  labelLength_20">Account Status:</label><span id="summaryCustomerStatus"></span></p>
        <p><label class="bold  labelLength_20">Product Information:</label><span id="summaryCustomerProduct"></span></p>
        <p><label class="bold  labelLength_20">Quantity:</label><span id="summaryCustomerQuantity"></span></p>
        <p><label class="bold  labelLength_20">Cost:</label><span id="summaryCustomerUnitCost"></span></p>
        <p><label class="bold  labelLength_20">Submission Date:</label><span id="submissionDate"></span></p>
       
      </div>
      <div class="">
        <button type="button" class="btn btn-default" id="editOrderBtn">Edit</button>
        <button type="button" class="btn btn-success" id="submitCustomerOrder" onclick="javascript:submitForm(document.customerOrderForm,'submitCustomerOrder')" onsubmit="return false">Submit Order</button>
      </div>
</div>

<!-- End Customer Order Summary -->			
		</div>  
		<div class="hidden-sm hidden-xs col-md-4 col-lg-4">
		<jsp:include page="../sidebar.jsp"/>
		</div>      
</div>
</div>
</body>

<script>
function submitForm(form,action){
	var action = action;
	form.action = action;
	form.submit();
}
$(document).ready(function(){
	var noOfItems = $("#noOfItems").text();

	$('#productContainer').show();
	$('#serviceContainer').hide();
	$('#customerSummaryModal').hide();

	$('#editOrderBtn').on('click',function(){
		$('#customerOrderDiv').show();
		$('#customerSummaryModal').hide();		
	});
 	$("#showOrderSummary").click(function(){
 		showOrderSummary();
 	});
 	
 	$("#submitCustomerOrder").click(function(){
 		var form = document.customerOrderForm;
 		var action = "/abankus/order/submitCustomerOrder";
 		submitForm(form,action);
 		return false;
 	});
});
function showOrderSummary(){
	var quantity = parseFloat($("#productQuantity").val());
	var product = $("#product  option:selected").text();
	var productDescription= $("#productDescription").text();
	var unitCost = $("#unitCost").text();
	var xUnitCost = $("#xUnitCost").val();
	var accountStatus = $("#accountStatus").text();
	var totalCost = (parseFloat(xUnitCost)* quantity);
	var serviceAmount = $("#serviceAmount").val();
	var paymentSchedule = $("#paymentSchedule").val();
	
	if(OrderValidation()){
		$('#page-title').html("Review and Submit")
		$('#customerSummaryModal').show();
		$('#customerOrderDiv').hide();
		$("#summaryCustomerProduct").text(product);
		$("#summaryCustomerQuantity").text(quantity);
		
		$("#summaryCustomerStatus").text(accountStatus);
		
		if($('#productCategory').val() === "Product"){
			$("#summaryCustomerTotalAmount").text(accounting.formatMoney(totalCost));
			$("#summaryCustomerUnitCost").text(accounting.formatMoney(unitCost));
			 $("#summaryCustomerPaymentSchedule").text("Monthly");
		}else{
			$("#summaryCustomerTotalAmount").text(accounting.formatMoney(serviceAmount));
			 $("#summaryCustomerPaymentSchedule").text(toTitleCase(paymentSchedule));
			$("#summaryCustomerUnitCost").text(accounting.formatMoney(serviceAmount));
		}
		populateForm();
	}
}

function populateForm(){
	var date = new Date();
	$('#submissionDate').text(date);
}
function OrderValidation(){
	var product = $("#product").val();
	if(product == "" || product == " "){
		$("#product-error").addClass("help-text-inline-error");
		$("#product-error").text("Select a Product!!!!")
		$("#product").focus();
		return;
	}else{
		$("#product-error").removeClass("help-text-inline-error");
		$("#product-error").text(" ")		
	}
	$("form").validate({
		onsubmit:false,
		rules : {
			quantity: {
				required :true,
				digits:true
			}
		},
		messages : {
			quantity : {
				required: "Product Quantity is required. Enter a valid Quantity.",
				digits: "Quantity can only be a whole number!!."
			}
		}
	});
	if(!$('form').valid())
		 return false;
	 return true;		
}
function getProductCode(form){
	var productCode = $("#product").val();
	if(productCode == " "){
		$("#productDescription").text("");
		$("#unitCost").text("");	
		$("#productCode").val("");
	}else{
		$("#productCode").val(productCode);
		
		$.ajax({
			url: 'getProductDetails',
			data: {
				productCode:productCode
			},
			dataType: "json",
			success: function(results){
				console.log(results);
				$("#productDescription").text(results.description);
				$("#unitCost").text(accounting.formatMoney(results.unitCost));
				$("#xUnitCost").val(results.unitCost);
				showContainer(results.category);
			},
			error :function(data){
				console.log(data.responseText);
			}
		});	
	}
}

function showContainer(cat){

	if(cat == "Product"){
		$('#productContainer').show();
		$('#serviceContainer').hide();		
	}else if(cat == "Service"){
		$('#productContainer').hide();
		$('#serviceContainer').show();
		
	}
	$('#productCategory').val(cat);
}
function isQuantityValid(quantity){
	var valid = validQuantity(quantity.value);
	if(valid){
		$("#quantity-error").text("");
	}else{
		$("#quantity-error").text("Quantity can only be a valid whole number. ");
	}
}
function validQuantity(value){
	var pattern = new RegExp("^\\d+$");

	return pattern.test(value);

}




function populateCart(results){
	$("#noOfItems").text(results);
}


</script>

</html>