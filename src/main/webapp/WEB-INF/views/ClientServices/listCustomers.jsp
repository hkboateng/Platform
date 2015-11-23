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
		          			<td><a class="btn btn-success" href="javascript:submitCustomerURL('${customer.getCustomerNumber()}',document.customerSearchForm);"><i class="fa fa-eye moveR_10"></i>Select</a></td>
		          		</tr>
		          	</c:forEach>
		          		</tbody>
		          		</table>
		       <sf:form name="customerSearchForm" method="post" action="viewProfile">
			      <input type="hidden" name="searchType" value="customerNumber">
		          <input type="hidden" value="" name="customerNumber"/>	
		               
		       </sf:form>

          </div>
</div>        
</div>
</div>
</div>
<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
<script>
$(document).ready(function(){
	 $('#customerList').DataTable();
	 
});
function submitCustomerURL(url,form){
	if(url){
		form.customerNumber.value = url;
	}
	form.submit();
}
</script>

</body>
</html>