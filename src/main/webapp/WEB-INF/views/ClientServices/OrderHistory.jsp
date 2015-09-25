<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<c:import var="customerOrderUtils" url="com.boateng.abankus.customerorders.utils.CustomerOrderUtils" scope="application"/>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Sales Connection - Client Order Summary</title>
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
			<div class="col-xs-12 col-sm-12 col-md-10">
			<h1>Customer Order History</h1>
			<hr  class="line1"/>
			<div id="orderSummary">
			<c:if test="${not empty customerOrder }">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Date:</th>
						<th>Product Code</th>
						<th>Sale Pending?</th>
						<th>Total Amount:</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${customerOrder }" var="customerOrderList">
					<tr>
						<td id="tableId">
						  <label for="tableId">
						    <input type="radio" name="blankRadio" id="blankRadio1" value="${customerOrderList.getOrderNumber()  }" aria-label="...">
						  </label>
						</td>
						<td>${customerOrderList.convertOrderDate()}</td>
						<td>${customerOrderList.getProductCode()}</td>
						<td>${customerOrderList.isOrderPending()}</td>
						<td>$ ${customerOrderList.getTotalAmount()}</td>
						<td></td>
					</tr>
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
			<hr>
			<button type="button" class="btn btn-primary">Make Payment</button>
			</div>
			</div>
		</div>
	</div>
</body>
</html>