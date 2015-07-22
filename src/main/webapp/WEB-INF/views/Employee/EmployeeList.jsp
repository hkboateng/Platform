
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/fonts-awesome/font-awesome.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/angular.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
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
		<div class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-3 main">
			<div class="row">
			<%-- Filter List --%>
				<div>
				
				</div>
				<div id="listOfEmployee">
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Full Name</th>
								<th>Address</th>
								<th>Contact Information</th>
								<th>View Profile</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="empList" items="${employeeList}" varStatus="TheCount">
								<tr>
									<td>${TheCount.count}</td>
									<td>${empList.firstname} ${empList.middlename} ${empList.lastname}</td>
									<td>${empList.address1 }</td>
									<td>${empList.cellphone } ${empList.email}</td>
									<td><a href="" class="btn btn-primary">Select <span class="btn btn-primary"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- prefix free to deal with vendor prefixes -->
</body>

</html>