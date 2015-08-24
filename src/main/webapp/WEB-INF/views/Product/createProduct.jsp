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
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
	<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div class="container" >
	<div class="row">
				<div  class="col-sm-3 col-md-3 sidebar">
			    <div id="accordian">
				<ul>
					<li>
						<h3><span class="fa fa-tachometer fa-2x"></span>Dashboard</h3>
						<ul>
							<li><a href="#">Reports</a></li>
							<li><a href="#">Search</a></li>
							<li><a href="#">Graphs</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<!-- we will keep this LI open by default -->
					<li class="active">
						<h3><span class="icon-tasks"></span>Tasks</h3>
						<ul>
							<li><a href="#">Today's tasks</a></li>
							<li><a href="#">Urgent</a></li>
							<li><a href="#">Overdues</a></li>
							<li><a href="#">Recurring</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="icon-calendar"></span>Calendar</h3>
						<ul>
							<li><a href="#">Current Month</a></li>
							<li><a href="#">Current Week</a></li>
							<li><a href="#">Previous Month</a></li>
							<li><a href="#">Previous Week</a></li>
							<li><a href="#">Next Month</a></li>
							<li><a href="#">Next Week</a></li>
							<li><a href="#">Team Calendar</a></li>
							<li><a href="#">Private Calendar</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="fa fa-sign-out"></span>Favourites</h3>
						<ul>
							<li><a href="#">Global favs</a></li>
							<li><a href="#">My favs</a></li>
							<li><a href="#">Team favs</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="fa fa-sign-out fa-lg"></span> Sign Out</h3>
				
					</li>
				</ul>
			</div>
	    </div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1>Client Services - Prospective Customer</h1>
			<hr>
          <div class="row">
          <div class="col-md-7">
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
			 
			<div class="col-xs-12 col-sm-12 col-md-8">
				<form name="createProductForm" action="/abankus/products/addProduct" method="post">

					<label for="productname">Product Name:</label>
					<input type="text" class="form-control" id="productname" name="productName"/>
					<label for="productcode">Product Code:</label><span id="helpBlock" class="help-text-inline">Maximum length is 8 charaters.</span>	
					<input type="text" class="form-control" id="productcode" name="productCode" maxLength="8"/>
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
