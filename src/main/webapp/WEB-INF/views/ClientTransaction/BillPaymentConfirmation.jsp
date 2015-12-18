<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Corporation - Payment Confirmation</title>
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
			<div class="col-sm-4 col-md-2 col-lg-2">
				<jsp:include page="../sidebar.jsp" />
			</div>
			<div class="col-sm-8 col-md-10 col-lg-10 main">
				<c:choose>
					<c:when test="${not empty paymentTransaction }">
						<c:set value="${paymentTransaction.getOrderNumber()}" var="orderNumber"/>
						<div class="col-sm-8 col-md-10 col-lg-10 main">
						<div class="page-header">
							<h2>Confirmation</h2>
						</div>
						<div class="width-100">
							<label>Confirmation Number:</label>
							<span>${paymentTransaction.getTransactionNumber()}</span>
						</div>
						<div class="width-100">
							<label>Amount Paid:</label>
							<span>${paymentTransaction.getAmountPaid() }</span>
						</div>
						<div class="width-100">
							<label>Bill/Order Number:</label>
							<span>${orderNumber}</span>
						</div>				
						<div class="width-100">
							<label>Payment Date:</label>
							<span>${paymentTransaction.getPaymentDate() }</span>
						</div>				
						<div class="width-100">
							<label>Payment Type:</label>
							<span>${paymentTransaction.getPaymentType().toUpperCase() }</span>
						</div>	
						</div>
						<div class="col-sm-8 col-md-3 col-lg-2 main">
							<div class="list-group bg-primary">
								<a href="<c:url value="/payment/receipts" />" class="list-group-item">
									<i class="glyphicon glyphicon-cog moveR_20"></i>Email Receipt
								</a>
								<a href="<c:url value="//payment/receipts"/>" class="list-group-item">
								  <i class="glyphicon glyphicon-off moveR_20"></i>Print Receipt
								</a>				
							</div>							
						</div>	
						<input type="hidden" value="${orderNumber}"	name="orderNumber" />	
					</c:when>
					<c:otherwise>
						<div>Something awful happen...</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>