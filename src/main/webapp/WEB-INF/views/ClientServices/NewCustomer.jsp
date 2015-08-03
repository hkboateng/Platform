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
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>

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
<div class="col-sm-9 col-sm-offset-3 col-md-12 col-md-offset-2 main">
		
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

			<sf:form method="post" modelAttribute="customer" action="/abankus/customers/addCustomer"  name="newCustomerForm" class="form" >
			<input type="hidden" name="customerType" id="custIndividual" value="${customerType }"/>
			<div>

						<label>
						First Name:</label>
						<input type="text" name="firstname" id="firstname" class="form-control" placeholder="First Name"/>
						
						<label for="lastname">
						Last Name:</label>
						<input type="text" name="lastname" id="lastname" placeholder="Last Name" class="form-control"/>	
					<div class="row">
					<div class="col-sm-12 col-md-6">
						<label for="state" >Gender:</label>
						<script>genderList();</script>				
					</div>
					
					</div>

						
						<label>	Company or Organization Name: (Optional)</label>
						<input type="text" name="company_name" id="company_name" class="form-control" placeholder="Company Name"/>


					<div class="row">				
					
					<div class="col-sm-12 col-md-12">
					<label for="address1">Address 1:</label>
					<input type="text" name="address1" id="address1" placeholder="Postal Address"  class="form-control"/>
					</div>
					<div class="col-sm-12 col-md-12">
					<label for="address2"> Address 2:</label>
					<input type="text" name="address2" placeholder="Optional" class="form-control" id="address2"/>				
					</div>					
					<div class="col-sm-12 col-md-4">
						<label for="city" >City:</label>
						<input type="text" class="form-control" id="city" name="city">					
					</div>
					<div class="col-sm-12 col-md-4">
						<label for="state" >State:</label>
						<script>createRegionList();</script>				
					</div>
					<div class="col-xs-6 col-sm-6 col-md-4">
						<label for="zip" >Zip Code:</label>
						<input type="text" class="form-control" id="zip" name="zipcode" size="6" maxlength="5">				
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<label for="phoneNumber">Phone Number:</label>	
						<input class="form-control "  id="phoneNumber" name="phoneNumber" type="text" placeholder="(Area Code)XXX-XXXX">
									  	<label>
						<input type="radio" name="phoneType" id="homePhone" value="homePhone" checked/> Home Phone
					</label>		
					<label >
				  		<input type="radio" name="phoneType" id="cellPhone" value="cellPhone"/> Cell (Mobile) Phone			
				  	</label> 						
					</div>

				  <div class="col-xs-6 col-sm-6">
				  	<label for="emailAddress">Email Address:</label><span id="email-error" class="help-text-inline"></span>
				   	<input type="email" id="emailAddress" name="emailAddress" class="form-control" placeholder="Email Address" ">						
								  
				  </div>
					</div>	
				  <div class="row">
						<div class="col-xs-6 col-sm-6">
						<label for="phoneNumber">Account Status:</label>	
							<select name="accountStatus" class="form-control">
								<option value="">Select Account Status</option>
								<option value="active">Active</option>
								<option value="prospect" selected>Prospective</option>
								<option value="inactive">InActive</option>
								<option value="close">Closed</option>
							</select>
						</div>
						<div class="col-xs-6 col-sm-6">
						
						</div>
						<div class="col-xs-6 col-sm-6">
						<label for="customerIndustry">Industry</label>
							<select name="industry" class="form-control">
								<option value="">Select Industry</option>
								<option value="active">Financial Services</option>
								<option value="prospect">Prospective</option>
								<option value="inactive">InActive</option>
								<option value="close">Closed</option>
							</select>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
						<label for="customerNotes">Notes:</label>
						<textarea name="notes" id="customerNotes" rows="4" class="form-control"></textarea>
						</div>					



				  </div>							
			</div>
			<hr>
			<p>
			<button class="btn btn-primary"> Save Customer </button>
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