<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
</head>
<body>

<!-- Page Header -->
<jsp:include page="../header.jsp"/>
<!-- Page Header ends -->
<div id="container" class="container">
<div class="row height">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1>Add New Employee</h1>
			<hr>
          <div>
          <div class="col-md-7">
			          <c:if test="${not empty success}">
			          	<span class="alert alert-success">${success }</span>
			          </c:if>
			<sf:form method="post" modelAttribute="login" action="addEmployeeLogin" name="securityForm" class="form">

			<input type="hidden" name="trigger" value="security">
			<input type="hidden" name="punt" value="dashboard">
			<input type="hidden" name="currentpage" value="employeeLoginCredential">	
			<input type="hidden" name="emailAddress" value="${employee.emailAddress}"/>	
			
			<ul class="block-list">
			<li>
			  <label for="username" >Username:</label>
			    <input type="text" class="form-control" name="username" value="${userInstance.getUsername() }" placeholder="User Name">					
			</li>
			<li>
				<label for="password" >Password:</label>
				<input type="password" class="form-control" id="password" name="password">				
			</li>
			<li>
				<label for="cpassword" >Confirm Password:</label>
				<input type="password" class="form-control" id="cpassword" name="cpassword" onblur="javascript:validatePassword();" onchange="javascript:validatePassword();">	
				<div id="pwdValid"></div>			
			</li>
			</ul>		
				<ul class="spaceBelow_30">
				<c:if test="${permissionList.size() > 0}">
					<c:forEach var="permission" items="${permissionList }" varStatus="gdg" >
						<li class="col-md-6 col-lg-6">
						<input type="checkbox" name="role" value="${permission.permissionId}">${permission.permission.replaceAll("_"," ") }
						</li>
					</c:forEach>
				</c:if>
			</ul>				
			<p>
			<button class="btn btn-primary" onclick="javascript:submitForm(document.securityForm);"> Save Employee </button>
			&nbsp;&nbsp;&nbsp;
			<a href="<c:url value="/employee/listEmployee" />"> Cancel </a>
			</p>
			
			</sf:form>
          </div>
         </div>
		</div>        
</div>
</div>
<!-- Page Header -->
<jsp:include page="../footer.jsp"/>
<!-- Page Header ends -->
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
<script src="<c:url value="/resources/js/angular-ui.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</html>