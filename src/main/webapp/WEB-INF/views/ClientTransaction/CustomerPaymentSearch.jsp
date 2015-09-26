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
		<script src="<c:url value="/resources/js/accounting/accounting.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/jquery-masked.js" />" type="text/javascript"></script>
	</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="row">
			<jsp:include page="../sidebar.jsp" />
			<div class="col-sm-9 col-md-10 col-md-offset-2 main">
			<h1> Make A Payment</h1>
			<hr class="line1">
						<div class="paymentContainer">
						<div class="platform-alert" id="paymentMessage">
						
						</div>
						<div class='spaceBelow_20'>
							<p>Search for Customer's Order:</p>
							<label class="sr-only">Order Number:</label>
    						<input type="text" name="searchOrderId" id="searchOrderId"  class="custom-text" placeholder="Order Number"/>
    						<button class="btn btn-success" value="Search" id="btnSearchOrder"><i class="fa fa-search"></i></button>
    						<hr>
						</div>	
						<c:choose>
						<c:when test="${not empty orderList }">

							<c:forEach items="${orderList}" var="order">
								<%@ include  file="IncludeCustomerPayment.jsp"%>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="alert alert-success" role="alert">
								<p>No Order Information was found for the Order Number you entered.</p>
							</div>						
						</c:otherwise>		
						</c:choose>			
						</div>
			</div>
		</div>
	</div>
	<sf:form name="searchOrder" method="post" action="/abankus/order/findOrderByOrderNumber">
		<input type="hidden" name="orderNumber" id="OrderNumber" value=""/>
	</sf:form>
	<script>
	$(document).ready(function(){

		$("#btnSearchOrder").click(function(){
			findOrderByOrderNumber();
		});
	});
	function findOrderByOrderNumber(){
		var number = $("#searchOrderId").val();
		if(!isEmpty(number)){
			document.searchOrder.orderNumber.value=number;
			document.searchOrder.submit();
		}else{
			$("#paymentMessage").text("Order Number cannot be null.");
		}
	}
	</script>
	</body>
	</html>
