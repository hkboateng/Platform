<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation</title>
<link href="<c:url value="/resources/css/kube.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
</head>
<body>

<!-- Page Header -->
<%-- Include page header --%>
<div class="navigation-toggle navigation-toggle-black" data-tools="navigation-toggle" data-target="#sidemenu">
    <span>Menu</span>
</div>
<nav  id="sidemenu" class="navbar">
<header id="sidemenuContainer" class="group">
	<nav class="navbar navbar-left">
    <ul>
    	<li>Platform</li>   
    </ul>
    </nav>
	<nav class="navbar navbar-right">
    <ul>
    	<li><a href="#">About Us</a></li>   
    	<li><a href="#">Contact Us</a></li> 
    </ul>
    </nav>    


</header>
</nav>

<!-- Page Header ends -->
<div class="container">
<div class="units-row">
	<div class="unit-100">
				<div class="unit-block">
				<c:if test="${not empty errors }">
				<div class="tools-alert tools-message-red"> ${errors } </div>
				</c:if>
					<c:if test="${info !=null }">
						<div class="tools-alert tools-alert-green">
							<span class="glyphicon glyphicon-info-sign"></span>${info }
						</div>
					</c:if>
						<c:if test="${param.error != null}">
							<div class="tools-alert tools-alert-red">
								<p>Invalid username and password.</p>
							</div>
						</c:if>					
					<form action="<c:url value='/login' />" method='POST' class="forms">

						<c:if test="${param.info != null}">
							<p>You have been logged out.</p>
						</c:if>
						<label> Username:
						<input type="text" name="username"	placeholder="Username " class="width-100" />
						</label>
						 <label>Password:
						  <input type="password" name="password"	placeholder="Password" class="width-100" />
						</label> <input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button type="submit" class="btn btn-blue width-100">Log in</button>
					</form>
					<p>Can't login?  <a href="">Contact your Administrator</a> </p>
				</div>
			</div>
</div>
</div>

    <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/kube.js" />" type="text/javascript"></script>
</body>
</html>