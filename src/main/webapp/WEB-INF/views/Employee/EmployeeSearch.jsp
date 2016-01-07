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
<link href="<c:url value="/resources/css/fonts-awesome/font-awesome.css" />" rel="stylesheet"/>
    <script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
	<!-- Page Header ends -->
	<!-- Body Begins-->
	<div id="container" class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<jsp:include page="../sidebar.jsp"/>
			</div>	
			<div class="col-sm-12 col-md-10 col-lg-10 center-block main">
				<div class="page-header">
					<h1>Search for Employee</h1>
				</div>
				<div class="col-sm-12 col-md-8 col-lg-8">
					<sf:form method="post" action="<c:url value="findEmployee"/>" name="EmployeeSearchForm">
					<label for="employeeSearch" class="sr-only">Employee </label>
					<input type="text" class="custom-text width-50" name="employeeSearch placeholder="Employee Name, Number and Email Address">
					<button class="btn btn-success" value="Search" id="btnSearchEmployee"><i class="fa fa-search"></i></button>
					</sf:form>
				</div>
			</div>
			
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btnSearchEmployee').click(function(){
			document.EmployeeSearchForm.submit();
		});
	});
</script>
</html>