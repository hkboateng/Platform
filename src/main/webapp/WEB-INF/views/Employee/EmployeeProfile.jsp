<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Payments - Employee Profile</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div id="container" class="container">
<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 main">
          	<div class="page-header">
          		<h1>Hi, <small>${employeeInstance}</small></h1>
          	</div>
          	<div id="employeeProfileDiv">
          	<div class="lead page-header">Staff Info</div>
          		<p>
          			<label class="label labelLength_10">Employee Number:</label>
          			<span>${employeeInstance.getEmployeeId()}</span>
          		</p>
          		<label class="label labelLength_30">Address</label>
          		<div class="moveL_5 spaceBelow_10">
          			<div>${employeeInstance.getAddress1() }</div>
          			${employeeInstance.getAddress2() }
          			<div>${employeeInstance.getCity() }&nbsp;${employeeInstance.getState() }&nbsp;${employeeInstance.getZipcode() }</div>
          		</div>
          		
          		<p><span class="label labelLength_10">Phone Number:</span><span>${employeeInstance.getPhoneNumber() }</span></p>
          		
          		<p><span class="label labelLength_10">Email Address:</span><span>${employeeInstance.getEmailAddress() }</span></p>
          	</div>
          	<div class="spaceBelow_20">
          		<hr>
          	</div>
          	<div class="">
          		<div class="lead page-header">Company Info</div>
          		<p><span class="labelLength_20 label">Company Name:</span></p>
          		<p><span class="labelLength_20 label">Company Address:</span></p>
          		<p><span class="labelLength_20 label">Industry:</span></p>
          	</div>
		</div>        
</div>
</div>
</body>

</html>