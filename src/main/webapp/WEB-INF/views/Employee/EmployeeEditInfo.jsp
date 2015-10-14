<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/angular.js" />" type="text/javascript"></script>
</head>
<body  ng-app="">
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div class="container">
<div class="row">
<jsp:include page="../sidebar.jsp"/>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1>Add New Employee</h1>
			<hr>
			<sf:form method="post" modelAttribute="employee" action="addEmployee"  name="newEmployee" class="form">
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
				<input type="text" class="form-control" id="email" ng-model="username" name="email">		
		
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
				<select name="gender" class="form-control">
				<option value="male"> Male </option>
				<option value="female"> Female </option>
				</select>		
			</li>
			</ul>		

			</sf:form>
		</div>        
</div>
</div>
</body>
<script>
var pwd = document.getElementById("password").value;
var cpwd = document.getElementById("cpassword").value;

function validatePassword(){

	var message = document.getElementById("pwdValid");
	var msg = "";
	if(cpwd === pwd){
		return;
		
	}
	if(cpwd !== pwd){
		msg +="<span class='alert alert-danger'>Both passwords do not match...Try again</span>";
	}
	message.innerHTML = msg;
	
}

function submitForm(document){
	var form = document.form;
	if(cpwd !== pwd){
		return;
	}else{
		form.submit();
	}
}
</script>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</html>