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
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div class="container">
<div class="row">

<jsp:include page="../sidebar.jsp"/>
 
		<div class="col-sm-9 col-md-10 col-md-offset-2 main">
          <h1>Create Orders</h1>
			<hr>
			<%-- Description of what this page is for. --%>
			<div>
			The Create Orders page, is the Page that Sales Reps or Consultants will use to create an Order for Customer. The order can be a sale of a product or services.
			When an order is created, a Bill of Invoice will also be created for the Customer in which the customer can use the invoice to make payments
			</div>
				<div class="form-group">
					<span>Search:</span>
					<label for="customerAccount" class="sr-only">Customer Account Number:</label>
					<input type="text" name="accountNumber" id="accountNumber" class="custom-text" placeholder="Enter Account Number, Customer Number"/>
					<button  id="btnSarchCustomer" class="btn btn-success" title="Search for Customer using Customer AccountNumber" ><i class="fa fa-search"></i></button>								
				</div>

			<hr>
				<div id="pending">
				
				</div>			
			<form class="forms" method="post" action="" name="customerOrderForm">
			<div class="panel panel-success">
			<div class="panel-heading">Customer Order <span class="pull-right"><span class=" bold ">Cart:</span> <span id="noOfItems"></span></span></div>
			  <div class="panel-body">
					<div id="clientName">
						<label for="clientName">Client Name:</label><span id="clientFullName"></span>
					</div>
					<div id="clinetAccountStatus">
						<label>Account Status:</label><span id="accountStatus"></span>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 " id="productList">
						<label>Product/Services:</label>
						<c:if test="${not empty productList}" >
							<table class="table table-striped">
								<tr>
									<th>No.</th>
									<th>Product Name</th>
									<th>Product Description</th>
									
									<th class="text-nowrap">Product Cost</th>
									<th>Quantity</th>
									<th></th>
								</tr>
								<c:forEach items="${productList}" var="products" varStatus="list">
								
								<tr>
									<td>${list.count }</td>
									<td class="text-nowrap">${products.productName }</td>
									<td>${products.description }</td>
									<td class="text-nowrap">${products.unitCost }</td>
									<td><input type="number" name="quantity" id="quantity" maxLength="1" min="1" max="100" value="1"/></td>
									<td><a href="#" onclick="addProductToChart();" class="btn btn-success">Add to Cart</a></td>
									
								</tr>
								<input type="hidden" name="productCode" value="${products.productCode}" id="productCode"/>
								</c:forEach>
							</table>						
						</c:if>

						</div>					
					</div>					
			<hr />
			<p>
			<input type="submit"  class="btn btn-success" onclick="javascript:continueToOrderPayment(document.customerOrderForm);" value="Continue to payment"/>
			</p>					
			  </div>
			</div>

			</form>
		</div>        
</div>
</div>
</body>
<script>
$(document).ready(function(){
	var noOfItems = $("#noOfItems").text();
	getChartItemNumber();

});
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
	$.ajax({
		url: 'addProductToCart',
		data: {
			productCode:productCode
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

function continueToOrderPayment(form){

			var productCode = $("#productCode").val();
			var quantity =  $("#quantity").val();

	$.ajax({
		url: 'getNumberInChart',
		data: {	productCode : productCode,quantity: quantity	},
		cache: false,
		dataType: "json",
		success: function(results){
			populateCart(results);
		},
		error :function(data){
			console.log(data.responseText);
		}
	});		
}
</script>

</html>