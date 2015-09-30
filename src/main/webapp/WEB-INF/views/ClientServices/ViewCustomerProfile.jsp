<%
	
	String customerType = request.getParameter("customerType");
	String employeeInfo = (String)session.getAttribute("employeeInfo");
	pageContext.setAttribute("customerType", customerType);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<%-- End of Include page header --%>
<div class="container">
<div class="row">

<div class="col-sm-9 col-md-12 col-lg-12 main-container">

          <h3>Customer Account Details</h3>
		  <hr class="line1">
          <div class="row">
          <c:if test="${not empty message}">
	          <div class="alert alert-danger" role="alert">
			      ${message}
			   </div>         
          </c:if>
          </div>
		 <c:if test="${not empty customer}">
		  	<div id="" class="row ">
 				 <div class="col-xs-12 col-md-8">
  				 	<div>
					  	<ul class="navigation-linear">
					  	<li class="pull-left bold">Personal Details</li>
						  <li class="pull-right"><a href="" class=""><i class="glyphicon glyphicon-pencil moveR_10"></i>Edit </a></li>
						</ul>  				 	
 				 	</div>				 

					<div class="clearfix"></div>
					<hr  class="nav-line"> 				 
 				 <div class="col-xs-12 col-md-6">
 				  <c:if test="${not empty customer}">
 				 <div id="customerDetailBlock">

				  	<div id="customerPersonalData">
				  		<p><span class="bold">Full Name:</span>&nbsp;${customer.firstname} ${customer.middlename } ${customer.lastname }</p>
				  		<p><span class="bold">Company Name:</span> ${customer.company_name }</p>
				  		<p><span class="bold">Customer Number: </span>${customer.customerNumber.toUpperCase() }</p>
				  	</div>
				  	</div>
				  	</c:if>
				  	</div>
				  	<div class="col-xs-12 col-md-6">
				  	<!-- Customer Address -->
				  	<div id="customerAccountData">
					  	<c:choose>
							<c:when test="${not empty address}">
						  	
						  	<c:forEach items="${address}" var="addressList">
						  		<span class="bold">${addressList.addressType.toUpperCase()} Address:</span><br>
						  		${addressList.address1 }&nbsp;${addressList.address2 }
						  		${addressList.city }&nbsp;${addressList.region }&nbsp;${addressList.zipcode }<br>
						  	</c:forEach>	
						  	</c:when>	 
						  	<c:otherwise>
						  	<div  class="alert alert-info" role="alert">
							  <strong>Ooops!</strong> Addess Information are not available this time!!!.
							</div>
						  	</c:otherwise>				 	
	 				 	</c:choose>	
	 				 	</div>
	 				 	<br>			  	
				  	<!-- Customer Phone Number's -->
					  	<c:if test="${not empty phone}">
					  	<c:forEach items="${phone }" var="phone" varStatus="counter">
					  		<p><b>Phone ${counter.count}</b>: &nbsp; ${phone.phoneNumber }</p>
					  	</c:forEach>
					  	</c:if>
				  	<!-- Customer Email Address -->
					  	<c:if test="${not empty email}">
					  	<c:forEach items="${email }" var="email" varStatus="counter">
					  		<p><b>EmailAddress ${counter.count}</b>: &nbsp; ${email.emailAddress}</p>
					  	</c:forEach>
					  	</c:if>
					  	
				  	
				  	</div>
				  	<div class="clearfix"></div>
			
 				 	<div>
					  	<ul class="navigation-linear">
					  	<li class="pull-left bold">Account Details</li>
					  	<c:if test="${not empty customerAccount}">
						  <li class="pull-right"><a href="" class=""><i class="glyphicon glyphicon-pencil moveR_10"></i>Edit </a></li>
						  </c:if>
						</ul>  				 	
 				 	</div>
 				 	<div class="clearfix"></div>
 				 	<hr  class="nav-line">
 				 	<c:choose>
						<c:when test="${not empty customerAccount}">
								<!-- Change Account Status modal -->
								
								<div class="modal fade update_CustomerCategory" tabindex="-1" role="dialog" aria-labelledby="updateCustomerCategory">
								  <div class="modal-dialog">
								    <div class="modal-content">
								    <div class="modal-header">
								    <h4 class="modal-title bold">Change Customer Account Status</h4>
								    </div>
								      <label>Account Status:</label>
								      <select name="accountStatus">
								      <c:if test="${customerAccount.status eq 'Active'}">
											<option value="prospect">Prospective</option>
											<option value="inactive">InActive</option>
											<option value="close">Closed</option>       
								      </c:if>
								      <c:if test="${customerAccount.status eq 'prospect'}">
										<option value="active">Active</option>
										<option value="inactive">InActive</option>
										<option value="close">Closed</option>      
								      </c:if>
								      <c:if test="${customerAccount.status eq 'inactive'}">
										<option value="active">Active</option>
										<option value="prospect">Prospective</option>
										<option value="close">Closed</option>        
								      </c:if>
								       <c:if test="${customerAccount.status eq 'close'}">
										<option value="active">Active</option>
										<option value="prospect">Prospective</option>
										<option value="inactive">InActive</option>
										</c:if> 
								      </select>
								      	 <input type="hidden" name="customerId" value="${customerAccount.customer.customerId }"/>
								      	 <input type="hidden" name="customerAccountId" value="${customerAccount.customerAccount}"/>
										  <div class="modal-footer">
									        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									        <button type="button" class="btn btn-primary" onClick="javascript:document.frmUpdateAccountStatus.submit();">Save changes</button>
									      </div>							      
								    </div>
								  </div>
								</div>		
								<!-- End Change Account Status modal -->				
					  	<div id="customerAccountData">
					  		<p><span class="bold">Account Number: </span>${customerAccount.accountNumber }</p>
					  		<p><span class="bold">Account Status: </span> ${customerAccount.status }
					  		<button type="button" class="btn btn-primary btn-sm moveR_10" data-toggle="modal" data-target=".update_CustomerCategory"><i class="glyphicon glyphicon-edit  moveR_5"></i>Edit</button>
					  		<p><span class="bold">Date Created: </span><fmt:formatDate type="date" dateStyle="long" timeStyle="long" value="${customerAccount.dateCreate }" /> </p>
					  		<p><span class="bold">Notes: </span> <br>
					  		<c:if test="${not empty customerAccount.notes }">
					  			${customerAccount.notes }
					  		</c:if>
					  		<c:if test="${empty customerAccount.notes }">
					  			No Data Available
					  			<!-- Button trigger modal -->
								<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
								  Launch demo modal
								</button>
					  		</c:if>					  		
					  		</p>
					  	</div> 	
					  	</c:when>	 
					  	<c:otherwise>
					  	<div  class="alert alert-info" role="alert">
						  <strong>Ooops!</strong>  ${customer.firstname} ${customer.middlename } ${customer.lastname } Account Details are not available this time!!!.
						</div>
					  	</c:otherwise>				 	
 				 	</c:choose> 	
					<div class="clearfix"></div>
 				 	<div>
 				 	
					  	<ul class="navigation-linear">
					  	<li class="pull-left bold">Contact Information</li>
					  	<c:if test="${not empty address}">
						  <li class="pull-right"><a href="" class=""><i class="glyphicon glyphicon-pencil moveR_10"></i>Edit </a></li>
						  </c:if>
						</ul>  				 	
 				 	</div>
 				 	
 				 	<div class="clearfix"></div>
 				 	<h4>Address	</h4>
 				 	<hr  class="nav-line">									  	
 				 	<c:choose>
						<c:when test="${not empty address}">
					  	<div id="customerAccountData">
					  	<c:forEach items="${address}" var="addressList">
					  		<span class="bold">${addressList.addressType.toUpperCase()} Address:</span><br>
					  		${addressList.address1 }&nbsp;${addressList.address2 }
					  		${addressList.city }&nbsp;${addressList.region }&nbsp;${addressList.zipcode }<br>
					  	</c:forEach>	
					  	</div> 	
					  	</c:when>	 
					  	<c:otherwise>
					  	<div  class="alert alert-info" role="alert">
						  <strong>Ooops!</strong> Addess Information are not available this time!!!.
						</div>
					  	</c:otherwise>				 	
 				 	</c:choose>				  	
				  									 
 				 </div>
  				 <div class="col-xs-12 col-md-4">
  				 <div id="customerDetailBlock">
 				 	<div>
					  	<ul class="navigation-linear">
					  	<li class="pull-left bold">Quick Links</li>
						</ul>  				 	
 				 	</div>				 
  				 </div>
  				 <div class="clearfix"></div>
  				 <hr  class="nav-line">
  				 <ul class="list-button-group">
  				 <c:choose>
  				 	<c:when test="${customerAccount.isCustomerActive()}">
	  				 	<li>
					  		<a href="sendMessage" class=""><span class="glyphicon glyphicon-envelope moveR_20" aria-hidden="true"></span>Send Message</a>  				 	
	  				 	</li>  				 	
  				 	</c:when>
  				 	<c:otherwise>
	  				 	<li>
					  		<a href="snedSaleMessage" class=""><span class="glyphicon glyphicon-envelope moveR_20" aria-hidden="true"></span>Send Sales Message</a>  				 	
	  				 	</li>   				 	
  				 	</c:otherwise>
				</c:choose>
  				 	<li>
				  		<a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class=""><i class="fa fa-money moveR_20"></i>Make A Payment</a>  				 	
  				 	</li>	
  				 	<li>
  				 		<a href="javascript:orderHistory('orderHistory',document.formOrderHistroy);" ><i class="fa fa-exchange moveR_20"></i>View Customer Order History</a>
  				 	</li>	
  				 	<li>
  				 		<a href="/abankus/client/createCustomerOrder?accountNumber=${customerAccount.accountNumber }"><i class="fa fa-exchange moveR_20"></i>Add New Order</a>
  				 	</li>			
				</ul>
  				 </div>		  	

			</div>
			<hr>	

			  <sf:form  name="formOrderHistroy" method="post" action="">
			  	<input type="hidden" name="customerId" id="customerIdHdn"  value="${customer.customerId }"/>
			``</sf:form>
	          <sf:form name="frmUpdateAccountStatus" method="post" action="updateAccountStatus">
	          	  <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>	          
	          </sf:form>
	          <sf:form name="makeCustomerPayment" method="post" action="">
	          <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>
	          </sf:form>			
			</c:if>
	<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>  
	<script>
	function makePayment(url,form){
		if(isEmpty(url) || !isAlphaNumeric(url)){
			return false;
		}
		form.action="/abankus/customers/"+url
		form.submit();
	}
	
	function orderHistory(url,form){
		if(isEmpty(url) || !isAlphaNumeric(url)){
			return false;
		}		
		form.action = "/abankus/client/"+url;
		form.submit();
	}
	</script>	  


          
</div>        
</div>
</div>
</body>
</html>
