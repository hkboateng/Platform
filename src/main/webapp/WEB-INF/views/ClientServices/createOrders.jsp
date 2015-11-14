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
					<button  id="btnSarchCustomer" class="btn btn-success" title="Search for Customer using Customer AccountNumber" ><i class="fa fa-search"></i></button>								
				</div>
				<div class="row">
				  <div id="resultHeading" class="resultHeading hidden">
		          	<div id="resultHeading-word" class="anw">Search Results</div>
		          </div>	
		         </div>			
				 <div id="pending">	
		          		
				 </div>			
			<c:set var="formState" value="${not empty account ? '' : 'hidden' }"/>	
			<sf:form class="forms ${formState}" method="post"  action="" name="customerOrderForm" id="customerOrderForm">
			<div class="">

			  <div class="row">
					<div id="clientName">
						<p><b>Client Name:</b><span id="clientFullName">${account.customer.firstname}&nbsp;${account.customer.lastname}</span></p>
					</div>
					<div id="clinetAccountStatus">
						<label>Account Status:</label><span id="accountStatus">${account.status }</span>
					</div>
					<c:if test="${not empty productList}" >

							<label for="product">Product/Services:</label><span id="product-error" class="help-text-inline-error"></span>
							
							<select name="product" id="product" class="form-state"  onchange="javascript:getProductCode(this);" onblur="javascript:getProductCode(this);">
								<option value=" ">Select Product/Service </option>
								<c:forEach items="${productList}" var="products" varStatus="list">
									<option value="${products.productCode }" title="${products.description }">${products.productName }</option>
								</c:forEach>
							</select>
	
						<div>
						<p>
							<b>Product Description:</b>&nbsp;<span id="productDescription"></span>
						</p>
						<p>
							<b>Product Cost (Unit Cost):</b>&nbsp;<span id="unitCost"></span>
						</p>
						<p>
						<label class="bold">Quantity:</label><input type="number" name="quantity" id="productQuantity" maxLength="3" min="1" max="100" value="1"/>
						</p>
						</div>					
						</c:if>
				
			<hr />
			<p>

			<input type="hidden" name="xUnitCost" value="" id="xUnitCost"/>		
			<input type="hidden" name="customerId" value="${account.customer.customerId }" id="customerId"/>
			<a href="/abankus/platform/dashboard" class="btn btn-primary moveR_20">Cancel Transaction</a>	
			<button type="button"  class="btn btn-success" id="showOrderSummary" >Continue to payment</button>
			
			</p>					
			  </div>
			</div>

			</sf:form>
		</div>  
		<div class="hidden-sm hidden-xs col-md-4 col-lg-4">
		<jsp:include page="../sidebar.jsp"/>
		</div>      
</div>
</div>
</body>
<!-- Customer Order Summary Modal -->
<div>
<div class="modal fade"  id="customerSummaryModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Order Summary</h4>
      </div>
      <div class="modal-body">
        <p>Customer Number: <span id="summaryCustomerNo"></span></p>
        <p><b>Account Status:</b><span id="summaryCustomerStatus"></span></p>
        <p><b>Product Information:</b><span id="summaryCustomerProduct"></span></p>
        <p><b>Quantity:</b><span id="summaryCustomerQuantity"></span></p>
        <p><b>Unit Cost:</b><span id="summaryCustomerUnitCost"></span></p>
        <p><b>Total Cost:</b><span id="summaryCustomerTotalAmount"></span></p>
        <hr />
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Edit</button>
        <button type="button" class="btn btn-success" id="submitCustomerOrder" onclick="javascript:submitForm(document.customerOrderForm,'submitCustomerOrder')" onsubmit="return false">Submit Order</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</div>

<!-- End Customer Order Summary -->
<script>
function submitForm(form,action){
	var action = action;
	form.action = action;
	form.submit();
}
$(document).ready(function(){
	var noOfItems = $("#noOfItems").text();
	getChartItemNumber();
 	$("#showOrderSummary").click(function(){
 		showOrderSummary();
 	});
 	
 	$("#submitCustomerOrder").click(function(){
 		var form = document.customerOrderForm;
 		var action = "submitCustomerOrder";
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

	if(OrderValidation()){
		$("#customerSummaryModal").modal({
			backdrop:"static"
		});
		$("#summaryCustomerNo").text();
		$("#summaryCustomerProduct").text(product);
		$("#summaryCustomerQuantity").text(quantity);
		$("#summaryCustomerUnitCost").text(accounting.formatMoney(unitCost));
		$("#summaryCustomerStatus").text(accountStatus);
		$("#summaryCustomerTotalAmount").text(accounting.formatMoney(totalCost));
	}
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
			},
			error :function(data){
				console.log(data.responseText);
			}
		});	
	}
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

function addProductToChart(){
	var noOfItems = $("#noOfItems").text();

	var productCode = $("#productCode").val();
	
	var customerAccount = $("#customerAccount").val();
	console.log(productCode);
	$.ajax({
		url: 'addProductToCart',
		data: {
			productCode:productCode, customerAccount:customerAccount
		},
		dataType: "json",
		success: function(results){
			populateCart(results);
		},
		error :function(data){
			console.log(data.responseText);
		}
	});			
}

function getChartItemNumber(){
	$.ajax({
		url: 'getNumberInChart',
		dataType: "json",
		cache: false,
		type: 'GET',
		success: function(results){
			populateCart(results);
		},
		error :function(data){
			console.log(data.responseText);
		}
	});			
}
function populateCart(results){
	$("#noOfItems").text(results);
}


</script>

</html>