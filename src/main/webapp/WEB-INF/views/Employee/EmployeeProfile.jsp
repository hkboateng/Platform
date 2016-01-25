<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Payments - Employee Profile</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div id="container" class="container">
<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 main">
          	<div class="page-header">
          		<h1>Hi, <small>${employeeInstance}</small></h1>
          	</div>
          	<div id="employeeProfileDiv">
          	<div class="lead page-header">Staff Info</div>
          		<p>
          			<label class="label labelLength_10">Employee Number:</label>
          			<span>${employeeInstance.getEmployeeId()}</span>
          		</p>
          		<label class="label labelLength_30">Address</label>
          		<div class="moveL_5 spaceBelow_10">
          			<div>${employeeInstance.getAddress1() }</div>
          			${employeeInstance.getAddress2() }
          			<div>${employeeInstance.getCity() }&nbsp;${employeeInstance.getState() }&nbsp;${employeeInstance.getZipcode() }</div>
          		</div>
          		
          		<p><label class="label labelLength_10">Phone Number:</label><span>${employeeInstance.getCellphone() }</span></p>
          		
          		<p><label class="label labelLength_10">Emaill Address:</label><span>${employeeInstance.getEmail() }</span></p>
          	</div>
          	<div class="spaceBelow_20">
          		<hr>
          	</div>
          	<div class="">
          		<div class="lead page-header">Company Info</div>
          		<p><span class="labelLength_20 label">Company Name:</span></p>
          		<p><span class="labelLength_20 label">Company Address:</span></p>
          		<p><span class="labelLength_20 label">Industry:</span></p>
          	</div>
          	<%--
			<sf:form method="post" modelAttribute="employee" action="addEmployee"  name="newEmployee" class="form hidden">
			<input type="hidden" name="trigger" value="personal">
			<input type="hidden" name="punt" value="employeeDepartmentInfo">
			<input type="hidden" name="currentpage" value="employeePersonal">
			<ul class="inline-list-2">
			<li>
				<label for="firstname" > First Name:</label>
				<input type="text" class="form-control" id="firstname" name="firstname">			
			</li>
			<li>
				<label for="lastname" > Last Name:</label>
				<input type="text" class="form-control" id="lastname" name="lastname">				
			</li>
			</ul>

				<label for="address1" >Address:</label>
				<input type="text" class="form-control" id="address1" name="address1"><br>		
				<label for="address2" >Address 2 (Optional):</label>
				<input type="text" class="form-control" id="address2" name="address2">				
		
			<ul class="inline-list-2">
			<li>
				<label for="email" >Email Address:</label>
				<input type="text" class="form-control" id="emailAddress" name="email">		
		
			</li>
			<li>
				<label for="phone" >Phone Number:</label>
				<input type="text" class="form-control" id="phone" name="cellphone">				
			</li>
			</ul>		
			<ul class="inline-list-3">
			<li>
				<label for="city" >City:</label>
				<input type="text" class="form-control" id="city" name="city">					
			</li>
			<li>
				<label for="state" >State:</label>
				<input type="text" class="form-control" id="state" name="state">				
			</li>
			<li>
				<label for="zip" >Zip Code:</label>
				<input type="text" class="form-control" id="zip" name="zipcode" size="6" maxlength="5">				
			</li>
			</ul>
			<ul class="inline-list-2">
			<li>
			  <label for="dateOfBirth" >Date of Birth:<span> </span></label>
			  <div class="form-group">
			  	<input type="text" name="dateOfBirth" id="day" placeholder=" dd" size="3" maxlength="2">
				<input type="text" name="month" id="month" placeholder=" mm" size="3" maxlength="2">
				<input type="text" name="year" id="year" placeholder=" yyyy" size="3" maxlength="4">	
				</div>
			</li>
			<li>
				<label for="gender" >Gender:</label>
				<script>genderList();</script>			
			</li>
			</ul>		

			</sf:form>
			 --%>
		</div>        
</div>
</div>
</body>

</html>