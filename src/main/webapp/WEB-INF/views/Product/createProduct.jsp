<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 <%
 	String errs = (String) request.getAttribute("errorss");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
	<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div id="container" class="container-fluid" >
	<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
				<jsp:include page="../sidebar.jsp"/>
			</div>		
<div class="col-sm-9 col-md-9 col-lg-9 main">
          <h1>Client Services - Prospective Customer</h1>
			<hr>
			<%-- Error or Information --%>
			<div class="info_header">
				<c:if test="${info != null }">
					<div class="infoContainer">
						${info }
					</div>
				</c:if>
				<c:if test="${error != null }">
					<div class="errorContainer">
						<c:forEach var="i" items="${error }" varStatus="errors">
							${errors}
						</c:forEach>
					</div>
				</c:if>
			</div>
			 <div class="col-md-6">
				<form name="createProductForm" action="/abankus/products/addProduct" method="post">

					<label for="productname">Product Name:</label>
					<input type="text" class="form-state" id="productname" name="productName"/>
					<label for="productcode">Product Code:</label><span id="helpBlock" class="help-text-inline">Maximum length is 8 charaters.</span>	
					<input type="text" class="form-state" id="productcode" name="productCode" maxLength="8"/>
					<sf:errors path="*"/>	
					
						<label for="category">Category:</label>
						<select class="form-state" name="category">
							<option value="">Select a Category</option>
							<option value="services">Services</option>
							<option value="product">Product</option>
						</select>					
					

					<label for="productDesc">Product Description:</label>
					<textarea rows="5" cols="50" class="form-state" name="description"></textarea>
 					<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
					<p>
						<input type="submit" id="btnCreateProduct" value=" Add Product" class="btn btn-success">
						
						<a href="#"> Cancel </a>
					</p>
				</form>
				</div>
	</div>

</div>
</div>
<script>
$(document).ready(function(){
	$("#btnCreateProduct").click(function(){
		
	});
});
</script>
</body>
</html>
