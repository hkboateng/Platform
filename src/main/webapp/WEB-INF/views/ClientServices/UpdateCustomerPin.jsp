<%
	
	String customerType = request.getParameter("customerType");
	String employeeInfo = (String)session.getAttribute("employeeInfo");
	pageContext.setAttribute("customerType", customerType);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-masked.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/customerValidation.js" />" type="text/javascript"></script>
</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp"/>
		<div  id="container" class="container">
			<div class="page-header">
				<h2>Update Customer PIN</h2>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<label for="customerNumber" class="labelLength_20">Customer Number:</label>
					<input name="customerNumber" id="customerId" type="text" class="form-state-inline"/>
					<hr>
					<input type="submit" value="Send Update Request" class="btn btn-success"/>
				</div>
			</div>
	</div>
</body>

<script>
$(document).ready(function(){

});

</script>
</html>