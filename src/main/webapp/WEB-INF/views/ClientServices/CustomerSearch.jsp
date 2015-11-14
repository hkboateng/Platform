<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Customer Search</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"	rel="stylesheet" />
<link href="<c:url value="/resources/css/platform.css" />"	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>
<script	src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"	type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />"	type="text/javascript"></script>
<script src="<c:url value="/resources/js/accounting/accounting.js" />"	type="text/javascript"></script>
</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div id="container" class="container">
		<div class="row">
			<div class="col-sm-9 col-md-10 center-block">
				<h2>Search for Customer</h2>
				<hr class="line1" />
				<div class="col-md-12">
					<label class="radio-inlne moveR_20">
						<input type="radio" name="searchCustomer" id="customerIdSrc" value="customerIdDiv" checked/>
						Find By Customer Name:
					</label>
					<label class="radio-inline moveR_20">
						<input type="radio" name="searchCustomer" id="accountNumberSrc" value="accountNumberDiv" class=""/>
						Account Number or Customer Number:
					</label>
					<label class="radio-inline moveR_20">
						<input type="radio" name="searchCustomer" id="emailPhoneSrc" value="emailPhoneDiv" class=""/>
						Email or Phone Number:
					</label>
					<div>
						<div id="customerIdDiv" class="hidden">
							<label>First Name:</label>
							<input type="text" name="firstname" class="form-state">
							<label>Last Name:</label>
							<input type="text" name="lastname" class="form-state">						
						</div>
						<div id="accountNumberDiv" class="hidden">
							<label>Customer Account Number:</label>
							<input type="text" name="accountNumber" class="form-state">
							<div class="center-block ">OR</div>
							<label>Customer Number or Identification:</label>
							<input type="text" name="customerNumber" class="form-state">								
						</div>		
						<p>
							<input type="submit" id="btnCustomerSearchSubmit" value=" Search" class="btn btn-primary">
						</p>			
					</div>

				</div>
				<div class="clearfix"></div>
				<div id="resultHeading" class="resultHeading">
					<div id="resultHeading-word" class="anw">Search Results</div>
				</div>
					<table class="table">
						<thead>
							<tr>
								<th>Name:</th>
								<th>Address:</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(e){
	//e.preventDefault();
	$('#customerIdSrc').on('click',function(){
		$('#searchType').val("customerId");
		$("#customerIdDiv").removeClass('hidden');
		$('#customerNameDiv').addClass('hidden');	
	});
	$('#accountNumberSrc').on('click',function(){
		$('#searchType').val("accountNumber");
		$("#customerIdDiv").addClass('hidden');
		$('#accountNumberDiv').removeClass('hidden');
	});	
});
</script>
</html>