 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Page Header -->
<nav id="" class="navbar  navbar-default navbar-static-top hideen-xs">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#stickyNavbar" aria-expanded="false" aria-controls="stickyNavbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/abankus/platform/index">Abankus Payments</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">

          <ul class="nav navbar-nav navbar-right">
            <li id="">
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>${employee.firstname}&nbsp;${employee.lastname}
		          <span class="caret"></span>
		        </a>
	            <ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/employee/editEmployee?employeeId=${employee.employeeId}&jumboId=${employee.id}"><i class="fa fa-pencil-square-o fa-lg moveR_10"></i>View Profile</a></li>
			          <li><a href="<c:url value="/Company/viewCompanyProfile" />"><i class="fa fa-cogs fa-lg moveR_10"></i>Company Settings</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="<c:url value="/platform/logout"/>"><i class="glyphicon glyphicon-off fa-lg moveR_10"></i>Logout</a></li>
		        </ul>
            </li>

          </ul>

        </div>
      </div>
    </nav>
  <nav id="stickyNav" class="navbar navbar-blue ">
  <div class="container">
        <div id="stickyNavbar" class="navbar-collapse collapse">
		<ul id="stickyMenu" class="nav navbar-nav navbar-right">
            <li id="">
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>${employee.firstname}&nbsp;${employee.lastname}
		          <span class="caret"></span>
		        </a>
	            <ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/employee/editEmployee?employeeId=${employee.employeeId}&jumboId=${employee.id}"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Update your Profile</a></li>
		          
			      <li><a href="<c:url value="/platform/settings" />"><i class="fa fa-cogs fa-lg moveR_5"></i>Account Settings</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="<c:url value="/platform/logout"/>"><i class="glyphicon glyphicon-off fa-lg moveR_10"></i>Logout</a></li>
		        </ul>
            </li>

          </ul>
          <ul class="nav navbar-nav">
            <li>
            	<a href="<c:url value="/platform/index" /> "><i class="fa fa-home fa-lg moveR_10"></i>DashBoard</a>
            </li>
            
            <li>
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>Products/Services
		          <span class="caret"></span>
		        </a> 
				<ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/products/createProduct"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Add Product</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="/abankus/products/listProduct"><i class="fa fa-sign-out fa-lg moveR_5"></i>List Products</a></li>
		        </ul>            
            </li>            
            <li>
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="glyphicon glyphicon-tasks moveR_10"></i>Sale Transaction
		          <span class="caret"></span>
		        </a> 
				<ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/registration/employee"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Accept Payment</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="/abankus/employee/listEmployee"><i class="fa fa-sign-out fa-lg moveR_5"></i>List Employees</a></li>
		        </ul>            
            </li>			
	            <li>
		            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			          <i class="glyphicon glyphicon-tasks moveR_10"></i>Payments and Collection
			          <span class="caret"></span>
			        </a> 
					<ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
			          <li><a href="<c:url value="/Payments/QuickPayment" />"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Accept Payment</a></li>
			          <li role="separator" class="divider"></li>
			          <li><a href="/abankus/employee/listEmployee"><i class="fa fa-sign-out fa-lg moveR_5"></i>Search for Transaction</a></li>
					  <li><a href="#"><span class="glyphicon glyphicon-list-alt moveR_20"></span>Transaction History</a></li>
					  <li>
						<a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class=""><i class="fa fa-money moveR_20"></i>Make A Payment</a>  				 	
					  </li>	
					  <li>
						<a href="javascript:submitCustomerURL('createCustomerOrder','document.createCustomerOrder');"><i class="fa fa-exchange moveR_20"></i>Add New Order</a>
					  </li>	
			        </ul>   
						<sf:form  name="formOrderHistroy" method="post" action="">
						</sf:form>
				        <sf:form name="frmUpdateAccountStatus" method="post" action="updateAccountStatus">
				          	  <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>	          
				        </sf:form>
				        <sf:form name="makeCustomerPayment" method="post" action="">
				          <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>
				        </sf:form>		                 
	            </li>						
            <li>
	            <a id="aClientInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="glyphicon glyphicon-cog moveR_10"></i>Client Services
		        </a> 
				<ul id="menu1" class="dropdown-menu" aria-labelledby="aClientInfoLink">
		          <li><a href="/abankus/customer/createOrders"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Create Orders</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="/abankus/customers/create"><i class="fa fa-sign-out fa-lg moveR_5"></i>Add New Customers</a></li>
		          <li><a href="/abankus/customers/listCustomer"><i class="fa fa-sign-out fa-lg moveR_5"></i>List Customers</a></li>
		          <li><a href="/abankus/customers/searchForCustomer">Search for Customers</a></li>
		        </ul>		                  
            </li>
          	<li class="dropdown">
          		<a id="searchBtn" href="<c:url value="/platform/platformSearch" />" class="activateLink"><span class="glyphicon glyphicon-search moveR_10"></span>Search</a>
        	</li>  
			<li id="" class="hidden-sm hidden-md hidden-lg">
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>${employee.firstname}&nbsp;${employee.lastname}
		          <span class="caret"></span>
		        </a>
	            <ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/employee/editEmployee?employeeId=${employee.employeeId}&jumboId=${employee.id}"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Update your Profile</a></li>
			          <li><a href="<c:url value="/platform/settings" />"><i class="fa fa-cogs fa-lg moveR_5"></i>Account Settings</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="<c:url value="/platform/logout"/>"><i class="glyphicon glyphicon-off fa-lg moveR_5"></i>Logout</a></li>
		        </ul>
            </li>        	      
          </ul>
          </div>        
  </div>
  
</nav>
<script>
$(document).ready(function() {
	var stickyNavTop = $('#stickyNav').offset().top;
	 
	var stickyNav = function(){
	var scrollTop = $(window).scrollTop();
	      
	if (scrollTop > stickyNavTop) { 
	    $('#stickyNav').addClass('sticky');
	    $('#stickyMenu').removeClass('hidden');
	} else {
	    $('#stickyNav').removeClass('sticky'); 
	    $('#stickyMenu').addClass('hidden');
	}
	};

	
		stickyNav();
		 
		$(window).scroll(function() {
		    stickyNav();
		});
	});
	
	
function makePayment(url,form){
	if(isEmpty(url) || !isAlphaNumeric(url)){
		return false;
	}
	form.action="/abankus/customers/"+url
	form.submit();
}
function toggleSearchContainer(){
	$('#searchContainer').toggle('fast');
}



</script>