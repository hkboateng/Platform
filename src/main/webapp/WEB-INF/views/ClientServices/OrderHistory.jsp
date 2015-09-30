<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.boateng.abankus.utils.SecurityUtils"  %>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Sales Connection - Client Order Summary</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
		<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
		<link href="<c:url value="/resources/css/tables/jquery.dataTables.css" />" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
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
			<table id='orderHistoryTable' class="table">
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
		</div>
	</div>
	<sf:form action="/abankus/Payments/makeCustomerOrderPayment" name="makeCustomerOrderPayment" method="post">
		<input type="hidden" name="orderNumber" id="orderNumberHdn" value=""/>
		<input type="hidden" name="totalAmount" id="totalAmountHdn"  value=""/>
		<input type="hidden" name="orderDate" id="OrderDateHdn"  value=""/>
		<input type="hidden" name="productCode" id="productCodeHdn"  value=""/>
		<input type="hidden" name="customerId" id="customerIdHdn"  value="${customerId }"/>
	</sf:form>
</body>
<script>
$(document).ready(function(){
	 $('#orderHistoryTable').DataTable();
	$("#makePaymentBtn").click(function(){
		var orderNo = $("input:radio[name=orderNumber]:checked").val();
		var amount= $("#tblTotalAmount").text();
		var form = document.makeCustomerOrderPayment;
		form.orderNumber.value = orderNo;
		form.totalAmount.value = amount;
		form.orderDate.value = $("#tblOrderDate").text();
		form.productCode.value = $("#tblProductCode").text();
		form.submit();
	});
});
</script>
</html>