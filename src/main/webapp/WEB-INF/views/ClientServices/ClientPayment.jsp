<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Corporation - Make a Payment</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/platform.css" />"	rel="stylesheet" />
		<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>
		<script	src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/bootstrap.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/accounting/accounting.js" />" 	type="text/javascript"></script>
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
				<div id="paymentContainer">
				<div>
					<p></p>
				</div>
				<hr>
				<button class="btn btn-success" id="paymentSummaryBtn" >Submit Payment</button>
				</div>
			</div>
		</div>
	</div>