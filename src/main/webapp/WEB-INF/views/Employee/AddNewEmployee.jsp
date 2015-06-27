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
<script src="<c:url value="/resources/js/angular.js" />" type="text/javascript"></script>
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
            <li><a href="#">Settings</a></li>
            <li><a href="#">About Us</a></li>
             <sec:authorize access="isAuthenticated()"> 
 			<li>
			    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
			      <span class="glyphicon glyphicon-user moveR_5" aria-hidden="true">
			      	</span><sec:authentication property="principal.username" />
			      <span class="caret"></span>
			    </a>
			    <ul class="dropdown-menu" role="menu">
		            <li><a href="#">Update Profile</a></li>
		            <li class="divider"><a href="#"></a></li>
		            <li><a href="logout">Logout</a></li>         	
			    </ul>
          	</li>		  
		  </sec:authorize>
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
				<ul class="progressbar-list-3">
					<li class="active">
						Personal Information
					</li>
					<li class="normal">
						Department/Employment
					</li>
					<li class="normal">
						Security and Login
					</li>
				</ul>
			</div>
          <div class="row">
          <div class="col-md-7">
          <div class="errors">
          <ul>
          <c:if test="${not empty errors}">
          <li class="alert alert-warning">${errors }</li>
          </c:if>
          </ul>
          </div>

			<sf:form method="post" modelAttribute="employee" action="addEmployee" class="form">
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
				<input type="text" class="form-control" id="email" name="email">					
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
			<h1> Job Credentials</h1>		
			<ul class="block-list">
			<li>
			<label for="department">
				Select Department:
			</label>
			<select name="department" class="form-control">
				<option value="12"> Sales </option>
				<option value="13"> Customer Services </option>
				<option value="14"> Help Desk </option>
			</select>
			</li>
			<li>
			<label for="position"> Job Title: </label>
			<input type="text" name="position" class="form-control" id="position"/>
			</li>
			</ul>				
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

<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/angular-ui.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</html>