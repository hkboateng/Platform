<%
	
	String customerType = request.getParameter("customerType");
	String employeeInfo = (String)session.getAttribute("employeeInfo");
	pageContext.setAttribute("customerType", customerType);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<%-- End of Include page header --%>
<div id="container" class="container">
<div class="row">
	<div class="col-sm-9 col-md-9 col-lg-9">
	<h3><c:if test="${not empty customer}">${not empty customer.getCustomerName() ? customer.getCustomerName() : customer.getCompany_name()} (Account #:${customerAccount.accountNumber.toUpperCase() })</c:if></h3>
	
	<c:choose>
		<c:when test="${not empty address}">
			<div class="col-xs-12 col-md-3">
				<c:forEach items="${address}" var="addressList">
					<span class="bold">${addressList.addressType.toUpperCase()} Address:</span><br>
					${addressList.address1 }&nbsp;${addressList.address2 }
					${addressList.city }&nbsp;${addressList.region }&nbsp;${addressList.zipcode }<br>
				</c:forEach>	
			</div>
		</c:when>	 
		<c:otherwise>
			Address Information are not available this time!!!.
		</c:otherwise>	
	</c:choose>
		<div class="col-xs-12 col-md-3">
			<!-- Phone Number's -->
			<c:if test="${not empty phone}">
				<div class="bold">Phone Number(s):</div>
				<c:forEach items="${phone }" var="phone" varStatus="counter">
					<div>${phone.phoneNumber }&nbsp; </div>
				</c:forEach>
			</c:if>
		</div>		
		<div class="col-xs-12 col-md-3">
			<!-- Customer Email Address -->
			<c:if test="${not empty email}">
				<div class="bold">EmailAddress:</div>
				<c:forEach items="${email }" var="email" varStatus="counter">
					<div>${email.emailAddress} &nbsp; </div>
				</c:forEach>
			
			</c:if>	
		</div>		
	</div> 	
	<div class="col-lg-3">
		<div class="pad_10">
			<sf:form action="/abankus/customers/viewProfile" method="get">
				<label>Quick Search</label>
					<div class="spaceBelow_10">
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Name
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Account No.
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> Email
					</label>
					</div>
				<input type="text" name="customerId" class="form-state"/>
				<button type="submit" class="btn btn-success btn-sm btn-block"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
			</sf:form>
		</div>	
	</div>
	<div class="clearfix underline-div"></div>
	<%-- Account Details Information --%>
	<div class="col-sm-12 col-md-12 col-lg-12 main-container">
		<h3>Account Details</h3>
		<div>
			<label class="bold">Account Number:</label>
			<span>${customerAccount.getAccountNumber() }</span>
		</div>
		<div>
			<label class="bold">Contact Person:</label>
			<span>${customer.getContactPerson() ? customer.getContactPerson() : '<a href="#"><span class="glyphicon glyphicon-pencil"></span>Add Contact Person</a>'}</span>
		</div>
		<div>
			<label class="bold">Account Status:</label>
			<span>${customerAccount.getStatus()}</span>
		</div>		
	</div>	
	<div class="col-sm-12 col-md-6 col-lg-6 main-container">
		<h3 class="underline-div">Transaction History</h3>

<div id="orderSummary">
			<c:if test="${not empty customerOrder }">
			<table id='orderHistoryTable' class="table">
				<thead>
					<tr>
						<th></th>
						<th>Date:</th>
						<th>Product Code</th>
						<th>Total Amount Remaining</th>
						<th>Total Amount:</th>
					</tr>
				</thead>
				<tbody>
				<%-- 
				<c:forEach items="${customerOrder }" var="customerOrderList" varStatus="counter">
					<tr>
						<td id="tableId">
						  <label for="tableId">
						  <c:set value="${customerOrderList.getOrderNumber()}" var="orderNumber"/>
						  	<% String t = (String)pageContext.getAttribute("orderNumber"); %>
						    <input type="radio" name="orderNumber" id="orderNumberRd${counter.count }" value="<%=SecurityUtils.encryptOrderNumber(t) %>">
						  </label>
						</td>
						<td id="tblOrderDate${counter.count }">${customerOrderList.convertOrderDate()}</td>
						<td id="tblProductCode${counter.count }">${customerOrderList.getProductCode()}</td>
						<td>${customerOrderList.isOrderPending()}</td>
						<td>$<span  id="tblTotalAmount${counter.count }"> ${customerOrderList.getTotalAmount()}</span></td>
						<td></td>
					</tr>
				</c:forEach>	
				--%>
				<c:forEach items="${billing.getBillingMap() }" var="billings" varStatus="counter">
					<c:set value="${billings.key }" var="key" />
						<c:if test="${billing.getCustomerBilling(key).totalAmountRemaining() gt 0}">
							<tr>
								<c:set value="${billings.key }" var="key" />
								<td id="tableId">
									<input type="radio" name="orderNumber" id="orderNumber${counter.count }" value="${billing.getCustomerBilling(key).encryptOrderNumber(key) }"/>
								</td> 
								<td id="tblOrderDate${counter.count }">${billing.getCustomerBilling(key).convertOrderDate()}</td>
								<td id="tblProductCode${counter.count }">${billing.getCustomerBilling(key).getProductCode()}</td>
								<td>$<span  id="tblTotalAmount${counter.count }">${billing.getCustomerBilling(key).totalAmountRemaining() }</span></td>
								<td>$&nbsp;${billing.getCustomerBilling(key).getTotalOrderAmount()}</td>						
							</tr>						
						</c:if>

				</c:forEach>			
				</tbody>			
				<tfoot>
					<tr>
					<td colspan="6">
						<button type="button" id="makePaymentBtn" class="btn btn-primary">Make Payment</button>
					</td>
			</tr>
				</tfoot>
			</table>
			</c:if>
			<c:if test="${empty customerOrder }">
			<div class="alert alert-success" role="alert">
				<p class="bold"><i class="fa fa-info moveR_20"></i>No Order History for Customer</p>
			</div>
				
				<button type="button" class="btn btn-success center-block " onclick="javascript:pushToURL('clients/createOrder')">Add Order for Customer<i class="fa fa-chevron-right moveL_30"></i></button>
			</c:if>

			</div>
				</div>
	          <%--
			 <c:if test="${not empty customer}">
			  	<div id="" class="row ">
	 				 
	  				 <div class="col-xs-12 col-md-8">
		  				 <div class="dropdown">
							  <button class="buttonAction dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							    <i class="fa fa-bars fa-lg moveR_10"></i>
							    Menu
							  
							  </button>
							  <ul class="dropdown-menu width-100 " aria-labelledby="dropdownMenu1">
				  				 <c:choose>
				  				 	<c:when test="${customerAccount.isCustomerActive()}">
					  				 	<li>
									  		<a href="sendMessage" class=""><span class="glyphicon glyphicon-envelope moveR_20" aria-hidden="true"></span>Send Message</a>  				 	
					  				 	</li>  				 	
				  				 	</c:when>
				  				 	<c:otherwise>
					  				 	<li>
									  		<a href="sendSaleMessage" class=""><span class="glyphicon glyphicon-envelope moveR_20" aria-hidden="true"></span>Send Sales Message</a>  				 	
					  				 	</li>   				 	
				  				 	</c:otherwise>
								</c:choose>
								<li role="separator" class="divider"></li>
				  				 	<li>
								  		<a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class=""><i class="fa fa-money moveR_20"></i>Make A Payment</a>  				 	
				  				 	</li>	
				  				 	<li>
				  				 		<a href="javascript:orderHistory('orderHistory',document.formOrderHistroy);" ><i class="fa fa-exchange moveR_20"></i>View Customer Order History</a>
				  				 	</li>	
				  				 	<li role="separator" class="divider"></li>
				  				 	<li>
				  				 		<a href="javascript:createOrder('createCustomerOrder','document.createCustomerOrder');"><i class="fa fa-exchange moveR_20"></i>Add New Order</a>
				  				 	</li>	
							  </ul>
							</div>
	  
	  				 </div>		  	
	
				</div>
						  <sf:form  name="formOrderHistroy" method="post" action="">
						  	<input type="hidden" name="customerId" id="customerIdHdn"  value="${customer.customerId }"/>
						  </sf:form>
	          <sf:form name="frmUpdateAccountStatus" method="post" action="updateAccountStatus">
	          	  <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>	          
	          </sf:form>
	          <sf:form name="makeCustomerPayment" method="post" action="">
	          <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>
	          </sf:form>
				</c:if>
				 --%>
	<sf:form action="/abankus/Payments/makeCustomerOrderPayment" name="makeCustomerOrderPayment" method="post">
		<input type="hidden" name="orderNumber" id="orderNumberHdn" value=""/>
		<input type="hidden" name="totalAmount" id="totalAmountHdn"  value=""/>
		<input type="hidden" name="orderDate" id="OrderDateHdn"  value=""/>
		<input type="hidden" name="productCode" id="productCodeHdn"  value=""/>
		<input type="hidden" name="customerId" id="customerIdHdn"  value="${customerId }"/>
	</sf:form>
			<div class="hidden-sm hidden-xs col-md-3 col-lg-3" >
				
			</div>
</div>
</div>
</body>
<script>
$(document).ready(function(){
	// $('#orderHistoryTable').DataTable();
	$("#makePaymentBtn").click(function(){
		var orderNo = $("input:radio[name=orderNumber]:checked").val();
		if(validSubmit(orderNo)){
			var amount= $("#tblTotalAmount").text();
			var form = document.makeCustomerOrderPayment;
			form.orderNumber.value = orderNo;
			form.totalAmount.value = amount;
			form.orderDate.value = $("#tblOrderDate").text();
			form.productCode.value = $("#tblProductCode").text();
			form.submit();			
		}else{
			$('#orderHistoryMessage').removeClass('hidden');
			$('#orderHistoryMessage').addClass('alert alert-danger');
			$('#orderHistoryMessage').html('You must select at least one option from the list of Order');
		}

	});
});

function validSubmit(radio){
	if(radio == undefined){
		return false;
	}
	return true;
}
</script>
</html>
