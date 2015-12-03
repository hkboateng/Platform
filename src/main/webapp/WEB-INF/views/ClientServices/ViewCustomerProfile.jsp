
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
	<h3>
		<c:if test="${not empty customer}">
		${not empty customer.getCustomerName() ? customer.getCustomerName() : customer.getCompany_name()} (Account #:${customerAccount.customer.customerNumber.toUpperCase() })
		</c:if>
	</h3>
		<c:choose>
			<c:when test="${not empty address}">
				<div class="col-xs-12 col-md-4">
					<c:forEach items="${address}" var="addressList">
						<div class="bold">${addressList.addressType.toUpperCase()} Address:</div>
						<div>${addressList.address1 }&nbsp;${addressList.address2 }</div>
						<div>${addressList.city }&nbsp;${addressList.region }&nbsp;${addressList.zipcode }</div>
					</c:forEach>	
				</div>
			</c:when>	 
			<c:otherwise>
				Address Information are not available this time!!!.
			</c:otherwise>	
		</c:choose>
	<div class="col-xs-12 col-md-4">
		<!-- Phone Number's -->
		<c:if test="${not empty phone}">
			<div class="bold">Phone Number(s):</div>
			<c:forEach items="${phone }" var="phone" varStatus="counter">
				<div><label>${phone.getPhoneType() ? 'Primary Phone:' :phone.phoneType()  }:&nbsp;</label>${phone.getPhoneNumber() }</div>
			</c:forEach>
		</c:if>
	</div>		
	<div class="col-xs-12 col-md-4">
		<!-- Customer Email Address -->
		<c:if test="${not empty email}">
			<div class="bold">EmailAddress:</div>
			<c:forEach items="${email }" var="email" varStatus="counter">
				<div>${email.emailAddress} &nbsp; </div>
			</c:forEach>
			
		</c:if>	
	</div>		
</div> 	
	
<div class="clearfix underline-div"></div>
	<%-- Account Details Information --%>
<div class="col-sm-12 col-md-8 col-lg-8 main-container">
		<h3>Account Details</h3>
		<div>
			<label class="bold">Account Number:</label>
			<span>${customerAccount.getAccountNumber() }</span>
		</div>
		<div>
			<label class="bold">Contact Person:</label>
			<span>${(person != null)? person : '<a href="/abankus/customers/addCustomerContactPerson"><span class="glyphicon glyphicon-pencil"></span>Add Contact Person</a>'}</span>
		</div>
		<div>
			<label class="bold">Account Status:</label>
			<span>${customerAccount.getStatus()}</span>
		</div>		

		<h3 class="underline-div">Transaction History - Open Orders</h3>

		<div id="orderSummary">
			<c:if test="${not empty customerOrder }">
			<table id='orderHistoryTable' class="table">
				<thead>
					<tr>
						<th>Order Date:</th>
						<th>Balance</th>
						<th>Total Amount:</th>
						<th>View Detail</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${billing.getBillingMap() }" var="billings" varStatus="counter">
					<c:set value="${billings.key }" var="key" />
					<c:set value="${billing.getCustomerBilling(key).encryptOrderNumber(key) }" var="keySec" />
						<c:if test="${billing.getCustomerBilling(key).totalAmountRemaining() gt 0}">
							<tr>
								<c:set value="${billings.key }" var="key" />
								<td id="tblOrderDate${counter.count }">${billing.getCustomerBilling(key).convertOrderDate()}</td>
								<td>$<span  id="tblTotalAmount${counter.count }">${billing.getCustomerBilling(key).totalAmountRemaining() }</span></td>
								<td>$&nbsp;${billing.getCustomerBilling(key).getTotalOrderAmount()}</td>		
								<td id="tblProductCode${counter.count }"><a onclick="javascript:submitURLForm(document.viewTransactionDetail,'${keySec }')" href="#">View Order</a></td>
								<td><button type="button" id="makePaymentBtn" onclick="javascript:submitCustomerPayment('${keySec }','${billing.getCustomerBilling(key).getTotalOrderAmount()}');"  class="btn btn-primary btn-sm">Make Payment</button></td>				
							</tr>						
						</c:if>

				</c:forEach>			
				</tbody>	
			</table>
			</c:if>
			<c:if test="${empty customerOrder }">
			<div class="alert alert-success" role="alert">
				<p class="bold"><i class="fa fa-info moveR_20"></i>No Order History for Customer</p>
			</div>
				<button type="button" class="btn btn-success center-block " onclick="javascript:pushToURL('clients/createOrder')">Add Order for Customer<i class="fa fa-chevron-right moveL_30"></i></button>
			</c:if>
			</div>
			<h3 class="underline-div"> Closed or Paid Orders</h3>
			<div>
				<c:choose>
				<c:when test="${not empty customerOrder }">
				<table id='orderHistoryClosed' class="table">
					<thead>
						<tr>
							<th></th>
							<th>Order Date:</th>
							<th>Order Number</th>
							<th>Total Amount Remaining</th>
							<th>Total Amount</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${billing.getBillingMap() }" var="billings" varStatus="counter">
						<c:set value="${billings.key }" var="key" />
							<c:if test="${billing.getCustomerBilling(key).finishPaying()}">
								<tr>
									<c:set value="${billings.key }" var="key" />
									<td id="tableId">
										<input type="radio" name="orderNumber" id="orderNumber${counter.count }" value="${billing.getCustomerBilling(key).encryptOrderNumber(key) }"/>
									</td> 
									<td id="tblOrderDate${counter.count }">${billing.getCustomerBilling(key).convertOrderDate()}</td>
									<td id="tblProductCode${counter.count }">${billing.getCustomerBilling(key).getProductCode()}</td>
									<td>$<span  id="tblTotalAmount${counter.count }">${billing.getCustomerBilling(key).totalAmountRemaining() }</span></td>
									<td>$&nbsp;${billing.getCustomerBilling(key).getTotalOrderAmount()}</td>	
									<td><button type="button" class="btn btn-success center-block " onclick="javascript:pushToURL('clients/createOrder')">Add Order for Customer<i class="fa fa-chevron-right moveL_30"></i></button></td>					
								</tr>						
							</c:if>
	
					</c:forEach>			
					</tbody>
				</table>
				</c:when>	
				<c:otherwise>
			<div class="alert alert-success" role="alert">
				<p class="bold"><i class="fa fa-info moveR_20"></i>No Order History for Customer</p>
			</div>
				
				<button type="button" class="btn btn-success center-block " onclick="javascript:pushToURL('clients/createOrder')">Add Order for Customer<i class="fa fa-chevron-right moveL_30"></i></button>
			</c:otherwise>					
			</c:choose>	
			</div>
</div>
<div class="col-sm-12 col-md-4 col-lg-4 main-container">
<div class="list-group">
<a href="javascript:document.createOrderForm.submit();" class="list-group-item">Create New Order<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
</div>
<jsp:include page="../sidebar.jsp"/>
</div>	
<sf:form name="createOrderForm" method="post" action="/abankus/customer/createOrders">
	<input type="hidden" name="accountNumber" value="${customerAccount.getAccountNumber() }"/>
</sf:form>
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
	<sf:form name="viewTransactionDetail" method="post" action="/abankus/platform/viewTransactionDetail">
		<input type="hidden" value="" name="orderNumber"/>	          
	</sf:form>				 
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

	
});

function validSubmit(radio){
	if(radio == undefined){
		return false;
	}
	return true;
}

function submitCustomerPayment(orderNo,amount){
	var form = document.makeCustomerOrderPayment;
	form.orderNumber.value = orderNo;
	form.totalAmount.value = amount;
	form.submit();	
}
function submitURLForm(form,val){
	form.orderNumber.value = val;
	form.submit();
}
</script>
</html>
