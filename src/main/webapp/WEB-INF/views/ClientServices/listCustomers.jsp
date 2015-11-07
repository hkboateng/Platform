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
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/tables/jquery.dataTables.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<%-- End of Include page header --%>
<div id="container" class="container">

<div class="row">
<div class="col-xs-12 col-sm-10 col-md-9">
		
          <h1>Client Services - Prospective Customer</h1>
			<hr>
          <div class="row pad_10">
		          <div class="errors">
			          <ul>
			          <c:if test="${not empty errors}">
			          	<li>${errors }</li>
			          </c:if>
			          </ul>
		          </div>
		          <div class="">

					<table id="customerList" class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>Full Name</th>
								<th>Address</th>
								<th>Contact Information</th>
								<th>View Profile</th>
							</tr>
						</thead>
						<tbody>		          
		          	<c:forEach varStatus="count" items="${customers}" var="customer" >
		          		<tr>
		          			<td>${count.count }</td>
		          			<td>${customer.firstname }</td>
		          			<td>${customer.lastname}</td>
		          			<td>${customer.company_name }</td>
		          			<td><a class="btn btn-success" href="<c:url value="viewProfile?customerId=${customer.customerId }"/>"><i class="fa fa-eye moveR_10"></i>Select</a></td>
		          		</tr>
		          	</c:forEach>
		          		</tbody>
		          		</table>
	          <input type="hidden" value="${customer.customerId }" name="customerId"/>
	          <input type="hidden" value="${customer.customerNumber }" name="customerName"/>
          </div>
</div>        
</div>
			<div class="hidden-sm hidden-xs col-md-3 col-lg-3" >
			<div>
				<h4>Notifications</h4>
				<ul class="list-group">
				  <li class="list-group-item">Cras justo odio</li>
				  <li class="list-group-item">Dapibus ac facilisis in</li>
				  <li class="list-group-item">Morbi leo risus</li>
				  <li class="list-group-item">Porta ac consectetur ac</li>
				  <li class="list-group-item">Vestibulum at eros</li>
				</ul>			
			</div>
				<jsp:include page="../sidebar.jsp"/>
			</div>
</div>
</div>
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
<script>
$(document).ready(function(){
	 $('#customerList').DataTable();
})
</script>

</body>
</html>