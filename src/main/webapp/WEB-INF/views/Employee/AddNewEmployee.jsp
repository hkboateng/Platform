<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation/customerValidation.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>

<div class="container" id="container">
<div class="row">

	<div class="col-sm-12 col-md-12 col-lg-12 center-block">
          <h1>Add New Employee</h1>
			<hr>

          <div class="row">
          <div class="col-md-8">
          <c:if test="${not empty error_message}">
          	<div class="alert alert-danger alert-dismissible fade in" role="alert">
     	  		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
				${error_message }
    	  	</div>
    	  </c:if>
          <c:if test="${not empty success_message}">
          <div class="alert alert-danger alert-dismissible fade in" role="alert">
     	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
	          <ul>
		          
		          	<li class="alert alert-warning">${success_message }</li>
		          
	          </ul>
    	  </div>
    	  </c:if>    	  
			<sf:form method="post" modelAttribute="employee" action="addEmployee"  id="formNewEmployee" name="formNewEmployee" class="form" >
			<div class="col-xs-12 col-sm-12 col-md-6">

				<label for="firstname" > First Name:</label>
				<input type="text" class="form-state width-100" id="firstname" name="firstname">			
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-6">
				<label for="lastname" > Last Name:</label>
				<input type="text" class="form-state width-100" id="lastname" name="lastname">				
			</div>
			<div class="col-xs-12 col-sm-12 col-md-12">
				<label for="address1" >Address:</label>
				<input type="text" class="form-state width-100" id="address1" name="address1">		
			</div>
			<div class="col-xs-12 col-sm-12 col-md-12">
				<label for="address2" >Address 2 (Optional):</label>
				<input type="text" class="form-state width-100" id="address2" name="address2">				
			</div>
			<div class="clearfix"></div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<label for="city" >City:</label>
				<input type="text" class="form-state width-100" id="city" name="city">	
			</div>	
			<div class="clearfix"></div>			
			<div class="col-xs-12 col-sm-6 col-md-6">
				<label for="state" >State:</label>
				<input type="text" class="form-state width-100" id="state" name="state">	
			</div>		
			<div class="clearfix"></div>	
			<div class="col-xs-12 col-sm-6 col-md-6">
				<label for="zipcode" >Zip Code:</label>
				<input type="text" class="form-state width-100" id="zipcode" name="zipcode" size="6" maxlength="5">				
			</div>			
			<div class="clearfix"></div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<label for="emailAddress" >Email Address:</label>
				<input type="email" class="form-state width-100" id="emailAddress"  name="emailAddress">	
			</div>	
			<div class="clearfix"></div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<label for="phoneNumber" >Phone Number:</label>
				<input type="text" class="form-state width-100" id="phoneNumber" name="phoneNumber">				
			</div>	
			<div class="clearfix"></div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<label for="gender" >Gender:</label>
				<script>genderList();</script>				
			</div>
			<div class="clearfix"></div>
          	<hr>	
          	 </sf:form> 		 
			<p>
			
			<button  id="btnAddEmployee" onclick="javascript:employeeFormValidation('#formNewEmployee');" class="btn btn-primary">Continue</button>
			<a href="<c:url value="/employee/listEmployee" />" class="moveL_20"> Cancel </a>
			</p> 
			
          </div>
        </div>
</div>  
     
</div>
</div>
<%-- Include page footer --%>
<jsp:include page="../footer.jsp"/>
<%-- End Include footer page --%>
</body>
<script>
$(document).ready(function(){
	$("#btnAddEmployee").click(function(){
		if(employeeFormValidation('#formNewEmployee')){
			$('#formNewEmployee').submit();
		}
	});
});
</script>

</html>