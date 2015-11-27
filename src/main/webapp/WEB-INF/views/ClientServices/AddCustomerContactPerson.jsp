<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Contact Person - Abankus Connection</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/platform.css" />"	rel="stylesheet" />
		<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>

		<script	src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/bootstrap.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/accounting/accounting.js" />" 	type="text/javascript"></script>
				<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
	</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div id="container" class="container">
		<div class="row">
			
			<div class="col-sm-12 col-xs-12 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2">
				<div class="center-block">
				<h2>Update Contact Person for Customer</h2>
				<hr>
				<sf:form method="post" action="updateContactPerson" id="updateContactPersonForm" name="updateContactPersonForm">
				<div class="col-xs-12 col-sm-6 col-md-5 col-lg-6">
					<label for="firstname">First Name:
					</label>	
					<input type="text" name="firstname" id="firstname" class="form-state"/>					
				</div>
				<div class="col-xs-12 col-sm-6 col-md-5 col-lg-6">
					<label for="lastname">Last Name:</label>	
					<input type="text" name="lastname" id="lastname" class="form-state"/>
				</div>			
				<div  class="col-xs-12 col-sm-6 col-md-5 col-lg-6">
					<label for="phonenumber">Phone Number:	</label>			
					<input type="text" name="phonenumber" id="phonenumber" class="form-state"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-5 col-lg-6">
					<label for="emailAddress">Email Address:
						
					</label>			
					<input type="text" name="emailAddress" id="emailAddress" class="form-state"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-5 col-lg-6">
					<label for="association">How Associated:</label>
					<select class="form-state" name="association">
						<option value="">Select</option>
						<option value="employee">Staff</option>
					</select>
				</div>	
				<div class="clearfix"></div>
				<hr>
				</sf:form>
				<div>
					<button id="submitBtn" class="btn btn-success moveR_20">Submit Update</button>
					<a href="<c:url value="viewProfile"/>" >Cancel</a>
				</div>
				
			</div>
			
			
			
			</div>		
		</div>
	</div>

	<script>
	$(document).ready(function(){
		$('#submitBtn').on('click',function(){
			if(validateContact()){
				submitForm();
			}			
		});

	});
	function submitForm(){
		var form = document.updateContactPersonForm;
		form.submit();
	}
	var validateContact = function(){
		$("#updateContactPersonForm").validate({
			onsubmit:false,
			rules : {
				firstname: {
					required :true
				},
				lastname: {
					required :true
				},
				emailAddress: {
					required :true,
					email:true
				},
				phonenumber: {
					required :true,
					digits: true
				},
				association: {
					required :true
				}
			}
		});
		if(!$('#updateContactPersonForm').valid())
			 return false;
		 return true;				
	}
	function validatePhone(number){
		var reg = "\d{3}-\d{3}-\d{4}|\d{10}";
		return reg.test(nubmer);
	}
	</script>
	</body>
	</html>