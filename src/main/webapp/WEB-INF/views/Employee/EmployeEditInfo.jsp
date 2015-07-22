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
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

<!-- Page Header ends -->
<div class="container">
<div class="row">
			<div  class="col-sm-3 col-md-3 sidebar">
			    <div id="accordian">
				<ul>
					<li>
						<h3><span class="fa fa-tachometer fa-2x"></span>Dashboard</h3>
						<ul>
							<li><a href="#">Reports</a></li>
							<li><a href="#">Search</a></li>
							<li><a href="#">Graphs</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<!-- we will keep this LI open by default -->
					<li class="active">
						<h3><span class="icon-tasks"></span>Tasks</h3>
						<ul>
							<li><a href="#">Today's tasks</a></li>
							<li><a href="#">Urgent</a></li>
							<li><a href="#">Overdues</a></li>
							<li><a href="#">Recurring</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="icon-calendar"></span>Calendar</h3>
						<ul>
							<li><a href="#">Current Month</a></li>
							<li><a href="#">Current Week</a></li>
							<li><a href="#">Previous Month</a></li>
							<li><a href="#">Previous Week</a></li>
							<li><a href="#">Next Month</a></li>
							<li><a href="#">Next Week</a></li>
							<li><a href="#">Team Calendar</a></li>
							<li><a href="#">Private Calendar</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="fa fa-sign-out"></span>Favourites</h3>
						<ul>
							<li><a href="#">Global favs</a></li>
							<li><a href="#">My favs</a></li>
							<li><a href="#">Team favs</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="fa fa-sign-out fa-lg"></span> Sign Out</h3>
				
					</li>
				</ul>
			</div>
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
                   <ul>
			          <c:if test="${not empty success}">
			          <li class="alert alert-success">${success }</li>
			          </c:if>
		          </ul>
			<sf:form method="post" modelAttribute="login" action="addEmployeeLogin" name="securityForm" class="form">

			<input type="hidden" name="trigger" value="security">
			<input type="hidden" name="punt" value="dashboard">
			<input type="hidden" name="currentpage" value="employeeLoginCredential">	
			<input type="hidden" name="emailAddress" value="${employee.email}"/>	
			
			<ul class="block-list">
			<li>
			  <label for="username" >Username:</label>
			    <input type="text" class="form-control" name="username" placeholder="User Name">					
			</li>
			<li>
				<label for="password" >Password:</label>
				<input type="password" class="form-control" id="password" name="password">				
			</li>
			<li>
				<label for="cpassword" >Confirm Password:</label>
				<input type="password" class="form-control" id="cpassword" name="cpassword" onblur="javascript:validatePassword();" onchange="javascript:validatePassword();">	
				<div id="pwdValid"></div>			
			</li>
			</ul>		
			<ul>
				<c:if test="${roleList.size() > 0}">
					<c:forEach var="role" items="${roleList }" varStatus="gdg" >
						<li>
						<input type="checkbox" name="role" value="${role.role }">${role.role }
						</li>
					</c:forEach>
				</c:if>
			</ul>				
			<p>
			<button class="btn btn-primary" onclick="javascript:submitForm(document.securityForm);"> Save Employee </button>
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
<script src="<c:url value="/resources/js/angular-ui.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</html>