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
<title>Abankus Corporation - Edit Customer Information</title>
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

<div class="col-sm-12 col-md-10 col-lg-10 main">
		
          <h1 class="pageHeading">Add New Customer</h1>
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
			<input type="hidden" name="trigger" value="personal">
			<input type="hidden" name="punt" value="employeeDepartmentInfo">
			<input type="hidden" name="currentpage" value="employeePersonal">
			<input type="hidden" name="customerType" id="custIndividual" value="${customerType }"/>
			<div>
				<c:choose>
					<c:when test="${customerType eq 'individual' }">
						<label>
						First Name:</label>
						<input type="text" name="firstname" id="firstname" class="form-state" placeholder="First Name"/>
						
						<label for="lastname">
						Last Name:</label>
						<input type="text" name="lastname" id="lastname" placeholder="Last Name" class="form-state"/>	
					<div class="row">
					<div class="col-sm-12 col-md-6">
						<label for="state" >Gender:</label>
						<script>genderList();</script>				
					</div>
					<div class="col-sm-12 col-md-6">
						<label for="phoneNumber">Phone Number:</label>	
						<input class="form-state"  id="dateOfBirth" name="dateOfBirth" type="text" placeholder="Month/day/Year">
					</div>					
					</div>

											
					</c:when>
					<c:when test="${customerType eq 'company'}">
						
						<label>	Company or Organization Name:</label>
						<input type="text" name="company_name" id="company_name" class="form-state" placeholder="Company Name"/>
						<h3> Contact Person</h3>
						<hr/>
						<label>	First Name:</label>
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
					</c:when>
				</c:choose>

				<div class="row">
				<div class="col-sm-12 col-md-12">
				<label for="address1">Address 1:</label>
				<input type="text" name="address1" id="address1" placeholder="Postal Address"  class="form-control"/>
				</div>
				<div class="col-sm-12 col-md-12">
				<label for="address2"> Address 2:</label>
				<input type="text" name="address2" placeholder="Optional" class="form-control" id="address2"/>				
				</div>
				</div>
					<div class="row">
					<div class="col-sm-12 col-md-4">
						<label for="city" >City:</label>
						<input type="text" class="form-control" id="city" name="city">					
					</div>
					<div class="col-sm-12 col-md-4">
						<label for="state" >State:</label>
						<script>createRegionList();</script>				
					</div>
					<div class="col-sm-12 col-md-4">
						<label for="zip" >Zip Code:</label>
						<input type="text" class="form-control" id="zip" name="zipcode" size="6" maxlength="5">				
					</div>
					</div>	
				  	<label for="emailAddress">Email Address:</label><span id="email-error" class="help-text-inline"></span>
				   	<input type="text" id="emailAddress" name="emailAddress" class="form-control" placeholder="Email Address" onblur="javascript:validateEmail(document.newCustomerForm.emailAddress);return false;">						
					
				  <div class="row">
				  <div class="col-xs-6 col-sm-6">
				  	<label for="phoneNumber">Phone Number:</label>  <span></span>	
						<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" size="20" >				  
				  </div>
				  <div class="col-xs-6 col-sm-6">
				  	<label>
						<input type="radio" name="phoneType" id="homePhone" value="homePhone" checked/> Home Phone
					</label>		
					<label >
				  		<input type="radio" name="phoneType" id="cellPhone" value="cellPhone"/> Cell (Mobile) Phone			
				  	</label> 						  
				  </div>

				  </div>							
			</div>
			<hr>
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
function validateEmail(email) 
{
	var r = email.value;
	
    var re = /\S+@\S+\.\S+/;
    if(!re.test(email)){
    	document.getElementById("email-error").innerHTML="Please enter a valid email!!";
    	$("#emailAddress").addClass("error ");
    	document.getElementById("emailAddress").focus();
    }
}
</script>
</html>