<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection : List Products</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
	<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/angular.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body ng-app="platformApp">

<!-- Page Header -->
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Abankus Connection</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">About Us</a></li>
            <li><a href="#">Help and Contact Us</a></li>
          </ul>

        </div>
      </div>
    </nav>

<!-- Page Header ends -->
<div class="container"  ng-controller="ProductController">
	<div class="row">
	<div class="col-sm-3 col-md-2 sidebar">

          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item</a></li>
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li> <b>Last Login:</b>12:30pm</li>
          </ul>
        </div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1>Products/ Service List</h1>
			<hr>
          <div class="row">
          <div class="col-md-12">
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
				<table class="table table-bordered table-hover">
					<thead class="bg-primary">
						<tr>
						<th> No.</th>
							<th>Product Name</th>
							<th>Product Code</th>
							<th>Description</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
							<c:forEach items="${productList}" var="list" varStatus="theCount">
							<tr>
								<td>${theCount.count }</td>
								<td>${list.productName} </td>
								<td>${list.productCode }</td>
								<td>${list.description }</td>
								<td><a href="">Edit ${list.productCode}</a></td>
							<tr>
							</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</div>

</div>
</div>
</body>
</html>