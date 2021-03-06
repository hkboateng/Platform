<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sign Upfor Abankus Payment</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script	src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<c:url value="/platform/index" />">
        Abankus Payments
      </a>
    </div>
  </div>
</nav>
	<div id="container" class="container">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="page-header">
					<h1>Abankus Payment Signup</h1>
				</div>
			</div>
			<div class="col-sm-12 col-md-8 col-lg-8">
				<c:if test="${success_message  != null}">
					<div class="alert alert-success">
						${success_message}
					</div>
				</c:if>
				<c:if test="${validationError  != null}">
					<div class="alert alert-warning">
						${validationError}
					</div>
				</c:if>			
				<c:if test="${error_message != null}">
					<div class="alert alert-success">
						${error_message}
					</div>
				</c:if>		
				<form name="frmCompanySignUp" id="frmCompanySignUp" action="<c:url value="/Company/saveCompany" />" method="post">
					<div class="col-sm-8 col-md-8">

						<div>
							<label for="companyname">Company Name:</label>
							<input type="text" class="form-state width-100" name="companyname" id="companyname"/>	
						</div>
						<div>
							<label for="address">Address:</label>
							<input type="text" class="form-state width-100" name="address" id="address"/>
						</div>
						<div>				
							<label for="address2">Address 2 (optional):</label>
							<input type="text" class="form-state width-100" name="address1" id="address1"/>
						</div>	
						</div>
						<div class="col-sm-12 col-md-6 col-lg-6">
							<label for="city">City:</label>
							<input type="text" class="form-state width-100" name="city" id="city"/>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-12 col-md-6 col-lg-6">
							<label for="companyname">State/Region:</label>
							<script>createRegionList();</script>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-12 col-md-6 col-lg-6">
						<label for="zipcode">Zip Code:</label>
						<input type="text" class="form-state width-100" name="zipcode" id="zipcode" maxlength=5/>		
						</div>			
						<div class="clearfix"></div>	
						<div class="col-sm-12 col-md-6 col-lg-6">
							<label for="phonenumber">Phone Number:</label>
							<input type="text" class="form-state width-100" name="phonenumber" id="phonenumber" placeholder="XXX-XXX-XXXX"/>	
						</div>	
						<div class="clearfix"></div>
						<div class="col-sm-12 col-md-6 col-lg-6">
							<label>Email address:</label>
							<input type="text" name="emailAddress" id="emailAddress" class="form-state width-100"/>
						</div>					
						<div class="clearfix"></div>
						<div class="lead page-header">
							<h2>Contact and Login Info</h2>
						</div>
						<div class="col-sm-12 col-md-6 col-lg-6">				
							<label for="firstname">First Name:</label>
							<input type="text" class="form-state width-100" name="firstname" id="firstname"/>
						</div>
						<div class="clearfix"></div>	
						<div class="col-sm-12 col-md-6 col-lg-6">
							<label for="lastname">Last Name:</label>
							<input type="text" class="form-state width-100" name="lastname" id="lastname"/>	
						</div>
						<div class="clearfix"></div>													
						<div class="col-sm-12 col-md-6 col-lg-6">
						<label for="username">Username:</label>
						<input type="text" name="username" id="username" class="form-state width-100"/>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-12 col-md-6 col-lg-6">
						<label>Password:</label>
						<input type="password" name="password" id="password" class="form-state width-100"/>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-12 col-md-6 col-lg-6">
						<label>Confirm Password:</label>
						<input type="password" name="confirmpassword" id="confirmpassword" class="form-state width-100"/>		
						</div>										

					<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
				</form>
				<div class="clearfix"></div>
				<hr>
				<input type="button" name="btnCompanySignUp" id="btnCompanySignUp"  class="btn btn-success moveR_20" value="Sign Up">
				<a href="<c:url value="/platform/index"/>" id="btnCancel">Cancel</a>
			</div>
		</div>
	</div>
</body>
<script>
$(document).ready(function(){

	$('#btnCompanySignUp').click(function(){
		var formValid = validateForm('form');
		if(formValid){
			document.frmCompanySignUp.submit();
		}
	});

});


</script>
</html>