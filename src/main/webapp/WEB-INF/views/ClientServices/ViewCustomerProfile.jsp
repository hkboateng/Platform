<%
	
	String customerType = request.getParameter("customerType");
	String employeeInfo = (String)session.getAttribute("employeeInfo");
	pageContext.setAttribute("customerType", customerType);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
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
<div class="col-sm-3 col-md-2 sidebar" ng-controller="DatepickerDemoCtrl">

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
		
          <h3>Customer Account Details</h3>
		  <hr class="line1">
          <div class="row">
          <c:if test="${not empty message}">
	          <div class="alert alert-danger" role="alert">
			      ${message}
			   </div>         
          </c:if>
          </div>
		 
		  	<div id="" class="row ">
 				 <div class="col-xs-12 col-md-8">
 				  <c:if test="${not empty customer}">
 				 <div id="customerDetailBlock">
 				 	<div>
					  	<ul class="navigation-linear">
					  	<li class="pull-left bold">Personal Details</li>
						  <li class="pull-right"><a href="" class=""><i class="glyphicon glyphicon-pencil moveR_10"></i>Edit </a></li>
						</ul>  				 	
 				 	</div>
 				 	
					<div class="clearfix"></div>
					<hr  class="nav-line">
				  	<div id="customerPersonalData">
				  		<p><span class="bold">Full Name:</span>&nbsp;${customer.firstname} ${customer.middlename } ${customer.lastname }</p>
				  		<p><span class="bold">Company Name:</span> ${customer.company_name }</p>
				  		<p><span class="bold">Customer Number: </span>${customer.customerNumber.toUpperCase() }</p>
				  	</div>
				  	</div>
				  	</c:if>
				  	
 				 	<div>
					  	<ul class="navigation-linear">
					  	<li class="pull-left bold">Account Details</li>
					  	<c:if test="${not empty customerAccount}">
						  <li class="pull-right"><a href="" class=""><i class="glyphicon glyphicon-pencil moveR_10"></i>Edit </a></li>
						  </c:if>
						</ul>  				 	
 				 	</div>
 				 	<div class="clearfix"></div>
 				 	<hr  class="nav-line">
 				 	<c:choose>
						<c:when test="${not empty customerAccount}">
					  	<div id="customerAccountData">
					  		<p><span class="bold">Account Number: </span>${customerAccount.accountNumber }</p>
					  		<p><span class="bold">Account Status: </span> ${customerAccount.status } <a href="" class=" btn btn-success btn-sm moveR_10"><i class="glyphicon glyphicon-edit  moveR_5"></i>Edit </a></p>
					  		<p><span class="bold">Date Created: </span><fmt:formatDate type="date" dateStyle="long" timeStyle="long" value="${customerAccount.dateCreate }" /> </p>
					  		<p><span class="bold">Notes: </span> ${customerAccount.notes }</p>
					  	</div> 	
					  	</c:when>	 
					  	<c:otherwise>
					  	<div  class="alert alert-info" role="alert">
						  <strong>Ooops!</strong>  ${customer.firstname} ${customer.middlename } ${customer.lastname } Account Details are not available this time!!!.
						</div>
					  	</c:otherwise>				 	
 				 	</c:choose> 	
					<div class="clearfix"></div>
 				 	<div>
					  	<ul class="navigation-linear">
					  	<li class="pull-left bold">Address</li>
					  	<c:if test="${not empty address}">
						  <li class="pull-right"><a href="" class=""><i class="glyphicon glyphicon-pencil moveR_10"></i>Edit </a></li>
						  </c:if>
						</ul>  				 	
 				 	</div>
 				 	<div class="clearfix"></div>
 				 	<hr  class="nav-line">									  	
 				 	<c:choose>
						<c:when test="${not empty address}">
					  	<div id="customerAccountData">
					  	<c:forEach items="${address}" var="addressList">
					  		<span class="bold">${addressList.addressType.toUpperCase()} Address:</span><br>
					  		${addressList.address1 }&nbsp;${addressList.address2 }
					  		${addressList.city }&nbsp;${addressList.region }&nbsp;${addressList.zipcode }<br>
					  	</c:forEach>	
					  	</div> 	
					  	</c:when>	 
					  	<c:otherwise>
					  	<div  class="alert alert-info" role="alert">
						  <strong>Ooops!</strong> Addess Information are not available this time!!!.
						</div>
					  	</c:otherwise>				 	
 				 	</c:choose>				  	
				  										 
 				 </div>
  				 <div class="col-xs-6 col-md-4">
  				 	<h3>Orders</h3>
  				 </div>		  	

			</div>
			<hr>	

	          <input type="hidden" value="${customer.customerId }" name="customerId"/>
	          <input type="hidden" value="${customer.customerNumber }" name="customerName"/>					

	  
		  


          
</div>        
</div>
</div>
</body>
</html>