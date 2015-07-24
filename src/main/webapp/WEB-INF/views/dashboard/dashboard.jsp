<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/fonts-awesome/font-awesome.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
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
          <li><a href="">Home</a></li>
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
<!-- Body Begins-->
<div class="container-fluid">
<div class="row">
<div class="col-sm-3 col-md-2 sidebar">
			    <div id="accordian">
				<ul>
					<li>
						<h3><span class="fa fa-tachometer fa-2x"></span>Dashboard</h3>
						<ul>
							<li><a href="<c:url value='/customers/create/individual' />">New Prospect Customer - Individual</a></li>
							<li><a href="<c:url value='/customers/create/company' />">New Prospect Customer - Customer</a></li>
							<li><a href="#">Graphs</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<!-- we will keep this LI open by default -->
					<li class="active">
						<h3><span class="icon-tasks"></span>Bill and Payments</h3>
						<ul>
							<li><a href="#">Today's tasks</a></li>
							<li><a href="#">Urgent</a></li>
							<li><a href="#">Overdues</a></li>
							<li><a href="#">Recurring</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="icon-calendar"></span>Employee Service</h3>
						<ul>
							<li><a href="#">Add Employee</a></li>
							<li><a href="#">Search</a></li>
							<li><a href="#">List Employee</a></li>
							<li><a href="#">Update Employee Details</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="fa fa-sign-out"></span>Products and Services</h3>
						<ul>
							<li><a href="#">Add Product/Services</a></li>
							<li><a href="#">Search</a></li>
							<li><a href="#">Update Products/Services</a></li>
							<li><a href="#">List Products/Services</a></li>
						</ul>
					</li>
					<li>
						<h3><span class="fa fa-sign-out"></span>Client Service</h3>
						<ul>
							<li><a href="#">Add Product/Services</a></li>
							<li><a href="#">Search</a></li>
							<li><a href="#">Update Products/Services</a></li>
							<li><a href="#">List Products/Services</a></li>
						</ul>
					</li>	
					<li>
						<h3><span class="fa fa-sign-out"></span>Reports and Documents</h3>
						<ul>
							<li><a href="#">Add Product/Services</a></li>
							<li><a href="#">Search</a></li>
							<li><a href="#">Update Products/Services</a></li>
							<li><a href="#">List Products/Services</a></li>
						</ul>
					</li>									
					<li>
						<h3><a class="text-primary" href="<c:url value='logout'/>"><span class="fa fa-sign-out fa-lg"></span> Sign Out</a></h3>
				
					</li>
				</ul>
			</div>

</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <h1>Dashboard</h1>
			<hr>
          <div class="row">
          
          </div>
</div>
</div>
</div>
</body>
</html>
