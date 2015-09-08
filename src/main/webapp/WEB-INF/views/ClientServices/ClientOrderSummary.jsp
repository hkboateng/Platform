<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
			<h1 class="line1">Customer Order Summary</h1>
			<div id="orderSummary">
				<p></p>
			</div>
			</div>
		</div>
	</div>
</body>
</html>