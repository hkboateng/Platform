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
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>

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
          <h1>Client Services - Prospective Customer</h1>
			<hr>
          <div class="row">
          <div class="col-md-7">
          <div class="errors">
          <ul>
          <c:if test="${not empty errors}">
          <li>${errors }</li>
          </c:if>
          </ul>
          </div>

			<sf:form method="post" modelAttribute="customer" action="/abankus/customers/addCustomer" app-init="customerType=individual" class="form" name="myForm">
			<input type="hidden" name="trigger" value="personal">
			<input type="hidden" name="punt" value="employeeDepartmentInfo">
			<input type="hidden" name="currentpage" value="employeePersonal">
			<input type="hidden" name="customerType" id="custIndividual" value="${customerType }"/>
			<div>
				<c:choose>
					<c:when test="${customerType eq 'individual' }">
						<label>
						First Name:</label>
						<input type="text" name="firstname" id="firstname" class="form-control" placeholder="First Name"/>
						
						<label for="lastname">
						Last Name:</label>
						<input type="text" name="lastname" id="lastname" placeholder="Last Name" class="form-control"/>	
					<ul class="inline-list-2">
					<li>
						<label for="state" >Gender:</label>
						<script>genderList();</script>				
					</li>
					<li>
				<label for="phoneNumber">Phone Number:</label>	
				<input class="form-control "  id="dateOfBirth" name="dateOfBirth" type="text" placeholder="Month/day/Year">
				
				<span class="add-on"><i class="icon-th"></i></span>
					</li>
					</ul>						
					</c:when>
					<c:when test="${customerType eq 'company'}">
						
						<label>	Company or Organization Name:</label>
						<input type="text" name="company_name" id="company_name" class="form-control" placeholder="Company Name"/>
						<h3> Contact Person</h3>
						<hr/>
						<label>	First Name:</label>
						<input type="text" name="firstname" id="firstname" class="form-control" placeholder="First Name"/>
						<label for="lastname">
						Last Name:</label>
						<input type="text" name="lastname" id="lastname" placeholder="Last Name" class="form-control"/>
						<label for="state" >Gender:</label>
						<script>genderList();</script>						
					</c:when>
				</c:choose>

				
				<label for="address1">Address 1:</label>
				<input type="text" name="address1" id="address1" placeholder="Postal Address"  class="form-control"/>
				<label for="address2"> Address 2:</label>
				<input type="text" name="address2" placeholder="Optional" class="form-control" id="address2"/>
					<ul class="cus-inline-list-3">
					<li>
						<label for="city" >City:</label>
						<input type="text" class="form-control" id="city" name="city">					
					</li>
					<li>
						<label for="state" >State:</label>
						<script>createRegionList();</script>				
					</li>
					<li>
						<label for="zip" >Zip Code:</label>
						<input type="text" class="form-control" id="zip" name="zipcode" size="6" maxlength="5">				
					</li>
					</ul>	
				  	<label for="emailAddress">Email Address:</label>
				   	<input type="text" id="emailAddress" name="emailAddress" class="form-control" placeholder="Email Address">						
					

				  <ul class="inline-list-2">
				  	<li>
				  	<label for="phoneNumber">Phone Number:</label>  <span></span>	
						<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" size="20" >	
						
				  	</li>
				  	<li>
				  	<label class="radio-inline">
						<input type="radio" name="phoneType" id="homePhone" value="homePhone" checked/> Home Phone
						</label>
					<label class="radio-inline">
				  		<input type="radio" name="phoneType" id="cellPhone" value="cellPhone"/> Cell (Mobile) Phone			
				  		</label>  	
				  	</li>
				  </ul>
				  

				  
				  
				  
				  								
			</div>
			<p>
			<button class="btn btn-primary"> Save Employee </button>
			&nbsp;&nbsp;&nbsp;
			<a href="#"> Cancel </a>
			</p>
		
			</sf:form>
          </div>
                    </div>
</div>        
</div>
</div>
</body>


<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-masked.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script>
$(document).ready(function(){
	$('#phoneNumber').mask('000-000-0000');
	$('#dateOfBirth').mask('00/00/0000');
});

</script>
</html>