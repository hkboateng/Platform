<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/platform.css" />"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.js" />"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/js/validation/jquery.validation.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/accounting/accounting.js" />"
	type="text/javascript"></script>
</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="row">
			<jsp:include page="../sidebar.jsp" />
			<div class="col-sm-9 col-md-10 col-md-offset-2 main">
				<h2>Search for Customer</h2>
				<hr class="line1" />
				<div>
					<div class="platform-form-group">
					<input type="radio" name="searchCustomer" id="searchCustomer" class="moveR_20"/>
						<label for="firstname">Customer First Name:</label> <input class="platform_form"
							type="text" name="firstname" id="firstname">
						<label	for="lastname">Customer Last Name:</label> 
						<input type="text"	name="lastname" id="lastname" class="platform_form">
					</div>
					<div class="platform-form-group">
					<input type="radio" name="searchCustomer" id="searchCustomer" class="moveR_20"/>
						<label for="accountnumber">Account Number:</label> <input class="platform_form"
							type="text" name="accountnumber" id="accountnumber">
					</div>
					<div class="platform-form-group">
					<input type="radio" name="searchCustomer" id="searchCustomer" class="moveR_20"/>
						<label for="customernumber">Customer Number:</label> <input class="platform_form"
							type="text" name="customernumber" id="customernumber">
					</div>			
					<div class="platform-form-group">
					<input type="radio" name="searchCustomer" id="searchCustomer" class="moveR_20"/>
						<label for="emailaddress">Email Address:</label> <input class="platform_form"
							type="text" name="emailaddress" id="emailaddress">			
					</div>		
					<div class="platform-form-group">
					<input type="radio" name="searchCustomer" id="searchCustomer" class="moveR_20"/>
						<label for="phonenumber">Phone Number:</label> <input class="platform_form"
							type="text" name="phonenumber" id="phonenumber">			
					</div>		
					<div class="platform-form-group">							
						<input type="submit" value="Search" class="btn btn-success"/>
					</div>														
				</div>
				<div id="resultHeading" class="resultHeading">
					<div id="resultHeading-word" class="anw">Search Results</div>
				</div>
			</div>
		</div>
	</div>