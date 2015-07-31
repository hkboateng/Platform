<%-- Page for enter Client Order which creates a ClientBilling instance --%>
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
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div class="container">
<div class="row">
<jsp:include page="../sidebar.jsp"/>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1>Add New Employee</h1>
			<hr>

			<form class="form-inline" action="" method="">
				<div class="form-group">
					<label for="customerAccount" class="sr-only">Customer Account Number:</label>
					<input type="text" name="customerAccount" id="customerAccount" class="custom-text" placeholder="Enter Account Number"/>
							
				</div>
				<button type="submit" class="btn btn-success" title="Search for Customer using Customer AccountNumber" ><i class="fa fa-search"></i></button>		
			</form>
			<hr>
			<form>
			
			</form>
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
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</html>