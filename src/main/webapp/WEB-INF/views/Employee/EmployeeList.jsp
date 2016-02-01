
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Payment - Staff List</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/tables/jquery.dataTables.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>

<div id="container" class="container">
	<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12 center-block main">
			<c:if test="${not empty success_message}">
				<div class="alert alert-danger" role="alert">
					<span>${success_message}</span>
				</div>
			</c:if>
			<%-- Filter List --%>
		  <div class="col-lg-12 page-header">
		   	<span class="lead">Employee List</span>
		   	<a href="<c:url value="/registration/employee" />" class="btn btn-success pull-right"><span class="glyphicon glyphicon-plus moveR_20"></span>Add New Employee</a>
		    </div>  
				<div id="listOfEmployee">
					<table id="employeeList" class="table">
						<thead>
							<tr>
								<th></th>
								<th>Full Name</th>
								<th>Address</th>
								<th>Contact Information</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="empList" items="${employeeList}" varStatus="TheCount">
								<tr>
									<td>${TheCount.count}</td>
									<td>${empList.firstname} ${empList.middlename} ${empList.lastname}</td>
									<td>
										${empList.address1 }<br>${empList.address2 }
										<p>${empList.city }&nbsp;${empList.state }&nbsp;${empList.zipcode }</p>
									</td>
									<td>
										<p><span class="label"><i class="fa fa-phone-square"></i></span>&nbsp;${empList.getPhoneNumber() }</p>
										<p><span class="label"><i class="fa fa-envelope"></i></span>&nbsp;${empList.getEmailAddress()}</p>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
		</div>
	</div>
</div>
<!-- prefix free to deal with vendor prefixes -->
</body>
	<script>
$(document).ready(function(){
	 $('#employeeList').DataTable();
	 
});

function submitCustomerURL(form,value){
	if( value !== undefined){
		form.customerNumber.value = value;
	}
	form.submit();
	
}
</script>
</html>