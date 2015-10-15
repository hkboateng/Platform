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
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<%-- End of Include page header --%>
<div class="container">
<div class="row">
<div class="col-sm-3 col-md-2 sidebar" >

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
		
          <h1>Client Services - Prospective Customer</h1>
			<hr>
          <div class="row">
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
</div>
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
<script>
$(document).ready(function(){
	 $('#customerList').DataTable();
})
</script>

</body>
</html>