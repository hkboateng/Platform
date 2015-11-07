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
<script src="<c:url value="/resources/js/visualization/d3.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<!-- Page Header -->
	<%-- Include page header --%>
	<jsp:include page="../header.jsp"/>
	<!-- Page Header ends -->
	<!-- Body Begins-->
	<div id="container" class="container">
		<div class="row">
		
			<div class="col-sm-11 col-md-12 col-lg-12 center-block main">
			 	<h2>Welcome, ${employee.firstname}&nbsp;${employee.lastname}</h2>
			         
					  <hr>
			          <div class="row">
			          <c:if test="${not empty success }">
				          <div class="alert alert-success" role="alert">
				          	${success}
				          </div>
			          </c:if>
			          <!-- Customer Search -->
			          </div>
			          <div class="row">
				          <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
				          	<h3>Messages</h3> <hr>
				          </div>		
				          <div class="col-xs-12 col-sm-6 col-md-8 col-lg-8">
				          	<h3>Transactions</h3> <hr>
				          </div>					          	          
			          </div>

			          <div class="row">
				          <div class="quick-stats">
				          	<div id="quick-stats-word" class="anw"> Quick Statistics</div>
				          </div>
								<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>
					          	<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>
					          	<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>
					          	<div class="col-xs-12 col-sm-6 col-md-4">
					          	
					          	</div>			          	
			          </div>
			</div>
		<script type="text/javascript">
		
		</script>
		</div>
	</div>
</body>
</html>
