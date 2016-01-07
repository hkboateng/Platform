<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
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
<div id="container" class="container-fluid">

<div class="row">
<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
<jsp:include page="../sidebar.jsp"/>
</div>
<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
		  <div class="col-lg-12 page-header">
		   	<span class="lead">Customers</span>
		   	<a href="create" class="btn btn-success pull-right"><span class="glyphicon glyphicon-plus moveR_20"></span>Add Customer</a>
		  </div>         
		         <div class="errors">
			          <ul>
			          <c:if test="${not empty errors}">
			          	<li>${errors }</li>
			          </c:if>
			          </ul>
		          </div>
		          <div>

					<table id="customerList" class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Contact Information</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>		          
		          		<c:forEach varStatus="count" items="${customers}" var="customer" >
			          		<tr>
			          			<td>${count.count }</td>
			          			<td>${customer.getFirstname() }</td>
			          			<td>${customer.getLastname()}</td>
			          			<td>${customer.getCompanyName() }</td>
			          			<td>
				          			<!-- Split button -->
										<div class="btn-group">
										  <button type="button" class="btn btn-success">Action</button>
										  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										    <span class="caret"></span>
										    <span class="sr-only">Toggle Dropdown</span>
										  </button>
										  <ul class="dropdown-menu">
										    <li><a href="javascript:submitCustomerURL(document.customerViewProfileForm,'${customer.customerNumber}')">View Customer Details</a></li>
										    <li><a href="#">Transaction History</a></li>
										    <li role="separator" class="divider"></li>
										    <li><a href="#">Quick Payment</a></li>
										  </ul>
										</div>			          			
			          		</tr>
		          		</c:forEach>
		          		</tbody>
		          		</table>

						<sf:form action="/abankus/customers/viewProfile" method="post" name="customerViewProfileForm">
						<input type="hidden" name="searchType" id="searchType" value="customerNumber">
						<input type="hidden" name="customerNumber" id="customerNumber" value="">
						</sf:form>
</div>
</div>
</div>
</div>
	<sf:form name="viewCustomerProfileBckBtn" action="/abankus/customers/viewProfile" method="post">
		<input type="hidden" name="searchType" id="searchType" value="customerNumber">
		<input type="hidden"  name="customerNumber" value="${cust}"/>	          
	</sf:form>	
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
<script>
$(document).ready(function(){
	 $('#customerList').DataTable();
	 
});

function submitCustomerURL(form,value){
	if( value !== undefined){
		form.customerNumber.value = value;
	}
	form.submit();
	
}
</script>
	<footer class="footer">
		<div class="container">
			<p>Copyright &copy; 2015</p>
		</div>		
	</footer>
</body>
</html>