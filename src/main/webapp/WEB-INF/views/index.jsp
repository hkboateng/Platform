<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
</head>
<body>

<!-- Page Header -->
<%-- Include page header --%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      
    </div>
 	<div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand">
        Abankus Payment
      </a>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="#">About Us</a></li>
      	<li><a href="#">Sign In</a></li>
        <li><a href="<c:url value="/Company/signup" />">Sign Up</a></li>
        <li><a href="#">Contact Us</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->   
  </div>
</nav>
<!-- Page Header ends -->
		<div id="container" class="container-fluid">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">	
					<div class="col-sm-12 col-md-8 col-lg-6">	
					<c:if test="${not empty errors }">
					<div class="alert alert-success"> ${errors } </div>
					</c:if>
					<c:if test="${info !=null }">
						<div class="alert alert-success">
							<span class="glyphicon glyphicon-info-sign"></span> ${info }
						</div>
					</c:if>
					<c:if test="${param.error != null}">
						<div class="alert alert-warning">
							<p>Invalid username and password.</p>
						</div>
					</c:if>								
						<form action="<c:url value='/login' />" method='POST' class="spaceBelow_10">
	
							<c:if test="${param.info != null}">
								<p>You have been logged out.</p>
							</c:if>
							<label class="label"> Username:</label>
							<input type="text" name="username"	placeholder="Username " class="form-state width-100" />
							
							 <label class="label">Password:</label>
							  <input type="password" name="password"	placeholder="Password" class="form-state width-100" />
							
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button type="submit" class="btn btn-success">Log in</button>
							<a href="#" id="aLoginCancelLnk" class="moveL_20">Cancel</a>
						</form>
						<p>Can't login?  <a href="">Contact your Administrator</a> </p>
					</div>
				</div>			
			</div>
	</div>

    <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
</body>
</html>