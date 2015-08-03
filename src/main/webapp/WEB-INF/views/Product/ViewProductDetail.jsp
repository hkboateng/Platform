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
		<h1>Product List</h1>
		<div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th> No.</th>
						<th> Product Code</th>
						<th> Product Name </th>
						<th>Product Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productList}" var="product" varStatus="counter">
						<tr>
							<td>${counter.count}</td>
							<td>${product.productCode }</td>
							<td>${product.productName }</td>
							<td>${product.description }</td>
						</tr>
					</c:forEach>	
				</tbody>		
			</table>

		</div>
		</div>

</div>
</div>
</body>