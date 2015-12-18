<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection : List Products</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/tables/jquery.dataTables.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>

</head>
<body>

<!-- Page Header -->
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<%-- End of Include page header --%>
<!-- Page Header ends -->
<div id="container" class="container-fluid">
	<div class="row">
			<div class="col-sm-2 col-md-2 col-lg-2">
				<jsp:include page="../sidebar.jsp" />
			</div>	
<div class="col-sm-9 col-md-10 col-lg-10">
          <h1>Products/Service List</h1>
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
				<table id="products" class="table  table-striped">
					<thead>
						<tr>
							<th></th>
							<th>Product Name</th>
							<th>Product Code</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
							<c:forEach items="${productList}" var="list" varStatus="theCount">
							<tr>
								<td>${theCount.count }</td>
								<td>${list.productName}</td>
								<td>${list.productCode }</td>
								<td>${list.description }</td>
								<td>
									<!-- Split button -->
									<div class="btn-group">
									  <button type="button" class="btn btn-danger">Action</button>
									  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									    <span class="caret"></span>
									    <span class="sr-only">Select</span>
									  </button>
									  <ul class="dropdown-menu">
									    <li><a href="#">Make Sale</a></li>
									    <li role="separator" class="divider"></li>
									    <li><a href="#">View Details</a></li>
									  </ul>
									</div>								
								</td>
							<tr>
							</c:forEach>
					</tbody>
				</table>
		</div>

</div>
</div>

<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#product').DataTable();
});
</script>
</html>