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
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<h3><c:if test="${not empty customer}">${not empty customer.getCustomerName() ? customer.getCustomerName() : customer.getCompany_name()} (Account #:${customerAccount.accountNumber.toUpperCase() })</c:if></h3>
	
	<c:choose>
		<c:when test="${not empty address}">
			<div class="col-xs-12 col-md-3">
				<c:forEach items="${address}" var="addressList">
					<span class="bold">${addressList.addressType.toUpperCase()} Address:</span><br>
					${addressList.address1 }&nbsp;${addressList.address2 }
					${addressList.city }&nbsp;${addressList.region }&nbsp;${addressList.zipcode }<br>
				</c:forEach>	
			</div>
		</c:when>	 
		<c:otherwise>
			Address Information are not available this time!!!.
		</c:otherwise>	
	</c:choose>
		<div class="col-xs-12 col-md-3">
			<!-- Phone Number's -->
			<c:if test="${not empty phone}">
				<div class="bold">Phone Number(s):</div>
				<c:forEach items="${phone }" var="phone" varStatus="counter">
					<div>${phone.phoneNumber }&nbsp; </div>
				</c:forEach>
			</c:if>
		</div>		
		<div class="col-xs-12 col-md-3">
			<!-- Customer Email Address -->
			<c:if test="${not empty email}">
				<div class="bold">EmailAddress:</div>
				<c:forEach items="${email }" var="email" varStatus="counter">
					<div>${email.emailAddress} &nbsp; </div>
				</c:forEach>
			
			</c:if>	
		</div>		
	</div> 	
	<div class="clearfix"></div>
	<div class="col-sm-12 col-md-3 col-lg-3 main-container">
	
		<jsp:include page="../sidebar.jsp"/>
	</div>
	<div class="col-sm-12 col-md-8 col-lg-9 main-container">

	          <div class="row">
	          <c:if test="${not empty message}">
		          <div class="alert alert-danger" role="alert">
				      ${message}
				   </div>         
	          </c:if>
	          </div>
	          <%--
			 <c:if test="${not empty customer}">
			  	<div id="" class="row ">
	 				 
	  				 <div class="col-xs-12 col-md-8">
		  				 <div class="dropdown">
							  <button class="buttonAction dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							    <i class="fa fa-bars fa-lg moveR_10"></i>
							    Menu
							  
							  </button>
							  <ul class="dropdown-menu width-100 " aria-labelledby="dropdownMenu1">
				  				 <c:choose>
				  				 	<c:when test="${customerAccount.isCustomerActive()}">
					  				 	<li>
									  		<a href="sendMessage" class=""><span class="glyphicon glyphicon-envelope moveR_20" aria-hidden="true"></span>Send Message</a>  				 	
					  				 	</li>  				 	
				  				 	</c:when>
				  				 	<c:otherwise>
					  				 	<li>
									  		<a href="sendSaleMessage" class=""><span class="glyphicon glyphicon-envelope moveR_20" aria-hidden="true"></span>Send Sales Message</a>  				 	
					  				 	</li>   				 	
				  				 	</c:otherwise>
								</c:choose>
								<li role="separator" class="divider"></li>
				  				 	<li>
								  		<a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class=""><i class="fa fa-money moveR_20"></i>Make A Payment</a>  				 	
				  				 	</li>	
				  				 	<li>
				  				 		<a href="javascript:orderHistory('orderHistory',document.formOrderHistroy);" ><i class="fa fa-exchange moveR_20"></i>View Customer Order History</a>
				  				 	</li>	
				  				 	<li role="separator" class="divider"></li>
				  				 	<li>
				  				 		<a href="javascript:createOrder('createCustomerOrder','document.createCustomerOrder');"><i class="fa fa-exchange moveR_20"></i>Add New Order</a>
				  				 	</li>	
							  </ul>
							</div>
	  
	  				 </div>		  	
	
				</div>
						  <sf:form  name="formOrderHistroy" method="post" action="">
						  	<input type="hidden" name="customerId" id="customerIdHdn"  value="${customer.customerId }"/>
						  </sf:form>
	          <sf:form name="frmUpdateAccountStatus" method="post" action="updateAccountStatus">
	          	  <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>	          
	          </sf:form>
	          <sf:form name="makeCustomerPayment" method="post" action="">
	          <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>
	          </sf:form>
				</c:if>
				 --%>
	  </div> 
</div>
</div>
  


          
 
      

</body>
</html>
