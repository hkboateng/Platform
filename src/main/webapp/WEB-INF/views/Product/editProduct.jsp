<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
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
<%--
<jsp:include page="../sidebar.jsp"/>
 --%>
		<div class="col-sm-9 col-md-10 main">
          <h1>Create Orders</h1>
			<hr>
				<div class="form-group">
					<span>Account Number:</span>
					<label for="customerAccount" class="sr-only">Customer Account Number:</label>
					<input type="text" name="accountNumber" id="accountNumber" class="custom-text" placeholder="Enter Account Number"/>
					<button  id="btnSarchCustomer" class="btn btn-success" title="Search for Customer using Customer AccountNumber" ><i class="fa fa-search"></i></button>								
				</div>
				<div id="pending">
				
				</div>
			<hr>
			
			<form method="POST" action="<c:url value="updateProducts"/>">
					<label for="productname">Product Name:</label>
					<input type="text" class="form-control" id="productname" name="productName"/>
					<label for="productcode">Product Code:</label><span id="helpBlock" class="help-text-inline">Maximum length is 8 charaters.</span>	
					<input type="text" class="form-control width-20" id="productcode" name="productCode" maxLength="8" disabled/>
					<sf:errors path="*"/>	
					<label for="productDesc">Product Description:</label>
					<textarea rows="5" cols="50" class="form-control" name="description"></textarea>
 					<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
					<p>
						<input type="button" id="btnCreateProduct" value=" Add Product" class="btn btn-success">
						
						<a href="#"> Cancel </a>
					</p>			
			</form>
		</div>        
</div>
</div>
</body>


</html>