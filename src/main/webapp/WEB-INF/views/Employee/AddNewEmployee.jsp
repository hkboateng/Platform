<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/angular.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body  ng-app="">
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
            <li><a href="#"><span  class="fa fa-cog fa-lg"></span>Settings</a></li>
            <li><a href="#">About Us</a></li>
            <li><a href="#">Help and Contact Us</a></li>
          </ul>

        </div>
      </div>
    </nav>

<!-- Page Header ends -->
<div class="container">
<div class="row">
<div class="col-sm-3 col-md-2 sidebar">

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
          <h1>Add New Employee</h1>
			<hr>

          <div class="row">
          <div class="col-md-7">
          <div class="errors">
          <ul>
          <c:if test="${not empty errors}">
          <li class="alert alert-warning">${errors }</li>
          </c:if>
          </ul>
          </div>
			<h3>Personal Information</h3><hr/>
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
			<p>
			<button class="btn btn-primary"> Save Employee </button>
			&nbsp;&nbsp;&nbsp;
			<a href="#"> Cancel </a>
			</p>
			</sf:form>
          </div>
          <div class="col-md-5">
 			<h3> Job Credentials</h3>	<hr/>	
			<label for="username">
				Username
			</label>
			<input type="text" class="form-control" id="username" name="username" value="{{username}}"/>				
			<label for="passwd"> Password: </label>
			<input type="password" name="password" class="form-control" id="password"/>	
			<label for="confirm_passwd">Confirm Password: </label>	
			<input type="password" class="form-control" id="cpassword" name="cpassword" onblur="javascript:validatePassword();" onchange="javascript:validatePassword();">	
			<div id="pwdValid"></div>	
			<div>Select Employee Role:</div>
			<ul>
				<c:if test="${roleList.size() > 0}">
					<c:forEach var="role" items="${roleList }" varStatus="gdg" >
						<li>
						<input type="checkbox" name="role" value="${role.role }">${role.role }
						</li>
					</c:forEach>
				</c:if>
			</ul>				
			<label for="department">
				Employee Role:
			</label>
			<select name="department" class="form-control">
				<option value="12"> Sales </option>
				<option value="13"> Customer Services </option>
				<option value="14"> Help Desk </option>
			</select>							         	
          </div>
                    </div>
            <hr> 					
			<p>
			<button class="btn btn-primary"> Save Employee </button>
			&nbsp;&nbsp;&nbsp;
			<a href="#"> Cancel </a>
			</p>
</div>        
</div>
</div>
</body>


<script>
var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope) {
    $scope.user = 'John Doe';
    $scope.email = 'john.doe@gmail.com';
});

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
</html>