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
	<%-- Include page header --%>
	<jsp:include page="../header.jsp"/>
	<!-- Page Header ends -->
<!-- Body Begins-->
	<div id="container" class="container">
	<h2 class="page-header">Welcome, ${employee.firstname}&nbsp;${employee.lastname}</h2>
		<div class="row center-block">
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<a href="dashboard" class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="fa fa-building" aria-hidden="true"></span>
					Company
				</a>
			</div>
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					Customers
				</button>			
			</div>		
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
					Payments
				</button>			
			</div>
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
					Settings
				</button>			
			</div>		
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
					Reports
				</button>
			</div>
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
					Dashboard
				</button>			
			</div>		
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
					Dashboard
				</button>			
			</div>
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
					Dashboard
				</button>			
			</div>			
			<div class="col-md-4 col-lg-4 col-sm-6 spaceBelow_10">
				<button class="btn btn-success btn-lg btn-block boxHeight_10" aria-label="Left Align">
					<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
					Dashboard
				</button>			
			</div>								
		</div>
	</div>
</body>
</html>
