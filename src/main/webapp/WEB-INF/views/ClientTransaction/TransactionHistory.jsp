<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Corporation - Transaction History</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/platform.css" />"	rel="stylesheet" />
		<link href="<c:url value="/resources/css/tables/jquery.dataTables.css" />" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/bootstrap.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
	</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="row">
			
			<div class="col-sm-9 col-md-9 col-lg-9">
			<h2 class="underline">Transaction Details</h2>
			</div>
			<div class="col-sm-9 col-md-3 col-md-3">
				<jsp:include page="../sidebar.jsp" />
			</div>			
		</div>
	</div>
	<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		
	});
	</script>
	</body>
	</html>
