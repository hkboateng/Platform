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
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
</head>
<body>

<!-- Page Header -->
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<%-- End of Include page header --%>
<!-- Page Header ends -->
<div id="container" class="container">
	<div class="row">
<div class="col-sm-9 col-md-12 col-lg-12">
          <h1>Products/ Service List</h1>
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
				<table id="product" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Product Code</th>
							<th>Description</th>
							<th>View Product</th>
						</tr>
					</thead>
					<tbody>
							<c:forEach items="${productList}" var="list" varStatus="theCount">
							<tr>
								<td>${list.productName}</td>
								<td>${list.productCode }</td>
								<td>${list.description }</td>
								<td><a class="btn btn-success" href="<c:url value="viewProduct/${list.getProductCode()}"/>">Select </a></td>
							<tr>
							</c:forEach>
					</tbody>
				</table>
		</div>

</div>
</div>


</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#product').DataTable({
		
	});
});
</script>
</html>