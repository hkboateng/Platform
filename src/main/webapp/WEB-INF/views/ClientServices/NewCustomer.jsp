<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-masked.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/customerValidation.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>

<div  id="container" class="container">
<div class="row">

          <div class="col-sm-12 col-md-8 col-md-8">
          <div class="page-header">
		  	<h1>Add Customer</h1>
		  </div>
          <div class="errors">
	          <ul>
		          <c:if test="${not empty errors}">
		          	<li>${errors }</li>
		          </c:if>
	          </ul>
          </div>
          <div class="info_message">
	          <ul>
		          <c:if test="${not empty message}">
		          	<li>${message }</li>
		          </c:if>
	          </ul>
          </div>
			<sf:form method="post" modelAttribute="customer" action="/abankus/customers/addCustomer"  name="customerForm" id="customerForm" class="form" >
			<input type="hidden" name="customerType" id="custIndividual" value="${customerType }"/>
					<div  class="row">
						<div class="col-xs-12 col-sm-6 col-md-8 col-lg-8">
							<label>
							First Name:</label>
							<input type="text" name="firstname" id="firstname" class="form-state width-100" placeholder="First Name"/>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
							<label for="middlename">Middle Name:</label>
							<input type="text" name="middlename" id="middlename" placeholder="Middle Name" class="form-state width-100"/>	
						</div>			
						<div class="clearfix"></div>			
						<div class="col-xs-12 col-sm-6 col-md-12 col-lg-12">
							<label for="lastname">Last Name:</label>
							<input type="text" name="lastname" id="lastname" placeholder="Last Name" class="form-state width-100"/>	
						</div>
						</div>
					<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-6">
						<label for="state" >Gender:</label>
						<script>genderList();</script>				
					</div>
					<div class="clearfix"></div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<label for="state" >Type of Identification:</label>
						<script>typeOfIdentification();</script>
						<div class="clear"></div>
						<label for="IdNumber" >Identification Number:</label>	
						<input type="text" name="IdNumber" id="IdNumber" placeholder="Id Number" class="form-state width-100"/>			
					</div>					
					</div>
					<div class="row">	
					<div class="col-sm-12 col-md-12">			
					<label>	Company or Organization Name: (Optional)</label>
					<input type="text" name="company_name" id="company_name" class=" form-state width-100" placeholder="Company Name"/>	
					</div>				
					<div class="col-sm-12 col-md-12">
						<label for="address1">Address 1:</label>
						<input type="text" name="address1" id="address1" placeholder="Postal Address"  class="form-state width-100"/>
					</div>
					<div class="col-sm-12 col-md-12">
						<label for="address2"> Address 2:</label>
						<input type="text" name="address2" placeholder="Optional" class=" form-state width-100" id="address2"/>				
					</div>					
					<div class="col-sm-12 col-md-4">
						<label for="city" >City:</label>
						<input type="text" class="form-state width-100" id="city" name="city">					
					</div>
					<div class="col-sm-12 col-md-4">
						<label for="state" >State:</label>
						<script>createRegionList();</script>				
					</div>
					<div class="col-xs-6 col-sm-6 col-md-4">
						<label for="zip" >Zip Code:</label>
						<input type="text" class="form-state width-100" id="zipcode" name="zipcode" size="6" maxlength="5">				
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<label for="phoneNumber">Phone Number:</label>	
						<input class="form-state width-100"  id="phoneNumber" name="phoneNumber" type="text" placeholder="(Area Code)XXX-XXXX">
					<div class="spaceBelow_10">
						<label>
							<input type="radio" name="phoneType" id="homePhone" value="homePhone" checked/> Home Phone
						</label>		
						<label >
					  		<input type="radio" name="phoneType" id="cellPhone" value="cellPhone"/> Cell (Mobile) Phone			
					  	</label>					
					</div>
 						
					</div>

				  <div class="col-xs-6 col-sm-6">
				  	<label for="emailAddress">Email Address:</label><span id="email-error" class="help-text-inline"></span>
				   	<input type="email" id="emailAddress" name="emailAddress" class="form-state width-100" placeholder="Email Address"  onblur="javascript:isEmailUnique(this.value);">						
								  
				  </div>
					</div>	
				  <div class="row">
						<div class="col-xs-12 col-sm-12 col-lg-6 col-md-6">
						<label for="accountStatus">Account Status:</label>	
							<select name="accountStatus" id="accountStatus" class="form-state  width-100">
								<option value="">Select Account Status</option>
								<option value="active" selected>Active</option>
								<option value="prospect">Prospective</option>
								<option value="inactive">InActive</option>
								<option value="close">Closed</option>
							</select>
						</div>
						<div class="clearfix">
						
						</div>
						<div class="col-xs-12 col-sm-12 col-lg-6 col-md-6">
						<label for="customerIndustry">Industry</label>
							<select name="customerIndustry" class="form-state  width-100">
								<option value="">Select Industry</option>
								<option value="financial">Financial Services</option>
								<option value="retail">Retail</option>
								<option value="selfemployed">Self-Employed</option>
								<option value="government">Government</option>
							</select>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12">
						<label for="customerNotes">Notes:</label>
						<textarea name="notes" id="customerNotes" rows="4" class="form-state width-100"></textarea>
						</div>					



				  </div>							
			
			<hr>
			<p>
			<button class="btn btn-primary" id="newCustomerSubmitBtn" onClick="javascript:validation();return false;"> Save Customer </button>
			&nbsp;&nbsp;&nbsp;
			<a href="#"> Cancel </a>
			</p>
		
			</sf:form>
          </div>
</div>
</div>
	<%-- Include page header --%>
	<jsp:include page="../footer.jsp"/>
	<!-- Page Header ends -->
</body>

<script>
$(document).ready(function(){
	$('#phoneNumber').mask('000-000-0000');
	$('#dateOfBirth').mask('00/00/0000');
	
	$('#newCustomerSubmitBtn').click(function(){
		if(validation()){
			submitForm(document.customerForm);
		}
	});
});
function isEmailUnique(email){

	$.ajax({
		url: 'isCustomerEmailUnique',
		data: {
			emailAddress:email
		},
		dataType: "json",
		success: function(results){
			console.log(results)
		},
		error :function(data){
			console.log(data.responseText);
		}
	});			
}

</script>
</html>