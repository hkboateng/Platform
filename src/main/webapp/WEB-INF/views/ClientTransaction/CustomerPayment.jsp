<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<c:import var="platform" url="com.boateng.abankus.utils.PlatformUtils"/>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Corporation - Make a Payment</title>
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
						<c:choose>
							<c:when test="${not empty orderInstance }">
							
							</c:when>
							<c:otherwise>
							<div class="alert alert-success" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								  <span aria-hidden="true">&times;</span>
								</button>							
								No Order Information is avaialble.
							</div>
							<div class='spaceBelow_20'>
								<label class="sr-only">Order Number:</label>
	    						<input type="text" name="searchOrderId" id="searchOrderId"  class="custom-text" placeholder="Order Number"/>
	    						<button class="btn btn-primary" value="Search"><i class="fa fa-search" onclick="javascript:findCustomerOrder(document.searchOrderId);"></i></button>
							</div>								
							</c:otherwise>
						</c:choose>
						</div>
					
						</div>
			</div>
		</div>
	</div>
	
	</body>
	</html>